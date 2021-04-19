package com.huan.bigdata.run

import org.apache.spark.sql.SparkSession
import org.springframework.boot.{ApplicationArguments, ApplicationRunner}
import org.springframework.stereotype.Component

/**
 * hdfs dfs -mkdir input
 * hdfs dfs -put src/main/resources/data/people.json input
 *
 * hdfs dfs -get /user/huanyu/input input
 *
 * hdfs dfs -ls /user/huanyu/input
 */
@Component
class SparkBatchRunner extends ApplicationRunner{
    override def run(args: ApplicationArguments): Unit = {
        try {

            //1:input data path
            val hdfsSourcePath = "hdfs://localhost:9000/user/huanyu/input/people.json"

            //2:create SparkSession
            val spark = SparkSession
                .builder()
                .appName("SparkBatchDemo")
                .master("local")
                //.master("yarn")
                // .config("spark.some.config.option", "some-value")
                .getOrCreate()
            //3:read data from hdfs
            val df = spark.read.json(hdfsSourcePath)
            //4.1: Displays the content of the DataFrame to stdout
            df.show()
            df.printSchema()
            df.select("name").show()
            //4.2 Select everybody, but increment the age by 1
            import spark.implicits._
            df.select($"name", $"age" + 1).show()

            df.filter($"age" > 21).show()

            df.groupBy("age").count().show()
            //5 write result to hdfs
            //  val hdfsTargetPath = "hdfs://127.0.0.1:9000/input/result/"
            //bin/hdfs dfs -rm -r /input/people_result.json
            // df.write.mode(SaveMode.Overwrite) json (hdfsTargetPath)

        } catch {
            case ex: Exception => {
                ex.printStackTrace() // 打印到标准err
                System.err.println("exception===>: ...") // 打印到标准err
            }
        }
    }
}
