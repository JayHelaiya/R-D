package com.jay.kafka;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ComsumerTest {

	public static void main(String[] args) {
	    Map<String, Object> props = new HashMap<String, Object>();
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9093,localhost:9094");
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "helloworld");
	    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	    
	    
	    List<KafkaBrokerInfo> brokerInfoList = new ArrayList<KafkaBrokerInfo>();
        brokerInfoList.add(new KafkaBrokerInfo("localhost", 9092));
        brokerInfoList.add(new KafkaBrokerInfo("localhost", 9093));
        brokerInfoList.add(new KafkaBrokerInfo("localhost", 9094));
        TopicMetaInfo test=new TopicMetaInfo();
	    
        String topic="office-employees";
	    KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
	    
		try {
			test.run(100, topic, 2, brokerInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	   // consumer.subscribe(Arrays.asList(topic));
	/*    while (true) {
	        ConsumerRecords<String, String> records = consumer.poll(5);
	        for (TopicPartition partition : records.partitions()) {
	            List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
	            for (ConsumerRecord<String, String> record : partitionRecords) {
	            	
	            	try {
						test.run(100, record.topic(), record.partition(), brokerInfoList);
					} catch (Exception e) {
						System.out.println(e);
					}
	            }
	            long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
	            consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
	        }
	        
	    }*/
	}
}