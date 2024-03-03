package lecture.part1basics

object Functions extends App {
  def aFunction(a: String, b: Int): String = a + " " + b
  
  def f: Int = {
    val a = 10
    val b = 100
    100
    a + b
    100
  }

  println(aFunction("Hello", 9))

  def aParameterLessFunction: Int = 42

  println(aParameterLessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if(n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }

  println(aRepeatedFunction("Hello", 3))

  // When we need loops, use recursion.

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  aFunctionWithSideEffects("Hello World! ")

  def aBigFunction(n: Int): Int = {
    def anAuxiliaryFunction(a: Int, b: Int): Int = a + b

    anAuxiliaryFunction(n, n-1)
  }

  println(aBigFunction(10))
}
