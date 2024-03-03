package lecture.part3fp

import scala.annotation.tailrec

object HOFsCurry extends App {

  // Nothing keeps us from defining such type of functions
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // Such function which either takes in a function as a parameter or returns a function is known as a HOF
  // Higher Order Function

  // map, filter, flatMap are all higher order function.

  // function that applies a function n times over a given value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)))

  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if(n <= 0) then x else nTimes(f, n - 1, f(x))

  println(nTimes(_ * 2, 1, 2))

  // Not Easy to Think About
  def nTimesBetter(f: Int => Int, n: Int): Int => Int =
    if (n <= 0) then (x: Int) => x else (x: Int) => nTimesBetter(f, n-1)(f(x))


  /**
   * nTimes(_ * 2, 3) = (x: Int) => nTimesBetter(_ * 2, 2)(f(x)) = f(f(f(x)))
   * nTimesBetter(_ * 2, 2) = nTimesBetter(_ * 2, 1)(f(x)) = f(f(x))
   * nTimesBetter(_ * 2, 1) = nTimesBetter(_ * 2, 0)(f(x)) = f(x)
   * nTimesBetter(_ * 2, 0) = (x: Int) => x
   *
   * lets assume this to g(x) = x
   */

  val eightX = nTimesBetter(_ * 2, 3) // f(x) = x * 2, this = f(f(f(x))) = x * 2 * 2 * 2 = 8x
  println(eightX(10))

  // Curried Functions
  val superAdder: Int => Int => Int = x => y => x + y // Parenthesis are implied by the compiler because type is declared
  val add3 = superAdder(3)

  println(add3(10))
  println(superAdder(3)(10)) // Can also be done like this -> with multiple parameter list.

  // Scala supports almost another kind of curried functions by supplying multiple parameter lists.
  def curriedFormatter(c: String)(x: Double): String = c.format(x)
  // we can define functions with multiple parameter lists to act like curried functions.

  val standardFormat = curriedFormatter("%4.2f")
  val preciseFormat: Double => String = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  // Basically
  // def func(x: Int): Int => Int = ??? can also be declared as
  // def func(x: Int)(y: Int): Int
  // But its not exactly the same... but has similar functionalities. The type for smaller functions has to be defined.
  // Otherwise the code wont compile as the compiler wont know what type of function is it.

  def multiAdder(x: Int)(y: Int)(z: Int): Int = x + y + z

  val doubleAdder = multiAdder(10)

  println(doubleAdder(20)(30))


  def nLer: Int => Int => Int = n => x => n * x

  def nLerCurry(n: Int)(x: Int): Int = n * x

  val doubler = nLer(2)
  println("Doubler - " + doubler(10))
  println("Doubler - " + nLer(2)(10))

  val doublerCurry = nLerCurry(2)
  println("Doubler Curry - " + doublerCurry(10))
  println("Doubler Curry - " + nLerCurry(2)(10))
}