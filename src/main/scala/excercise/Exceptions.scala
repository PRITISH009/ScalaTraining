package excercise

object Exceptions extends App {
  val outOfMemoryError = new OutOfMemoryError("Program is Out of Memory")
  val stackOverflowError = new StackOverflowError("Stackoverflow error")

  // OOM
  // val array = Array.ofDim(Int.MAX_VALUE)

  // SO
//   def infinite: Int = 1 + infinite
//   val noLimit = infinite

  class OverFlowException extends RuntimeException("OverFlow Exception")
  class UnderFlowException extends RuntimeException("UnderFlow Exception")
  class DivisionByZeroException extends RuntimeException("Division by 0!!")

  object Calculator {
    def add(a: Int, b: Int): Int = {
      if(a > 0 && b > 0 && a + b < 0) then throw new OverFlowException else a + b
    }
    def subtract(a: Int, b: Int): Int = {
      if(a > 0 && b < 0 && a - b < 0) throw new OverFlowException
      else if(a < 0 && b > 0 && a - b > 0) throw new UnderFlowException
      else a - b
     }
    def multiply(a: Int, b: Int): Int = {
      if(((a > 0 && b > 0) || (a < 0 && b < 0)) && a * b < 0) throw new OverFlowException
      else if(((a > 0 && b < 0) || (a < 0 && b > 0)) && a * b > 0) throw new UnderFlowException
      else a * b
    }
    def divide(a: Int, b: Int): Int = if(b == 0) then throw new DivisionByZeroException else a / b
  }

  //println(Calculator.add(Int.MaxValue, 1))
  //println(Calculator.divide(2, 0))

}
