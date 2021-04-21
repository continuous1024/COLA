//package com.huan.bigdata.run
//
//import org.apache.kafka.common.serialization.StringDeserializer
//import org.apache.spark.SparkConf
//import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
//import org.apache.spark.streaming.kafka010.KafkaUtils
//import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
//import org.apache.spark.streaming.{Seconds, StreamingContext}
//import org.springframework.boot.{ApplicationArguments, ApplicationRunner}
//import org.springframework.stereotype.Component
//
//import scala.util.parsing.json.JSON
//
///**
// * start zookeeper :cd /Users/huanyu/Documents/kafka/kafka_2.12-2.8.0 && bin/zookeeper-server-start.sh config/zookeeper.properties
//// * start kafka:cd /Users/huanyu/Documents/kafka/kafka_2.12-2.8.0 && nohup bin/kafka-server-start.sh config/server.properties &
// * create topic:./bin/kafka-topics.sh --create --zookeeper 127.0.0.1:2181  --replication-factor 1 --partitions 1 --topic spark-topic-1
// * send data to kafka:./bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic spark-topic-1
// * example data
// * {"name":"Justin", "age":19,"time":"2019-06-22 01:45:52.478","time1":"2019-06-22 02:45:52.478"}
// * *
// * cp /Users/wangleigis163.com/.m2/repository/org/apache/kafka/kafka-clients/2.4.1/kafka-clients-2.4.1.jar /Users/wangleigis163.com/Documents/alex/dev/evn/spark-3.0.0-bin-hadoop2.7/jars
// * cp /Users/wangleigis163.com/.m2/repository/org/apache/spark/spark-streaming_2.12/3.0.0/spark-streaming-kafka-0-10_2.12-3.0.0.jar /Users/wangleigis163.com/Documents/alex/dev/evn/spark-3.0.0-bin-hadoop2.7/jars
// * cp /Users/wangleigis163.com/.m2/repository/org/apache/spark/spark-token-provider-kafka-0-10_2.12/3.0.0/spark-token-provider-kafka-0-10_2.12-3.0.0.jar /Users/wangleigis163.com/Documents/alex/dev/evn/spark-3.0.0-bin-hadoop2.7/jars
// * cd $SPARK_HOME && nohup ./bin/spark-submit  --class "geekbang.quickstart.SparkStreamingDemo" --master  yarn --deploy-mode cluster /Users/wangleigis163.com/Documents/alex/dev/code/private/system-architecture/spark/target/spark-1.0.jar &
// * reduceByKey(_+_)是reduceByKey((x,y) => x+y)的一个 简洁的形式
// *  bin/hdfs dfs -rm -r /spark/etl/
// *   bin/hdfs dfs -mkdir /spark/etl/
// */
////@Component
//class SparkStreamingScalaRunner extends ApplicationRunner{
//    override def run(args: ApplicationArguments): Unit = {
//        try {
//            val kafkaParams = Map[String, Object](
//                "bootstrap.servers" -> "localhost:9092",
//                "key.deserializer" -> classOf[StringDeserializer],
//                "value.deserializer" -> classOf[StringDeserializer],
//                "group.id" -> "spark_stream_cg",
//                "auto.offset.reset" -> "latest",
//                "enable.auto.commit" -> (false: java.lang.Boolean)
//            )
//            val topics = Array("spark-topic-1", "spark-topic-2")
//            val conf = new SparkConf().setAppName("SparkStreamingDemo")
//                .setMaster("local")
//            //  .setMaster("yarn")
//
//
//            val streamingContext = new StreamingContext(conf, Seconds(30))
//            val checkPointDirectory = "hdfs://127.0.0.1:9000/user/huanyu/spark/checkpoint"
//            streamingContext.checkpoint(checkPointDirectory);
//            val stream = KafkaUtils.createDirectStream[String, String](
//                streamingContext,
//                PreferConsistent,
//                Subscribe[String, String](topics, kafkaParams)
//            )
//
//
//            val etlResultDirectory = "hdfs://127.0.0.1:9000/spark/etl/"
//            val etlRes = stream.map(record => (record.value())).filter(message =>  JSON.parseFull(message).isDefined)
//            etlRes.count().print()
//            etlRes.saveAsTextFiles(etlResultDirectory)
//
//            streamingContext.start()
//            streamingContext.awaitTermination()
//
//
//        } catch {
//            case ex: Exception => {
//                ex.printStackTrace() // 打印到标准err
//                System.err.println("exception===>: ...") // 打印到标准err
//            }
//
//        }
//    }
//}
