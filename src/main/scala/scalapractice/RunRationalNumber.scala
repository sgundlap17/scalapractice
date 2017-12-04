package scalapractice

object RunRationalNumber {
  def main(args: Array[String]): Unit = {
  val firstRationalNumber=new RationalNumber(1,2)
  val secondRationalNumber=new RationalNumber(1,4)

  val resultRationalNumber1=firstRationalNumber.add(secondRationalNumber)
  val resultRationalNumber2=firstRationalNumber.sub(secondRationalNumber)
  val resultRationalNumber3=firstRationalNumber.mul(secondRationalNumber)
  val resultRationalNumber4=firstRationalNumber.div(secondRationalNumber)

  println("first Rational Number:"+firstRationalNumber)
  println("second Rational Number:"+secondRationalNumber)
  println("addition of Numbers:"+resultRationalNumber1)
  println("subtraction of Numbers:"+resultRationalNumber2)
  println("multiplication of Numbers:"+resultRationalNumber3)
  println("division of Numbers:"+resultRationalNumber4)

}
}

