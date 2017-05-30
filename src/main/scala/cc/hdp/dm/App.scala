package cc.hdp.dm

import java.io.{BufferedReader, FileNotFoundException, FileReader, IOException}

/**
  * Created by Administrator on 2016/12/4 0004.
  */
class Domain{
//  @throws(classOf[IOException])
}
object App {
  def main(args: Array[String]): Unit = {
//    val Unit1: Unit = 1 to 5 : _*
//    sayName("am")
//    println(sum(10))
//    println(fib(3))
//    sayName("Hello")
//    sayName("Dwyane","Wade")


//    sayName(lastName="Wade",firstName="Dwyane",midName="Martin")
//    sayName("Dwyane",midName="Martin")
//    println(sum2(1 to 5 :_*))


//    import scala.io.Source._
//    lazy val lines = fromFile("F:\\vm_linux\\bigdata\\vmware0.log").mkString
//    val split: Array[String] = lines.split("\n")
//    for(line <- split){
//      println(line)
//    }

//    val n = 99
//    // IO File
//    try{
//      val half = if (n%2 == 0) n/2 else throw new RuntimeException("N must be event")
//      // use File
//    }catch {
//      case e: Exception => e.printStackTrace()
//    }finally {
//      //colse io
//    }

    import scala.io.Source._
    try {
      val file = fromFile("F:\\vm_linux\\bigdata\\vmware.log")
    } catch {
      case ex: FileNotFoundException => println("Missing file exception")
      case ioex: IOException =>  println("IO Exception")
    }

    try {
      val fr = new FileReader("F:\\vm_linux\\bigdata\\vmware.log")
      var ch = 0
      var flag = true
      while ( flag ) {
        ch = fr.read()
        print(ch.toChar)
        if(ch == -1){
          flag = false
        }
      }
    } catch {
      case ex: FileNotFoundException => println("Missing file exception")
      case ex: IOException => println("IO Exception")
    }

  }

  def sum(num : Int) = {
    var sum = 0;
    for(i <- 1 to num)
      sum += i
    sum
  }

  def fib(n : Int) : Int = {
//    print(n+",")
    if(n <= 1) 1
    else fib(n-1) + fib(n-2)
  }

  def sayName(firstName:String,midName:String="Martin",lastName:String="Odersky"): Unit ={
    println(firstName + " " + midName + " " + lastName)
  }

  def sum(nums : Int*) : Int = {
    var sum = 0;
    for(num <- nums){
      sum += num
    }
    sum
  }
  def sum2(nums: Int*): Int = {
    if (nums.length == 0) 0
    else nums.head + sum2(nums.tail: _*)
  }

}
