package lecture.part4PM

import scala.util.Random

object PatternMatching extends App {

  // switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "The 1"
    case 2 => "Double or Nothing"
    case 3 => "Third time's the charm"
    case _ => "Something Else"  // The _ is call the wild card and it will match anything
  }

  println(x)
  println(description)

  // The above pattern match expression looks like a switch in other languages.
  // But a Pattern match is much more powerful

  // 1. It can decompose values.
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)
  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, My name is $n and I am a Minor" // cases can be used with guards like this
    case Person(n, a) => s"Hi, My name is $n and I am $a years old"
    case _ => "I dont know who I am"
  }

  println(greeting)

  /*
  1. Cases are matched in Order
  2. what if no cases match? MatchError
  3. type of the PM expression? unified types of all the types in all the cases
  4. PM works really well with case classes as they come out of the box with patterns to extract elements from it.
  */

  // 2. PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")

  animal match {
    case Dog(someBreed) => println(s"A dog of the $someBreed breed")
  }

  // Sealed classes help us cover our asses by providing warnings for uncovered cases
  // which match everything

  val isEven = x match {
    case n if x % 2 == 0 =>  true
    case _ => false
  }

  // Don't do overkill like the above

  /*
    Exercise
    simple function that uses PM, takes an Expr => human readable form
    e.g. Sum(Number(2), Number(3)) => 2 + 3
    e.g. Sum(Number(2), Number(3), Number(4)) = 2 + 3 + 4
    e.g. Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
    e.g. Sum(Prod(Number(2), Number(1)), Number(3)) = 2 * 1 + 3
  */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  val expr1 = Sum(Number(2), Number(3))
  val expr2 = Sum(Sum(Number(2), Number(3)), Number(4))
  val expr3 = Prod(Sum(Number(2), Number(3)), Number(4))
  val expr4 = Sum(Prod(Number(2), Number(1)), Number(4))
  val expr5 = Prod(Sum(Number(2), Number(1)), Sum(Number(3), Number(4)))

  def expression(expr: Expr): String = {
    expr match {
      case Sum(e1, e2) => expression(e1) + " + " + expression(e2)
      case Prod(e1, e2) => {
        def mayBeParenthesesShow(expr: Expr) = expr match {
          case Prod(_, _) => expression(expr)
          case Number(_) => expression(expr)
          case _ => "(" + expression(expr) + ")"
        }
        mayBeParenthesesShow(e1) + " * " + mayBeParenthesesShow(e2)
      }
      case Number(n) => s"$n"
    }
  }

  println(expression(expr1))
  println(expression(expr2))
  println(expression(expr3))
  println(expression(expr4))
  println(expression(expr5))
}
