package com.huan.bigdata.run.rdd;

import org.apache.spark.{HashPartitioner, RangePartitioner, SparkConf, SparkContext, TaskContext}
import org.springframework.boot.{ApplicationArguments, ApplicationRunner}
import org.springframework.stereotype.Component;

//@Component
class SparkPartitioner extends ApplicationRunner {
    override def run(args: ApplicationArguments): Unit = {
        //1:初始化SparkContext
        val conf=new SparkConf().setAppName("partitioner").setMaster("local")
        val sc=new SparkContext(conf)
        val list = List("1","2","3","4","5","6");
        //2:初始数据到RDD，执行map计算,构造(key,value)数据1->(1,value_1)
        val rdd = sc.parallelize(list).map(x=>(x,"value_"+x))

        rdd.foreachPartition(t => {
            val id =  TaskContext.get.partitionId
            println("base partitionNum:" + id)
            t.foreach( data => {
                println(data)
            })
        })

        //3:并对数据进行HashPartitioner分区
        rdd.partitionBy(new HashPartitioner(3)).foreachPartition(t => {
            val id =  TaskContext.get.partitionId
            println("HashPartitioner partitionNum:" + id)
            t.foreach( data => {
                println(data)
            })
        })


        //4:并对数据进行HashPartitioner分区
        rdd.partitionBy(new RangePartitioner(3,rdd,true,3)).foreachPartition(t => {
            val id =  TaskContext.get.partitionId
            println("RangePartitioner partitionNum:" + id)
            t.foreach( data => {
                println(data)
            })
        })

        //4：停止SparkContext
        sc.stop()
    }
}
