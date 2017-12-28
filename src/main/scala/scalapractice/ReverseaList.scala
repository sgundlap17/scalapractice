package scalapractice

object ReverseaList {

    def main(args: Array[String]): Unit ={

      val list = List range (1,11)
      //Reverse a List Using FoldLeft
      val revFold = reverse(list)
      println("The reverse of A list Using FoldLeft: "+revFold)
      val parttions = list partition(_ %2 ==0)
      println("The PArtitioned example: "+parttions)
      println("The TakeWhile Example: "+list.takeWhile(_>5))
      println("The FoldLeft Example: "+list.foldLeft(0)(_+_))
      // println("The FoldRight Example: "+list.foldRight(0)(_/_))
      //println("The reduce Examples: "+list.reduceLeft(_%) + "and" +list.reduceRight(_%))
      // println("The Scan Examples: "+list.scan(0)(_+) + "and" +list.scanRight(0)(_+))
      println("What span will give you: "+list.span(_>5))
      println("What Sliding WIll give You: "+list.sliding(3,3).foreach(println))
      println("What GropBy do For us: "+list.groupBy(_>5))
      //Let Us Explore Array From here
      val num = new Array[Int](5)
      println("Is num is empty: "+num.foreach(println))
      num.update(3,420)
      println("Is array updated: "+num.foreach(println))



    }


    def reverse[A](list: List[A]): List[A] =
      list.foldLeft(List[A]())((r,c) => c :: r)
}
