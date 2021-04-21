package com.huan.bigdata.run.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.springframework.boot.{ApplicationArguments, ApplicationRunner}
import org.springframework.stereotype.Component

@Component
class SparkRDDScalaDemo extends ApplicationRunner{
    override def run(args: ApplicationArguments): Unit = {
        try {
            //1:初始化SparkContext
            val conf = new SparkConf().setAppName("SparkRDDDemo").setMaster("local")
            val sc = new SparkContext(conf)
            var list = List("beijing", "beijing", "beijing", "shanghai", "shanghai", "tianjing", "tianjing");
            //2:初始数据到RDD
            val rdd: RDD[String] = sc.parallelize(list)
            //3.1:执行map计算(transformations操作)
            val mapResult = rdd.map((_, 1))
            // 3.2:执行reduceByKey计算(transformations操作)
            val reduceResult =  mapResult.reduceByKey((pre, after) => pre + after)
            //4：执行collect操作(action操作)
            reduceResult.collect().foreach( x => print(x) )
            // reduceResult.saveAsTextFile("/spark/out/put/path")
            //5：停止SparkContext
            sc.stop()
        } catch {
            case e: Exception => {
                e.printStackTrace()
            }
        }
    }
}
