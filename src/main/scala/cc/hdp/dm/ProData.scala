package cc.hdp.dm

import java.io.{BufferedWriter, File, FileWriter}

import sun.plugin.util.UIUtil

/**
  * Created by Administrator on 2017/3/25 0025.
  */
object ProData {
  def main(args: Array[String]): Unit = {
    val file = new File("G:\\data.txt")
    if(!file.exists()) file.createNewFile()
    //数据产生
    var flag = true
    var num = 0

    val writer = new BufferedWriter(new FileWriter(file))
    while(flag){
      val buffer = new StringBuffer()
      for(i <- 0 to 10){
        buffer.append("Hello ")
        for(i <- 0 to 8){
          buffer.append("Apache ")
        }
        for(i<- 0 to 3){
          buffer.append("Spark ")
        }
        buffer.append("! ")
      }
      buffer.append("\n")
      writer.write(buffer.toString)
      writer.flush()
      num +=1
      if(num == 50000000){
        flag = false
      }
    }
  }
}
