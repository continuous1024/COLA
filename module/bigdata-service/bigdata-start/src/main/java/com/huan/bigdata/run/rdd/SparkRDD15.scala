package com.huan.bigdata.run.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.broadcast.Broadcast

object SparkRDD15 {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setAppName("SparkRDD15").setMaster("local")
        val sc = new SparkContext(conf)
        val basePath = "./src/main/resources/"
        /*
            //1：transformations操作，不会立刻执行
            val lines = sc.textFile(basePath+"simple")
            //2:transformations操作,map操作不会立刻执行
           val  linesLength = lines.map(x=>x.length)
           //3:reduce是Action操作，会触发执行
            val totaLength  = linesLength.reduce((x,y)=>x+y)
            println(totaLength)
        */
        //1:通过Lambda方式定义函数
        // val lines = sc.textFile(basePath + "simple")
        //    val linesLength = lines.map(x => x.length)
        //    linesLength.foreach(x=>println(x))
        //

        //2：自定义Function
        //    def getLength(s: String): Int = {
        //      s.length
        //    }
        //
        //    def myReduce(a: Int, b: Int): Int = {
        //      a + b;
        //    }
        //
        //    val linesLength = lines.map(x => getLength(x))
        //    val totaLength = linesLength.reduce((x, y) => myReduce(x, y))
        //    println(totaLength)
        //3：自定义类中实现Spark Function
        /*   class MyClass {
             def getLength(s: String): Int = {
               s.length
             }
             def myReduce(a: Int, b: Int): Int = {
               a + b;
             }
           }
           val linesLength = lines.map(x => new MyClass().getLength(x))
           val totaLength = linesLength.reduce((x, y) => new MyClass().myReduce(x, y))
           println(totaLength)*/


        //1：数据加载
        /*    val lines = sc.textFile(basePath + "simple",2)
            lines.foreachPartition(t=>{
              val id = TaskContext.getPartitionId()
              println("partitionNum:"+id)
              t.foreach(data=>println(data))
            })
            */

        val rdd = sc.textFile(basePath + "simple",2)
        //rdd.flatMap(x=>x.split(" ")).map(x=>(x,1)).foreach(x=>println(x))
        //rdd.flatMap(x=>x.split(" ")).filter(x=>x!="bag").foreach(x=>println(x))
        rdd.flatMap(x=>x.split(" ")).foreach(x=>println(x))
        //1：定义Broadcast
        val broadcastVar: Broadcast[Array[Int]] = sc.broadcast(Array[Int](1, 2, 3))
        //2:将Broadcast的数据进行广播
        sc.broadcast(broadcastVar)
        rdd.foreach(x=>{
            //3：获取Broadcast的数据，该数据将在每个节点上进行共享
            val bc = broadcastVar.value
            //1;2;3-x
            val result = bc.mkString(";")+"-"+x
            println(result)

        })
    }
}

