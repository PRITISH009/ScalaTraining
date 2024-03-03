package lecture.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favouriteMovie: String) {
    def likes(movie: String): Boolean = movie == favouriteMovie
    def isHangingOutWith(person: Person): String = s"$name likes to hang out with ${person.name}"
    def +(person: Person): String = s"$name likes to hang out with ${person.name}"
    def unary_! : String = s"This is $name!!!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi!! My name is $name and I Like $favouriteMovie"
  }

  val mary = new Person("Mary", "Inception")

  println(mary.likes("Inception"))
  println(mary likes "Inception") // infix notation or operator notation (only works with methods which have 1 param)
  // Syntactic Sugars (above). These are nicer ways of writing code.

  val tom = new Person("Tom", "Fight Club")

  println(mary isHangingOutWith tom)

  println(mary + tom)
  println(mary.+(tom))

  // Operators in Scala

  // Mathematical operators also works the same way.
  // For eg. + is a method defined in Int for eg. to work like this -
  println(1 + 2) // Since 1 is an object of Int class it is the same as calling -
  println(1.+(2))

  // All Operators in Scala are methods.
  // Similarly Akka Actors have methods like ! and ?

  // prefix notation
  val x = -1 // - is a unary operator here, these are also methods
  val y = 1.unary_- // this is equivalent to -1.

  // Unary_prefix only works with a few operators. it works with - + ~ and ! operator.

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())  // Special method. When a compiler sees an object being called like a method, it looks for the
  // apply method.

  println(mary())

}
