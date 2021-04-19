package com.huan.bigdata.run

import org.apache.spark.SparkConf
import org.apache.spark.api.java.JavaSparkContext
import org.springframework.boot.{ApplicationArguments, ApplicationRunner}
import org.springframework.stereotype.Component
;

@Component
class SparkJobStageSplitRunner extends ApplicationRunner {
    override def run(args: ApplicationArguments): Unit = {
        try {
            //1：初始化JavaSparkContext
            val conf: SparkConf = new SparkConf().setAppName("SparkJobStageSplitDemo").setMaster("local")
            val sc = new JavaSparkContext(conf)
            //2：构造RDD并执行reduce计算
            val list=sc.parallelize(List(('a',1),('a',2),('b',3),('b',4)))
            list.filter( data_tuple=> data_tuple._2<=3 )//1:filter操作为ShuffleMapStage
                .reduceByKey((x,y) => x+y)//2:reduceByKeyShuffle操作，以此为界限划分出stage，即ResultStage
//                .saveAsTextFile("out/put/path1")//saveAsTextFile为action操作，划分出第一个job
                .collect().foreach(x=>print(x))
            list.filter( data_tuple=> data_tuple._2>=2 )//1:仅有的filter操作为ResultStage
//                .saveAsTextFile("out/put/path2")//saveAsTextFile为action操作，划分出第二个job
                .collect().foreach(x=>print(x))
            Thread.sleep(10000000)


        } catch {
            case ex: Exception => {
                ex.printStackTrace() // 打印到标准err
                System.err.println("exception===>: ...") // 打印到标准err
            }
        }
    }
}
