package scalapractice

import scala.math._
object FirstAssignment {
  def main(args:Array[String]):Unit=
  {

val a=List("spark","scala","training")
  a.foreach(println)
    println("second element in the list:" + a(1))
    val b=a.count(x=>x.length==5)
    println("count of  element:" +b)
    println("list without first element:" +a.drop(1))
    println("drop right 2 elements:"+a.dropRight(2))
    println("Check whether 'spark' exist in list:"+a.contains("spark"))
    println("print a list that have 4 letter word:"+ a.filter(x=>x.length==4))
    println("print a list that have 5 letter word:"+ a.filter(x=>x.length==5))
    println("first element of list:" +a.head)
    println("except last element of list:" +a.init)
    println("identify element of list is empty or not:" +a.isEmpty)
    println("return last element of list:"+a.last)
    val c=a.map(x=>x+""+"easy")
    println("concatenate easy to all words:"+c)
    println("square root of 3:"+3 - pow(sqrt(3), 2))
}}
