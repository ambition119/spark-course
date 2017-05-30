package cc.hdp.course

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/2/26 0026.
  */
object RddPersist {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("RddPersist").setMaster("local")
    val sc = new SparkContext(conf)

    //创建Rdd,通过文本数据
    val rdd: RDD[String] = sc.textFile("file:\\G:\\视频\\搜狗\\大数据精准营销中搜狗用户画像挖掘\\user_tag_query.2W.TRAIN")

    //第一次
    val start =  System.currentTimeMillis()
    val count: Long = rdd.count()
    val end = System.currentTimeMillis()
    println("第一次count计算： "+ (end - start))

    //第二次计算
    val start2 =  System.currentTimeMillis()
    val count2: Long = rdd.count()
    val end2 = System.currentTimeMillis()
    println("第二次count计算： "+ (end2 - start2))


    Thread.sleep(60000)
  }
}
