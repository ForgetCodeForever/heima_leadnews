package com.heima.kafka.sample;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 生产者
 */
public class ProducerQuickStart {

    public static void main(String[] args) {
        // 1.kafka的配置信息
        Properties properties = new Properties();
        // kafka的连接地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.200.128:9092");
        // 发送失败, 失败的重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 5);
        // 消息key的序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // 消息value的序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // ack配置  消息确认机制
        properties.put(ProducerConfig.ACKS_CONFIG, "all");

        // 消息压缩
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");  // lz4  gzip

        // 2.生产者对象
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);

        // 封装发送的消息
        // ProducerRecord<String,String> record = new ProducerRecord<String, String>("topic", "同步和异步组合提交偏移量", "hello kafka");
        for (int i = 0; i < 5; i++) {
            ProducerRecord<String,String> record = new ProducerRecord<String, String>("kafkaStream-topic-input", "hello kafkaSpringStream");
            producer.send(record);
        }

        // 3.发送消息
        // producer.send(record);

        // 同步消息发送
        /*try {
            RecordMetadata recordMetadata = producer.send(record).get();
            System.out.println(recordMetadata.offset());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/

        // 异步消息发送
//        producer.send(record, new Callback() {
//            @Override
//            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//                if(e != null){
//                    System.out.println("记录异常信息到日志表中");
//                }
//                System.out.println(recordMetadata.offset());
//            }
//        });

        // 4.关闭消息通道, 必须关闭, 否则消息发送不成功
        producer.close();
    }

}