package cc.hdp.hdp.am

import java.io.File

import scala.io.BufferedSource

/**
  * Created by Administrator on 2016/12/6 0006.
  */
object Fun {
  def main(args: Array[String]): Unit = {
//    sayName2("Kevin")
//    println(sum(3))
//    println(fib(3))

//    iterDir("F:\\vm_linux")

//    defaultName("Jemiy")
//    defaultName("Jemiy","Kevin","Dvide")
//    defaultName(midName="Divde",firstName="Kevin",lastName = "Jhon")
//    defaultName("Kevin",midName="Divde",lastName = "Jhon")
//    println(sum2(1,2,3))
//    println(1.to(3) )
    println(sum2(1 to 3 :_*))  //_*将参数变为序列化处理
  }

  //单语句块的函数
  def sayName(name:String): Unit = println("Hello " +name) //等同于sayName2
  def sayName2(name:String) = {
    println("Hello " +name)
  }   //函数处理逻辑比较复杂，对应语句就比较多

  def sum(num:Int) = {
    var result = 0
    if(num > 0){
      for(i <- 1 to num){
        result += i
      }
    }
    result
  }

  //递归函数
  def fib(num:Int): Int = {
    if (num <= 1) 1
    else fib(num - 1) + fib(num - 2)
  }

  //文件目录的遍历
  def iterDir(dirName:String):Unit = {
    val file = new File(dirName)
    if(file.isDirectory){
      val files = file.listFiles()
      files.foreach{
        file => iterDir(file.getAbsolutePath)
      }
    }else {
      println(dirName)
    }
  }

  //默认参数
  def defaultName(firstName:String,midName:String="Martin",lastName:String="Odersky"): Unit ={
    println(firstName+" " + midName+" "+lastName)
  }

  def sum2(nums:Int*): Int ={  //Seq[Int]
    var result = 0
    for(num <- nums){
      result += num
    }
    result
  }
}
