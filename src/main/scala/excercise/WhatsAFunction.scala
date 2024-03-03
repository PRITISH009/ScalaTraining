package excercise

object WhatsAFunction extends App {

  // A function which takes 2 strings and concatenate them
//  val concatenate: (String, String) => String = new Function2[String, String, String] {
//    override def apply(v1: String, v2: String): String = v1 + v2
//  }

  // lambda
  val concatenate: (String, String) => String = (a, b) => a + " " + b

  println(concatenate("Pritish", " Gupta"))

  // define a function which takes an int and returns another function (which takes an int and returns an int).

  //This is a higher order function and can also act as a curried method.
  val returnsNlerFunction: Int => Int => Int = n => ele => n * ele

  println(returnsNlerFunction(2)(10)) // Multiple parameter lists - expects 20

  val doubler = returnsNlerFunction(2)
  println(doubler(10))

  val tripler = returnsNlerFunction(3)
  println(tripler(10))

  val quadripler = returnsNlerFunction(4)
  println(quadripler(10))
}
