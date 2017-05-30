package cc.hdp.dm

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}
import org.apache.log4j.{Level, Logger}
import scala.util.Random

/**
  * Created by Administrator on 2016/11/28 0028.
  */
object SWordCount {
  def main(args: Array[String]) = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    //spark入口
    //spark conf配置对象
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[6]")
    val sc = new SparkContext(conf)

    //RDD
    val file: RDD[String] = sc.textFile("file:\\G:\\data.txt",6)

    val flatMap: RDD[String] = file.flatMap(line => line.split(" "))

    //自定义Partitioner
//    val map: RDD[(String, Long)] = flatMap.map(word => (word,1l))
//    val byKey = map.reduceByKey(_ + _).sortByKey(false,2).groupByKey(new Partitioner {override def numPartitions: Int = 48
//
//      override def getPartition(key: Any): Int = key.hashCode() % 6
//    })
//    byKey.foreach( wordCount => println(wordCount))

    //添加随机数
    val map: RDD[(String, Long)] = flatMap.map(word => ((new Random().nextInt(6))+"_"+word,1l))
    var randomRdd: RDD[(String, Long)] = map.reduceByKey(_ + _)
    randomRdd.foreach(wordCount => println(wordCount))
    var keyRdd = randomRdd.map(word => (word._1.split("_")(1),word._2))
    var key = keyRdd.reduceByKey(_ + _).sortByKey(false,2).groupByKey()
    key.foreach(wordCount => println(wordCount))


//    Thread.sleep(60000)
    //    sc.stop()
  }
}
