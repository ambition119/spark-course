package cc.hdp.dm

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.Map

case class Person(name:String,age:Int) extends Ordered[Person]{
  override def compare(that: Person): Int = {
    if(this.name == that.name)
      1
    else
      -1
  }
}

/**
  * Created by Administrator on 2017/2/18 0018.
  */
object RddOp {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("RddOp").setMaster("local")
    val sc = new SparkContext(conf)

//    transformations 操作
    val nums = Array(1,2,3,4)
    val names = Array("Kevin","Jhon","Lily","Lucy")

    val numRdd = sc.parallelize(nums)
    val nameRdd = sc.parallelize(names)

    //map值改变元素值
    val rdd: RDD[Int] = numRdd.map(x => x * 2)
//    rdd.foreach(println(_))
    //map,改变元素类型
    val mapRdd: RDD[(String, Int)] = nameRdd.map(x => (x,1))

    val tuples: Array[(String, Int)] = mapRdd.collect()
//    for(ele <- tuples) println(ele)


    val words = Array("Hello Kevin","I'm Jhon","Nice meeting you")
    val wordRdd: RDD[String] = sc.parallelize(words)

    //flatMap，拆分单词
    val flatMapRDD: RDD[String] = wordRdd.flatMap(x => x.split(" "))
//    flatMapRDD.foreach(println)

    //filter,过滤偶数
    val filterRDD: RDD[Int] = numRdd.filter(num => num % 2 == 0)
    filterRDD.foreach(println)
    //过滤包含有m字符的单词
//    wordRdd.filter(x => x.contains("m")).foreach(println)

    //PairRDD
    val scores = Array(("Kevin",60),("Lily",70),("Lily",80),("Kevin",90))
    val scoreRDD: RDD[(String, Int)] = sc.parallelize(scores)

    val groupByKeyRDD: RDD[(String, Iterable[Int])] = scoreRDD.groupByKey()
//    groupByKeyRDD.foreach(println)
//    CompactBuffer 类似 ArrayBuffer，只能append-only,通常是有groupBy操作产生

    //对成绩聚合
//    scoreRDD.reduceByKey(_ + _ ).foreach(println)
//    scoreRDD.groupByKey().map(x =>(x._1,x._2.sum)).foreach(println)

    //reduceByKey比较高效，因为它会在map端，先进行本地combine，可以大大减少要传输到reduce端的数据量，减小网络传输的开销。
   // reduceByKey处理不了时，才用groupByKey().map()来替代。

    //按照名字排序
    scoreRDD.sortByKey(false).foreach(println)

    //对象比较
    val persons = Array(new Person("Kevin",20)
                        ,new Person("Lily",22)
                   )
    val personRdd: RDD[Person] = sc.parallelize(persons)
    val personRddMap: RDD[(Person, Int)] = personRdd.map(x => (x,1))
    personRddMap.sortByKey().foreach(println)


    //join
    val info = Array(("Kevin",20),("Lily",22))
    val infoRdd: RDD[(String, Int)] = sc.parallelize(info)

    //笛卡尔积
    val joinRdd: RDD[(String, (Int, Int))] = infoRdd.join(scoreRDD)
    joinRdd.foreach(println)
    //处理
    infoRdd.join(scoreRDD.groupByKey()).foreach(println)
//    infoRdd.join(scoreRDD.groupByKey()).map(x => (x._1,x._2._1,x._2._2.head,x._2._2.tail.head)).foreach(println)

    val cogroupRdd: RDD[(String, (Iterable[Int], Iterable[Int]))] = infoRdd.cogroup(scoreRDD)
    cogroupRdd.foreach(println)
    val address = Array(("Kevin","gz"),("Lily","sz"))
    val addressRdd: RDD[(String, String)] = sc.parallelize(address)

    val sports = Array(("Kevin","play"),("Lily","eat"))
    val sportRdd: RDD[(String, String)] = sc.parallelize(sports)

    val cogroup: RDD[(String, (Iterable[Int], Iterable[Int], Iterable[String]))] = infoRdd.cogroup(scoreRDD,addressRdd)
    cogroup.foreach(println)
    infoRdd.cogroup(scoreRDD,addressRdd,sportRdd).foreach(println)


//    actions操作
//    reduce,聚合
    val reduce: Int = numRdd.reduce(_ + _ )
    println(reduce)

    //collect
    val collect: Array[Int] = numRdd.collect()
    for(ele <- collect) println(ele)

    //take , TopN
    val take: Array[Int] = numRdd.take(3)
    for(i <- 0 until take.length ) println( take(i))

    // first
    val first: Int = numRdd.first()
    println(first)

    //count
    val count: Long = numRdd.count()
    println(count)

    //saveAsTextFile
//    numRdd.saveAsTextFile("file:\\F:\\demo")

    //countByKey
    val key: Map[String, Long] = scoreRDD.countByKey()
    for(ele <- key) println(ele)

    //foreach
    numRdd.foreach(println)

  }
}
