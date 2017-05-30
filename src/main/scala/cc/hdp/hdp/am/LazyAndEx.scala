package cc.hdp.hdp.am

import java.io.{FileNotFoundException, IOException}

import scala.io.{BufferedSource, Source}

/**
  * Created by Administrator on 2016/12/18 0018.
  */
object LazyAndEx {
  def main(args: Array[String]): Unit = {
    //lazy
//    val lines = Source.fromFile("F:\\vm_linux\\bigdata\\vmware0.log")  //lazy 限定val声明的变量
//   lazy val lines = Source.fromFile("F:\\vm_linux\\bigdata\\vmware0.log")  //lazy 限定val声明的变量
//   val strings: Array[String] = lines.mkString.split("\n")  //第一次调用
//   for(str <- strings){
//     println(str)
//   }

//    try{
//      val lines = Source.fromFile("F:\\vm_linux\\bigdata\\vmware0.log")  //java.io.FileNotFoundException
//    } catch {
//      case fex: FileNotFoundException => println("FileNotFoundException Exception happend")  //模式匹配
//      case ioex : IOException => println("IOException Exception happend")
//      case ex: Exception => println("Exception happend")   //捕获异常，使用模式匹配
//    }
//      val lines = Source.fromFile("F:\\vm_linux\\bigdata\\vmware0.log")  //java.io.FileNotFoundException
    var file :BufferedSource = null
    try{
      file = Source.fromFile("F:\\vm_linux\\bigdata\\vmware0.log") //java.io.FileNotFoundException
    } catch {
      case ex: FileNotFoundException => println("FileNotFoundException happend")   //捕获异常，使用模式匹配
    } finally {
      if(file != null) {
        file.close()
      }
    }

//    try{} catch {}
//    try{} catch{} finally {}
//    try{    try{} catch{} finally {}   } catch{} finally {}


  }
}
