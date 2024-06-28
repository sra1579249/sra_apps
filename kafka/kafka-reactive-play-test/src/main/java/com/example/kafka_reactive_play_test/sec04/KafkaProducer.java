package com.example.kafka_reactive_play_test.sec04;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.nio.charset.StandardCharsets;
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

        var flux = Flux.range(1, 10)
                .map(KafkaProducer::createSenderRecord);

        var sender = KafkaSender.create(options);
        sender.send(flux)
                .doOnNext(i -> log.info("topic: {}, key: {}", i.recordMetadata().topic(), i.recordMetadata().partition()))
                .subscribe();

    }

    private static SenderRecord<String, String, String> createSenderRecord(Integer i){
        var headers = new RecordHeaders();
        headers.add("client-id", "some-client-id".getBytes(StandardCharsets.UTF_8));
        headers.add("tracking-id","123".getBytes(StandardCharsets.UTF_8));
        var producerRecord = new ProducerRecord<>("order-events", null, i.toString(), "order-events-"+i, headers);
        return SenderRecord.create(producerRecord, producerRecord.key());
    }
}
