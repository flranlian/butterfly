package com.chain;

import com.chain.com.chain.service.IAccountService;
import com.chain.com.chain.service.MybatisAccountService;
import com.chain.mode.Account;
import com.chain.zookeeper.ConfData;
import com.chain.zookeeper.ZookeeperAccess;
import com.chain.zookeeper.ZookeeperAccessDefault;
import com.msxf.kafka.consumer.DefaultConsumer;
import com.msxf.kafka.consumer.DefaultConsumerFactory;
import com.msxf.kafka.exception.MsxfKafkaException;
import com.msxf.kafka.handler.ConsumerHandler;
import com.msxf.kafka.producer.DefaultProducer;
import com.msxf.kafka.producer.DefaultProducerFactory;
import javafx.application.Application;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ButterflyWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class ButterflyWebApplicationTests {
	@Autowired
	IAccountService accountService;
	@Test
	public void contextLoads() {
		List<Account> list = accountService.findAccountList();
		System.out.println("测试结果begin");
		if(list!=null){
		  for(Account a :list){
		  	System.out.println("测试结果"+a.toString());
		  }
		}
		System.out.println("测试结果==========");
	}

	@Test
	public void testConsumerKafka(){
		String groupId = "testClient";
		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrapServers());
		props.put("group.id", groupId);
		props.put("enable.auto.commit", "false");
		props.put("auto.commit.interval.ms", "1000");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		props.put("auto.offset.reset", "earliest");
		props.put("max.partition.fetch.bytes", 20000000);
		props.put("rebalance.max.retries", 100);
		String topic = "prod_client";
		DefaultConsumerFactory factory = new DefaultConsumerFactory(props);

		DefaultConsumer defaultConsumer = new DefaultConsumer(topic,factory, new ConsumerHandler() {
			@Override
			public void consume(List list) throws Exception {
				if(list!=null){
					list.forEach( a ->{
						System.out.println("开始消费数据了"+a);

					});
				}

			}
			@Override
			public Map<String, List> sort(ConsumerRecords consumerRecords) {
				System.out.println("开始消费数据的排序操作"+consumerRecords);
				List list = new ArrayList();
				list.add(consumerRecords);
				Map map = new HashMap<>();
				map.put("consumerRecords",list);
				//consumerRecords.
				return map;
			}
		});
		defaultConsumer.consumer();
	}

	@Test
	public void testProductKafka(){
		String groupId = "testClient";
		try {
			Properties props = new Properties();
			props.put("bootstrap.servers", bootstrapServers());
			props.put("acks", "all");
			props.put("retries", 10_000);
			props.put("max.in.flight.requests.per.connection", 1);
			props.put("key.serializer", StringSerializer.class.getName());
			props.put("value.serializer", StringSerializer.class.getName());
			String topic = "prod_client";
			DefaultProducerFactory factory = new DefaultProducerFactory();
			factory.setProperties(props);
			DefaultProducer producer = new DefaultProducer(topic,factory);
			producer.sendMessage("发送数据了");
			System.out.println("发送数据了");
		} catch (MsxfKafkaException e) {
			e.printStackTrace();
			System.out.println("发送数据失败"+e);
		} finally {
		}
	}

	@Test
	public void testZookeeper(){
		try {
//			ZooKeeper zk = new ZooKeeper("localhost:2181",
//                    3000, new Watcher() {
//                // 监控所有被触发的事件
//                public void process(WatchedEvent event) {
//                    // TODO Auto-generated method stub
//                    System.out.println("已经触发了" + event.getType() + "事件！");
//                }
//            });
           String nodeName = "/applications/gg/gcory/pp";
			ZkClient  zkClient = new ZkClient("localhost:2181");
			zkClient.subscribeDataChanges(nodeName, new IZkDataListener() {
				@Override
				public void handleDataChange(String s, Object o) throws Exception {
					System.out.println("node data changed!");
					System.out.println("node=>" + s);
					System.out.println("data=>" + o);
					System.out.println("--------------");
				}

				@Override
				public void handleDataDeleted(String s) throws Exception {
					System.out.println("node data deleted!");
					System.out.println("s=>" + s);
					System.out.println("--------------");

				}
			});

			List<String> nodes = zkClient.getChildren("/applications/gg/gcory");
			nodes.forEach(p ->{
				System.out.println("nodes:"+p.toString());
				List<String> childrens = zkClient.getChildren("/applications/gg/gcory"+"/"+p);
                if(childrens!=null){
					childrens.forEach(y->{
						System.out.println("childrens:"+p.toString()+","+y);
					});
				}
			});

			if (!zkClient.exists(nodeName)) {
				System.out.println("nodeName 不存在");
				zkClient.createPersistent(nodeName);
			}
//			zkClient.writeData(nodeName, "1");
//			zkClient.writeData(nodeName, "2");

			System.out.println("nodeName开始写数据");


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
       while(true){
		   try {
			   TimeUnit.SECONDS.sleep(5);
		   } catch (InterruptedException e) {
			   e.printStackTrace();
		   }
	   }
	}

	/**
	 * 测试节点数据的变化
	 */
	@Test
	public void testNodeCalue(){
		ZookeeperAccess zookeeperInstance = new ZookeeperAccessDefault("localhost:2181", "/applications/gg/gcory");
		zookeeperInstance.getChangingBytes("pp",this::setConfigBytes);

		while(true){
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void testPathCacheChanging(){
		ZookeeperAccess zookeeperInstance = new ZookeeperAccessDefault("localhost:2181", "/applications");
		zookeeperInstance.getPathCacheChangingBytes("/gg/gcory",this::setConfigBytes);

		while(true){
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	private void setConfigBytes(byte[] configBytes) {
		if (configBytes == null) {
			System.out.println("MqManager : MqConsumer.properties has been just changed to null !!!!");
			//LOGGER.info("MqManager : MqConsumer.properties has been just changed to null !!!!");
		} else {
			System.out.println("MqManager : MqConsumer.properties has been just changed");
		}
		ConfData confData =	bytesToConfData(configBytes);
	String dbUrl =	confData.strEx("dburl");
		System.out.println("dbUrl:"+dbUrl);
	}

	private static ConfData bytesToConfData(byte[] configBytes) {
		ConfData ret = new ConfData();
		if (configBytes != null) ret.readFromByteArray(configBytes);
		return ret;
	}

	protected String bootstrapServers() {
		return "localhost:9092";
	}
}
