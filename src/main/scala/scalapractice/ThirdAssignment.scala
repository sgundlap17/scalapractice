package scalapractice

object ThirdAssignment {
  def main(args: Array[String]): Unit = {
    val list = List(10, 20, 30, 40)

    for(i<-list)
      {
        if(i>10)
          {
            println(i)
          }
      }
    def fil(x:Int):Boolean={
      var result:Boolean=false
      if(x>10)
        result=true
      result
    }
    for(i<-list)
    {
      if(fil(i)==true)
      {
        println(i)
      }
    }
    println("filtered elements:")
    list.filter((x: Int) => x > 10).map(println(_))
    list.filter(x => x > 10).map(println(_))
    list.filter(_ > 10).map(println(_))
  }
}

