package lecture.part3fp

object WhatsAFunction extends App {

  trait MyFunction[A, B] {
    def apply(element: A): B
  }

  // Use Functions as First Class Elements (like we work with plain values)
  // Problem: Object Oriented World where everything is an instance of some kind of class.

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  // This above Function Simulation works very well because of apply method which makes it look like we are calling
  // a function.
  println(doubler(2))

  // These function types are supported out of the box by scala.
  // function types = Function1[A, B] Function2[A, B, C]
  // example of Function 1
  val stringToIntConvertor = new Function1[String, Int] {
    override def apply(ele: String): Int = ele.toInt
  }

  println(stringToIntConvertor("3") + 4) // Should give 7

  // By default Scala supports upto 22 parameters.

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  println(adder(2, 4)) // expected 6

  // A syntactic sugar for this Function[Int, Int, Int] type is (Int, Int) => Int (parenthesis are imp)
  // this is getting closer to functional programming.

  // Function types Function2[A, B, R] == (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS/INSTANCES OF CLASSES DERIVING FROM Function1 Function2 etc.

  // MOAR Syntax
  val niceAdder: Int => Int = _ + 1
  val niceIncrementer: (Int, Int) => Int = _ + _

  val higherOrderFunction = new Function[Int, Function[Int, Int]] {
    override def apply(x: Int): Function[Int, Int] = new Function[Int, Int] {
      override def apply(v: Int): Int = x * v * 100
    }
  }

  println(higherOrderFunction(2)(5))
}



