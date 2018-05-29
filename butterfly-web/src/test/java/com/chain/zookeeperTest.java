package com.chain;

import org.I0Itec.zkclient.ZkClient;
import org.apache.catalina.User;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by lian.ran on 2018/5/24.
 */
public class zookeeperTest {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("xxxxxx:2181");
        Stat stat =new Stat();
        List<String> nodes = zkClient.getChildren("/consumers/prod_READ_BATCH_MERGE_SETTLE_FILE/offsets");


        Object obj = zkClient.readData("/consumers/prod_READ_BATCH_MERGE_SETTLE_FILE/offsets/prod_batch_merge_settle_file");
        System.out.println("节点内容:"+obj);

        nodes.forEach(p ->{
            System.out.println("nodes:"+p.toString());
            List<String> childrens = zkClient.getChildren("/consumers/prod_READ_BATCH_MERGE_SETTLE_FILE/offsets"+"/"+p);
            if(childrens!=null){
                childrens.forEach(y->{
                    System.out.println("childrens:"+p.toString()+","+y);
//                    List<String> childrens2 = zkClient.getChildren("/consumers/prod_READ_BATCH_MERGE_SETTLE_FILE/offsets"+"/"+p+"/"+y);
//                    System.out.println("childrens2:"+childrens2);
//                    if(childrens2!=null){
//                        childrens2.forEach(k->{
//                            System.out.println("childrens2:"+y.toString()+","+k);
//                        });
//                    }
                });
            }
        });
    }
}
