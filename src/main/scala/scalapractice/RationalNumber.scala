package scalapractice

class RationalNumber(a:Int,b:Int) {
  val numerator= a;
  val denominator= b;
  def add (secondRationalNumber:RationalNumber):RationalNumber={
    new RationalNumber(numerator*secondRationalNumber.denominator+denominator*secondRationalNumber.numerator,
      denominator*secondRationalNumber.denominator)
  }
  def sub(secondRationalNumber:RationalNumber):RationalNumber= {
    new RationalNumber(numerator * secondRationalNumber.denominator - denominator * secondRationalNumber.numerator,
      denominator * secondRationalNumber.denominator)
  }
  def mul(secondRationalNumber:RationalNumber):RationalNumber= {
    new RationalNumber(numerator * secondRationalNumber.numerator, denominator * secondRationalNumber.denominator)

  }
   def div(secondRationalNumber:RationalNumber):RationalNumber= {
     new RationalNumber(numerator * secondRationalNumber.denominator, denominator * secondRationalNumber.numerator)
   }
override def toString():String=
{
  numerator+"/"+denominator
}
}
