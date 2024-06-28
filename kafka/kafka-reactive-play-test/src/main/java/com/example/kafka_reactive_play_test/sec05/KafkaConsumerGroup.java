package com.example.kafka_reactive_play_test.sec05;

public class KafkaConsumerGroup {
    private static class Consumer1{
        public static void main(String[] args) {
            KafkaConsumer.start("1");
        }
    }

    private static class Consumer2{
        public static void main(String[] args) {
            KafkaConsumer.start("2");
        }
    }

    private static class Consumer3{
        public static void main(String[] args) {
            KafkaConsumer.start("3");
        }
    }

}
