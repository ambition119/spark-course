package cc.hdp.course

import org.apache.spark.{Accumulable, AccumulatorParam, SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/3/19 0019.
  */
object VectorAccumulatorParam extends AccumulatorParam[Long] {
  //初始化
  def zero(initialValue: Long): Long = {
    0
  }
  //累加方法
  def addInPlace(v1: Long, v2: Long): Long = {
    v1 + v2
  }
}

object Accumulators {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Accumulators").setMaster("local[4]")
    val sc = new SparkContext(conf)

//    val accum = sc.accumulator(0, "Accumulator")
//    sc.parallelize(Array(1, 2, 3, 4)).foreach(x => accum += x)
//    println(accum.value)

    //自定义累加器
    var accumulable: Accumulable[Long, Long] = sc.accumulable(0l,"My Accumulator")(VectorAccumulatorParam)
    sc.parallelize(Array(1, 2, 3, 4)).foreach(x => accumulable += x)
    println(accumulable.value)

    Thread.sleep(60000)
    sc.stop()
  }
}
