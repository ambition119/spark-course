package cc.hdp.hdp.am

/**
  * Created by Administrator on 2016/10/6 0006.
  */
object App {
  def main(args: Array[String]): Unit = {
    //输出语句，print,println,printf 字符串格式化输出
//    println("Hello")
//    println("World")
//    val name = "ambition"
//    val age = 24
//    printf("I'm %s,%d years old.",name,age)
//    val res = if(age > 18 ) true else false
//    println(res)

//    val a,b,c = 10;if(a > 0) {println(a);println(b);println(c)}

//    val a,b,c = 10
//    val res = if(a > 0) {
//      println(a)
//      println(b)
//      println(c)
//      a
//      b
//      c
//      a + b + c
//    }
//    println(res)

//    var a = 10
//    while (a > 0){
//      println(a)
//      a -= 1
//    }
//    do {
//      println(a)
//      a -= 1
//    } while (a > 0)

//    for (i <- 1 to 10 ){
//      println(i)
//    }
    for (i <- 1 until 10){
//      println(i)
    }
    // to和until的区别，就是until是不包含上下的
//    println( 1 to 10)

    //for生成集合
//    println( for(i <- 1 to 10) yield i )

    //使用boolean跳出循环
//    var flag = true
//    var x = 10
//    while (flag){
//      println(x)
//      x -= 1
//      if (x == 5) flag = false
//    }

    //使用Breaks跳出循环
    import  scala.util.control.Breaks._
    var res = 0
    breakable{
//      for(i <- (1,2,3,4,5,6)){
//        println("out put res :" + res)
//        res += 1
//        if(i == 5) break()
      }
//    }
  }
}
