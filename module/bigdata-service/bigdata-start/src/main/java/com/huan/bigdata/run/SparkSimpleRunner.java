package com.huan.bigdata.run;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//@Component
public class SparkSimpleRunner implements ApplicationRunner, Serializable {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String logFile = "file:///Users/huanyu/Project/COLA/modules/README.md"; // Should be some file on your system
        SparkConf conf = new SparkConf().setMaster("local").setAppName("SimpleApp");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> logData = sc.textFile(logFile).cache();
        long numAs = logData.filter((Function<String, Boolean>) s -> s.contains("a")).count();
        long numBs = logData.filter((Function<String, Boolean>) s -> s.contains("b")).count();
        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
    }
}
