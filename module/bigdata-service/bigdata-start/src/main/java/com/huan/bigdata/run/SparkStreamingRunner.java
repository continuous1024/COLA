//package com.huan.bigdata.run;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.spark.SparkConf;
//import org.apache.spark.streaming.Durations;
//import org.apache.spark.streaming.api.java.JavaDStream;
//import org.apache.spark.streaming.api.java.JavaInputDStream;
//import org.apache.spark.streaming.api.java.JavaStreamingContext;
//import org.apache.spark.streaming.kafka010.ConsumerStrategies;
//import org.apache.spark.streaming.kafka010.KafkaUtils;
//import org.apache.spark.streaming.kafka010.LocationStrategies;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//import scala.util.parsing.json.JSON;
//
//import java.util.*;
//
////@Component
//public class SparkStreamingRunner implements ApplicationRunner {
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        String topics = "spark-topic-1";
//        String brokers = "localhost:9092";
//        String groupId = "spark_stream_cg";
//        Set<String> topicsSet = new HashSet<>(Arrays.asList(topics.split(",")));
//        Map<String, Object> kafkaParams = new HashMap<>();
//        kafkaParams.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
//        kafkaParams.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        kafkaParams.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        kafkaParams.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//        SparkConf sparkConf = new SparkConf().setAppName("SparkStreamingRunner").setMaster("local");
//        JavaStreamingContext streamingContext = new JavaStreamingContext(sparkConf, Durations.seconds(30));
//        String checkPointDirectory = "hdfs://127.0.0.1:9000/user/huanyu/spark/checkpoint";
//        streamingContext.checkpoint(checkPointDirectory);
//
//        // Create direct kafka stream with brokers and topics
//        JavaInputDStream<ConsumerRecord<String, String>> messages = KafkaUtils.createDirectStream(
//            streamingContext,
//            LocationStrategies.PreferConsistent(),
//            ConsumerStrategies.Subscribe(topicsSet, kafkaParams));
//
//        String etlResultDirectory = "hdfs://127.0.0.1:9000/spark/etl/";
//        JavaDStream<String> etlRes = messages.map(ConsumerRecord::value).filter(message ->  JSON.parseFull(message).isDefined());
//        etlRes.count().print();
//
//        streamingContext.start();
//        streamingContext.awaitTermination();
//
//    }
//}
