package com.huan.bigdata.run

import org.apache.spark.{SparkConf, SparkContext}
import org.springframework.boot.{ApplicationArguments, ApplicationRunner}
import org.springframework.stereotype.Component;

//@Component
class SparkScalaRunner extends ApplicationRunner {
    override def run(args: ApplicationArguments): Unit = {
        val logFile = "file:///Users/huanyu/Project/COLA/modules/README.md"
        val conf = new SparkConf().setMaster("local").setAppName("Scala Application")
        val sc = new SparkContext(conf)
        val logData = sc.textFile(logFile, 2).cache()
        val numAs = logData.filter(line => line.contains("a")).count()
        val numBs = logData.filter(line => line.contains("b")).count()
        println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
    }
}
