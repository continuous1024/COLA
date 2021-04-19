package com.huan.stream.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * https://kafka.apache.org/27/javadoc/index.html?org/apache/kafka/clients/producer/KafkaProducer.html
 *
 */
public class KafkaProducerTest {

    private Producer<String, String> producer;

    @Before
    public void initProducer() {
        System.out.println(System.getProperty("java.library.path"));
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<>(props);
    }

    @Test
    public void testSendMessage() {
        try {
            for (int i = 0; i < 10; i++)
                this.producer.send(new ProducerRecord<>("quickstart-events", Integer.toString(i), Integer.toString(i))).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @After
    public void destroyProducer() {
        if (this.producer != null) {
            this.producer.close();
        }
    }
}
