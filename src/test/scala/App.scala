/**
  * Created by Administrator on 2016/10/6 0006.
  */
object App {
  def main(args: Array[String]): Unit = {
    import  scala.util.control.Breaks._
    var res = 0
    breakable{
      for(i <- 0 until 10){
        println("out put res :" + res)
        res += 1
        if(i == 5) break()
      }
    }


    //
//    var res = 0
//    var flag = true
//    while (flag){
//      println("out put res :" + res)
//      res += 1
//      if(res == 5) flag = false
//    }
//    var res = 0
//    for(x <- 1 to 10 ){
//      println("out put res :" + res)
//      res += 1
//      if(x == 5) return
//    }

//    while(x >= 0) {
//      println("out put x :" + x)
//      x -= 1
//      if(x == 5) return
//    }
//
//
//    do {
//      println("out put x :" + x)
//      x -= 1
//    } while (x >= 0)
//    println("out put end.")
//
//    val name = "ambition"
//    val age = 24
//    printf("Hello,I'm %s, %d years old.",name,age)

//    val s = for( i <- 1 to 10) yield i
//    println(s)
//    for (i <- 1 to 9; j <- 1 to 9) {
//      if(j == 9) {
//        println(i +" * " + j + " = " + (i * j) +"\t")
//      }else {
//        print(i +" * " + j  + " = " +  (i * j) +"\t")
//      }
//    }

    for(i <- 1 to 100 if i % 2 == 0) print(i + " ")

    val col = for(i <- 1 to 10) yield i
//    println( col )
  }
}
