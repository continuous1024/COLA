package com.huan.bigdata.run;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;

import static org.apache.spark.sql.functions.col;

// @Component
public class SparkBatchRunner implements ApplicationRunner {
    public static class Person implements Serializable {
        private String name;
        private long age;
    
        public String getName() {
          return name;
        }
    
        public void setName(String name) {
          this.name = name;
        }
    
        public long getAge() {
          return age;
        }
    
        public void setAge(long age) {
          this.age = age;
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String hdfsSourcePath = "hdfs://localhost:9000/user/huanyu/input/people.json";
        SparkSession spark = SparkSession
                                .builder()
                                .master("local")
                                .appName("SparkBatchRunner")
                                .getOrCreate();
        Dataset<Row> df = spark.read().json(hdfsSourcePath);
        df.show();
        df.printSchema();
        df.select("name").show();
        df.select(col("name"), col("age").plus(1)).show();
        df.filter(col("age").gt(21)).show();
    }
    
}
