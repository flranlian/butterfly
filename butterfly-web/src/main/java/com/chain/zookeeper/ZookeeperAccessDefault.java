package com.chain.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

public class ZookeeperAccessDefault implements ZookeeperAccess {
  private final CuratorFramework rootClient, currentClient;

  public ZookeeperAccessDefault(String connectionStr, String rootPath) {
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(500, 30);

    rootClient = CuratorFrameworkFactory.newClient(connectionStr, retryPolicy);
    rootClient.start();

    currentClient = rootClient.usingNamespace(killHeadSlash(rootPath));
  }

  private ZookeeperAccessDefault(CuratorFramework rootClient, CuratorFramework currentClient) {
    this.rootClient = rootClient;
    this.currentClient = currentClient;
  }

  private static String killHeadSlash(String str) {
    if (str == null) return null;
    if (str.length() == 0) return str;
    if (str.startsWith("/")) return str.substring(1);
    return str;
  }

  private ZookeeperAccessDefault(CuratorFramework rootClient, CuratorFramework parentClient, String subPath) {
    this.rootClient = rootClient;
    currentClient = parentClient.usingNamespace(killHeadSlash(parentClient.getNamespace() + "/" + killHeadSlash(subPath)));
  }

  @Override
  public void close() {
    rootClient.close();
  }

  public ZookeeperAccess cd(String subPath) {
    return new ZookeeperAccessDefault(rootClient, currentClient, subPath);
  }

  @Override
  public ZookeeperAccess root() {
    return new ZookeeperAccessDefault(rootClient, rootClient);
  }

  @Override
  public void setBytes(String subPath, byte[] bytes) {
    try {
      String curatorSubPath = ensureHeadSlash(subPath);

      if (currentClient.checkExists().forPath(curatorSubPath) == null) {
        currentClient.create().creatingParentContainersIfNeeded().forPath(curatorSubPath);
      }

      currentClient.setData().forPath(curatorSubPath, bytes);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static String ensureHeadSlash(String subPath) {
    if (subPath == null) return null;
    if (subPath.startsWith("/")) return subPath;
    return '/' + subPath;
  }

  @Override
  public byte[] getBytes(String subPath) {
    try {
      String slashedPath = ensureHeadSlash(subPath);
      if (currentClient.checkExists().forPath(slashedPath) == null) return null;
      return currentClient.getData().forPath(slashedPath);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean exists(String subPath) {
    try {
      return currentClient.checkExists().forPath(ensureHeadSlash(subPath)) != null;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void delete(String subPath) {
    try {
      currentClient.delete().guaranteed().deletingChildrenIfNeeded().forPath(ensureHeadSlash(subPath));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Tapping getChangingBytes(String subPath, GetBytesHandler handler) {
    try {

      final NodeCache nodeCache = new NodeCache(currentClient, ensureHeadSlash(subPath));

      NodeCacheListener listener = () -> {
        ChildData currentData = nodeCache.getCurrentData();
        if (currentData == null) return;
        if (currentData.getStat() == null) {
          handler.gotBytes(null);
        } else {
          handler.gotBytes(currentData.getData());
        }

      };

      nodeCache.getListenable().addListener(listener);

      nodeCache.start();
      listener.nodeChanged();

      return () -> {
        try {
          nodeCache.close();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      };

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Path Cache：监视一个路径下1）孩子结点的创建、2）删除，3）以及结点数据的更新。产生的事件会传递给注册的PathChildrenCacheListener。
   * @param subPath
   * @param handler
   * @return
   */
  public Tapping getPathCacheChangingBytes(String subPath, GetBytesHandler handler) {
    try {

   //   final NodeCache nodeCache = new NodeCache(currentClient, ensureHeadSlash(subPath));

      PathChildrenCache nodeCache  = new PathChildrenCache(currentClient,ensureHeadSlash(subPath),true);

      /*
      PathChildrenCacheListener listener = () -> {
        List<ChildData> currentDatas = nodeCache.getCurrentData();
        if(currentDatas!=null){
          currentDatas.forEach(currentData ->{
            if (currentData == null) return;
            if (currentData.getStat() == null) {
              handler.gotBytes(null);
            } else {
              handler.gotBytes(currentData.getData());
            }
          });
        }

      };
      nodeCache.getListenable().addListener(listener);*/
      nodeCache.getListenable().addListener((client1, event) -> {
        ChildData data = event.getData();
        if (data == null) {
          System.out.println("No data in event[" + event + "]");
          handler.gotBytes(null);
        } else {
          System.out.println("Receive event: "
                  + "type=[" + event.getType() + "]"
                  + ", path=[" + data.getPath() + "]"
                  + ", data=[" + new String(data.getData()) + "]"
                  + ", stat=[" + data.getStat() + "]");
          handler.gotBytes(data.getData());
        }
      });
      nodeCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);


      return () -> {
        try {
          nodeCache.close();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      };

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  @Override
  public long lastModifiedMillis(String subPath) {
    try {
      Stat stat = currentClient.checkExists().forPath(ensureHeadSlash(subPath));
      if (stat == null) return 0;
      return stat.getMtime();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String getRootPath() {
    return ensureHeadSlash(currentClient.getNamespace());
  }

  @Override
  public List<String> ls() {
    try {
      return currentClient.getChildren().forPath("/");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String fullName() {
    return ensureHeadSlash(currentClient.getNamespace());
  }

  @Override
  public String name() {
    String fullName = fullName();
    if (fullName == null) return null;
    int index = fullName.lastIndexOf('/');
    if (index < 0) return fullName;
    return fullName.substring(index + 1);
  }
}
