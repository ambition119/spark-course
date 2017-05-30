package cc.hdp.hdp.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/2/9 0009.
  */
object CreateRdd {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .appName("DecisionTree mllib")
      .master("local[*]")
      .config("spark.sql.warehouse.dir", "file:///D:/code/spark_code/course/")
      .getOrCreate()
    val sc = sparkSession.sparkContext
    val sqlCtx = sparkSession.sqlContext
//    val conf = new SparkConf().setAppName("CreateRdd").setMaster("local")
//    val sc = new SparkContext(conf)

    //第一种：并行化集合创建RDD，主要是用来测试自己程序逻辑
    val data = Array(1, 2, 3, 4, 5)
    //接受第二个参数是分片数量
    val rdd: RDD[Int] = sc.parallelize(data)

    println(rdd.count())
  }
}
