package com.huan.bigdata.run.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.springframework.boot.{ApplicationArguments, ApplicationRunner}
import org.springframework.stereotype.Component

@Component
class SparkRDDTRF extends ApplicationRunner {
    override def run(args: ApplicationArguments): Unit = {
        try {
            //1:初始化SparkContext
            val conf = new SparkConf().setAppName("SparkRDDTRF").setMaster("local")
            val sc = new SparkContext(conf)
            val basePath = "./src/main/resources/"
            //2:加载数据到RDD
            val hdfsSourcePath = "hdfs://127.0.0.1:9000/user/huanyu/input/people.json"
            val lines = sc.textFile(hdfsSourcePath) //load hdfs file
            //3： transformations操作


            /*  def getLength(s: String): Int = {
                s.length
              }

              def myReduce(a: Int, b: Int): Int = {
                return a + b;
              }*/

            class MyClass {
                def getLength(s: String): Int = {
                    s.length
                }
                def myReduce(a: Int, b: Int): Int = {
                    return a + b;
                }
            }

            // val lineLengths = lines.map((s: String) => s.length)
            //val lineLengths = lines.map(x => getLength(x))
            val lineLengths = lines.map(x => new MyClass().getLength(x))

            //val totalLength  = lineLengths.reduce((x,y) => x + y)
            //  val totalLength = lineLengths.reduce((x, y) => myReduce(x, y))
            val totalLength = lineLengths.reduce((x, y) => new MyClass().myReduce(x, y))

            //4：执行collect操作(action操作)
            System.out.println("[ spark map reduce operation： ] count is:" + totalLength)
            //5：停止SparkContext
            sc.stop()
        } catch {
            case e: Exception => {
                e.printStackTrace()
            }
        }
    }
}
