package cc.hdp.hdp.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/11/28 0028.
  */
object SWordCount {
  def main(args: Array[String]): Unit = {
    //spark入口
    //spark conf配置对象
    val conf = new SparkConf().setAppName("SWordCount").setMaster("local")

    val sc = new SparkContext(conf) //需要参数，即spark 配置对象

    //RDD
    //统计hdfs文件中单词的次数  /user/root/spark/file_in/README.md
    val file: RDD[String] = sc.textFile("F:\\vm_linux\\bigdata\\vmware.log").cache() //parm:路径,分区数  ctr+alt+v  返回值

    //假设统计行数
//    file.flatMap(line => line.split("\n"))   //flatMap   T --> Set(T),改变的是元素的数量，不改变元素类型
    //统计单词个数
    val flatMap: RDD[String] = file.flatMap(line => line.split(" "))
    //#   -->(#,1)
    //apache ->(apache,1)
    //spark
    //#   -->(#,1)
    val map: RDD[(String, Int)] = flatMap.map(word => (word,1))
    //(#,1,1)
//    val reduceByKey: RDD[(String, Int)] = map.reduceByKey(_ + _) //_表示集合中的元素
    //上面的操作都是属于转换，通过一个RDD产生一个新的RDD

    //action
//    reduceByKey.foreach( wordCount => println(wordCount))
//    reduceByKey.saveAsTextFile("/user/root/spark/file_out/SWordCount")
      val numAs = file.filter(line => line.contains("apache")).count()
      val numBs = file.filter(line => line.contains("spark")).count()

      println("Lines with apache: %s, Lines with spark: %s".format(numAs, numBs))

//    sc.stop()
  }
}
