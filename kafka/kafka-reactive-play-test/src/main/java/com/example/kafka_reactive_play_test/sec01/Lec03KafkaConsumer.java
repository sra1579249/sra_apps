package com.example.kafka_reactive_play_test.sec01;

/*

goal: simple kafka consumer with multiple topics with reactor kafka

*/

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Lec03KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(Lec03KafkaConsumer.class);

    public static void main(String[] args) {
        var consumerConfig = Map.<String,Object>of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.GROUP_ID_CONFIG, "demo-group",
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest",
                ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, "1"
        );

        var receiverOptions = ReceiverOptions.create(consumerConfig)
//                                .subscription(List.of("orders","pay"));
                                    .subscription(Pattern.compile("orde.*"));

        KafkaReceiver.create(receiverOptions)
                .receive()
                .doOnNext(r -> log.info("topic: {},key: {}, value: {}", r.topic(), r.key(), r.value()))
                .doOnNext(r -> r.receiverOffset().acknowledge())
                .subscribe();
    }


}
