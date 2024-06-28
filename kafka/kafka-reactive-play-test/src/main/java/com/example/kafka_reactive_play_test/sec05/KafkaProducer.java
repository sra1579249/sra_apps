package com.example.kafka_reactive_play_test.sec05;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

/*
    goal: to produce records along with headers
 */

public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    public static void main(String[] args) {
        var producerConfig = Map.<String, Object>of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class
        );

        var options = SenderOptions.<String, String>create(producerConfig);

        var flux = Flux.interval(Duration.ofMillis(500))
                .take(1000)
                .map(KafkaProducer::createSenderRecord);

        var sender = KafkaSender.create(options);
        sender.send(flux)
                .doOnNext(i -> log.info("topic: {}, key: {}", i.recordMetadata().topic(), i.recordMetadata().partition()))
                .subscribe();

    }

    private static SenderRecord<String, String, String> createSenderRecord(Long i){
        var producerRecord = new ProducerRecord<>("products",  i.toString(), "product-"+i);
        return SenderRecord.create(producerRecord, producerRecord.key());
    }
}
