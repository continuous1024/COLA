package com.huan.bigdata.run.rdd

import org.apache.spark.{SparkConf, SparkContext, TaskContext}
import org.springframework.boot.{ApplicationArguments, ApplicationRunner}
import org.springframework.stereotype.Component

@Component
class SparkRDDTRF2 extends ApplicationRunner {
    override def run(args: ApplicationArguments): Unit = {
        try {
            //1:初始化SparkContext
            val conf = new SparkConf().setAppName("SparkRDDTRF").setMaster("local")
            val sc = new SparkContext(conf)
            //      var list = List("apple", "bag bag cat", "cat cat");


            val basePath = "./src/main/resources/"

            val rdd  = sc.textFile(basePath+"simple",2)
            //2:初始数据到RDD,参数为2个分区
            //   val rdd: RDD[String] = sc.parallelize(list, 2)

            //3： map操作返回每个元素经过func方法处理后所生成的新元素所组成的数据集合,foreach为Action操作
            /*  rdd.map(x => (x.toLowerCase)).foreach( x => println(x))  //x => x1
              rdd.map(x => (x,1)).foreach( x => println(x)) //x => (x1 ,1)
              rdd.map(x => x.split("-")).foreach( x => println(x.toList)) //x => list
              rdd.filter(x => x.contains("beijing")).foreach(x => println(x))
              rdd.flatMap(x => {
                x.split("-")
              }).foreach(x => println(x))*/
            rdd.glom().collect().foreach(x => println(x.mkString(",")))

            // rdd.flatMap(x=> {x.split(" ")}).glom().collect().foreach(x => println(x.mkString(",")))

            rdd.flatMap(x=> {x.split(" ")}).collect().foreach(x => println(x))
            rdd.flatMap(x=> {x.split(" ")}).map(x => (x,1)).foreach( x => println(x)) //x => (x1 ,1)

            rdd.flatMap(x=> {x.split(" ")}).filter(x => x!="bag").foreach(x => println(x))



            rdd.flatMap(x=> {x.split(" ")}).mapPartitions( x => {
                println("partitionId:"+TaskContext.getPartitionId())
                Iterator(x.length)}).collect().foreach(x => println(x))

            rdd.flatMap(x=> {x.split(" ")}).mapPartitionsWithIndex( (index,data) => {
                println("index:"+index)
                println("data:"+data)
                Iterator(index)
            }
            )
            rdd.flatMap(x=> {x.split(" ")}).sample(false,0.5,5)

            rdd.union(rdd).foreach(x => println(x))
            rdd.flatMap(x=> {x.split(" ")}).distinct().foreach(x => println(x))
            rdd.flatMap(x=> {x.split(" ")}).map(x => (x,1)).groupByKey().collect.foreach(x => println(x))


            val map=  rdd.flatMap(x=> {x.split(" ")}).map(x => (x,1))
            map.reduceByKey((pre, after) => pre + after).foreach(x => println(x))
            println("**************************sortByKey**************************")
            //  var sortResult = map.reduceByKey((pre, after) => pre + after).sortByKey().foreach(x => println(x))

            var reduceByKeyResult = map.reduceByKey((pre, after) => pre + after)
            reduceByKeyResult.join(reduceByKeyResult).foreach(x => println(x))

            reduceByKeyResult.cogroup(reduceByKeyResult).collect().foreach(x => println(x))

            println("**************************cartesian**************************")
            reduceByKeyResult.cartesian(reduceByKeyResult).collect().foreach(x => println(x))
            println(rdd.coalesce(1).collect().mkString(";"))
            rdd.repartition(3).foreachPartition(x=>{
                val id =  TaskContext.get.partitionId
                println("partitionNum:" + id)
                x.foreach( data => {
                    println(data)
                })
            })

            import org.apache.spark.storage.StorageLevel
            rdd.persist(StorageLevel.MEMORY_ONLY)

            import org.apache.spark.broadcast.Broadcast
            val broadcastVar: Broadcast[Array[Int]] = sc.broadcast(Array[Int](1, 2, 3))

            rdd.foreach(x=>{
                val  bc = broadcastVar.value
                val result = bc.mkString("-")+"-"+x
                println(result)
            })
            //5：停止SparkContext
            sc.stop()
        } catch {
            case e: Exception => {
                e.printStackTrace()
            }
        }
    }
}
