package cc.hdp.dm

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/2/26 0026.
  */
object CaRdd {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("CaRdd").setMaster("local")
    val sc = new SparkContext(conf)

//    val rdd: RDD[String] = sc.textFile("file:\\G:\\视频\\搜狗\\user_tag_query.2W.TRAIN").cache()
val rdd: RDD[String] = sc.textFile("file:\\G:\\视频\\搜狗\\user_tag_query.2W.TRAIN").persist(StorageLevel.MEMORY_ONLY_SER)

    //开始时间
    val start: Long = System.currentTimeMillis()
    //统计rdd的数量
    val count: Long = rdd.count()
    //结束时间
    val end = System.currentTimeMillis()
    println("第一次花费时间：" + (end -start))

    //第二次count
    val start2 = System.currentTimeMillis()
    val count2 = rdd.count()
    val end2 = System.currentTimeMillis()

    println("第二次花费时间：" + (end2 -start2))


    //休眠
    Thread.sleep(100000)







  }
}
