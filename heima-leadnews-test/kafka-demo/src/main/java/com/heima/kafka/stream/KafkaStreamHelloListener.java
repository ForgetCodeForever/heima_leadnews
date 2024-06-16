package com.heima.kafka.stream;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Arrays;

@Configuration
@Slf4j
public class KafkaStreamHelloListener {

    @Bean
    public KStream<String,String> kStream(StreamsBuilder streamsBuilder){
        // 创建kstream对象，同时指定从那个topic中接收消息
        KStream<String, String> stream = streamsBuilder.stream("kafkaStream-topic-input");
        /**
         * 处理消息的value
         */
        stream.flatMapValues(new ValueMapper<String, Iterable<String>>() {
                    @Override
                    public Iterable<String> apply(String value) {
                        return Arrays.asList(value.split(" "));
                    }
                })
                // 按照value进行聚合处理
                .groupBy((key, value)->value)
                // 时间窗口
                .windowedBy(TimeWindows.of(Duration.ofSeconds(600)))
                // 统计单词的个数
                .count()
                // 转换为kStream
                .toStream()
                .map((key,value)->{
                    System.out.println("key:" + key + ", value:" + value);
                    return new KeyValue<>(key.key().toString(), value.toString());
                })
                //发送消息
                .to("kafkaStream-topic-out");
        return stream;
    }
}