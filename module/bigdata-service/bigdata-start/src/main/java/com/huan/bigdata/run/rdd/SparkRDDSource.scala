package com.huan.bigdata.run.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.springframework.boot.{ApplicationArguments, ApplicationRunner}
import org.springframework.stereotype.Component

//@Component
class SparkRDDSource extends ApplicationRunner{

    override def run(args: ApplicationArguments): Unit = {
        try {
            //1:初始化SparkContext
            val conf = new SparkConf().setAppName("SparkRDDSource").setMaster("local")
            val sc = new SparkContext(conf)
            val basePath = "./src/main/resources/"
            //2:加载数据到RDD
            //val rdd = sc.textFile(basePath+"people.txt") //load file
            // val rdd = sc.textFile(basePath+"subdata/") //load path
            // val rdd = sc.textFile(basePath+"subdata/*.txt") //load all txt file in path
            // val rdd = sc.textFile(basePath+"subdata/people.txt.gz") //load gz file[gzip data.txt]

            val hdfsSourcePath = "hdfs://127.0.0.1:9000/user/huanyu/input/people.json"

            val rdd = sc.textFile(hdfsSourcePath) //load hdfs file
            //3： transformations操作
            val filterResult =  rdd.filter( x => x.length>7)
            //4：执行collect操作(action操作)
            filterResult.collect().foreach( x => println(x) )
            //5：停止SparkContext
            sc.stop()
        } catch {
            case e: Exception => {
                e.printStackTrace()
            }
        }
    }
}
