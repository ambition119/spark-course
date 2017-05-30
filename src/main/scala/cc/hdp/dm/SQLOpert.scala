package cc.hdp.dm

import java.util.Properties

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/4/24 0024.
  */
object SQLOpert {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SQLOpert").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    //json文件
//    val df = sqlContext.read.json("file:\\G:\\code\\source_code\\spark\\examples\\src\\main\\resources\\people.json")
//    val df = sqlContext.read.parquet("file:\\G:\\code\\source_code\\spark\\examples\\src\\main\\resources\\users.parquet")
    val props = new Properties()
    props.put("user","root")
    props.put("password","123456")
    val df = sqlContext.read.jdbc("jdbc:mysql://hdp1:3306/spark?useSSL=true","student",props)
    df.printSchema()
//    df.registerTempTable("users")
//    var frame = sqlContext.sql("select * from users")
//    frame.printSchema()

  }
}
