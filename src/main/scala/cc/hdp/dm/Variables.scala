package cc.hdp.dm

import org.apache.spark.rdd.RDD
import org.apache.spark.{AccumulatorParam, SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/3/12 0012.
  */
object VectorAccumulatorParam extends AccumulatorParam[Long] {
  //初始化方法
  def zero(initialValue: Long): Long = {
    0l
  }
  //累加方法
  def addInPlace(v1: Long, v2: Long): Long = {
    v1 + v2
  }
}
object Variables {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Variables").setMaster("local[2]")
    val sc = new SparkContext(conf)

//    val accum = sc.accumulator(0, "Accumulator")
    val myAccum = sc.accumulator(0l,"My Accumulator")(VectorAccumulatorParam)
    sc.parallelize(Array(1, 2, 3, 4)).foreach(x => myAccum += x)
    println(myAccum.value)

    Thread.sleep(60000)
  }
}
