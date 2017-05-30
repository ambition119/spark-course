package cc.hdp.bi

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/11/27 0027.
  */
object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Scala_WordCount")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("/user/root/spark/file_in/README.md")
    //根据空格切分单词
    val words = lines.flatMap( line => line.split(" "))
    val pairs = words.map(word =>(word,1))
    val count = pairs.reduceByKey( _ + _)

//    count.foreach( wordCount => println(wordCount._1 +":" + wordCount._2))
    //    count.saveAsTextFile("/user/root/spark/file_out/Scala_WordCount")

    val str = "/user/root/spark/file_out/Scala_WordCount"
    val path = new Path(str)
    val fs = path.getFileSystem(sc.hadoopConfiguration)
    fs.delete(path,true)
    count.saveAsTextFile(str)
    println(path.toString)

  }
}
