package com.example.kafka_reactive_play_test.sec03;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.util.Map;

/*
    goal: to demo back pressure using max in flight for a reactive producer.
    producer could be a confusing term & it depends on the context.
 */

public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    public static void main(String[] args) {
        var producerConfig = Map.<String, Object>of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class
        );

        var options = SenderOptions.<String, String>create(producerConfig).maxInFlight(10000);

        var flux = Flux.range(1, 1_000_000)
                .map(i -> new ProducerRecord<>("order-events", i.toString(), "order-events-"+i))
                .map(pr -> SenderRecord.create(pr, pr.key()));

        var startTime = System.currentTimeMillis();

        var sender = KafkaSender.create(options);
        sender.send(flux)
                .doOnNext(i -> log.info("topic: {}, key: {}", i.recordMetadata().topic(), i.recordMetadata().partition()))
                .doOnComplete(() -> log.info("time duration: {}", System.currentTimeMillis() - startTime))
                .subscribe();

    }
}
