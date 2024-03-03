package excercise

import scala.annotation.targetName
import scala.language.postfixOps

object MethodNotations extends App {
  class Person(val name: String, favouriteMovie: String, val age: Int = 18) {
    def likes(movie: String): Boolean = movie == favouriteMovie
    def isHangingOutWith(person: Person): String = s"$name likes to hang out with ${person.name}"
    def +(person: Person): String = s"$name likes to hang out with ${person.name}"
    def +(nickName: String): Person = new Person(s"$name ($nickName)", favouriteMovie)
    def unary_+ : Person = new Person(name, favouriteMovie, age+1)
    def unary_! : String = s"This is $name!!!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi!! My name is $name and I Like $favouriteMovie"
    def apply(num: Int): String = s"$name watched her fav movie $favouriteMovie $num times"
    def learns(subject: String): String = s"$name Learns $subject"
    def learnsScala: String =   learns("Scala")
    override def toString: String = s"Hi, this is $name, I am $age years old and my Favourite Movie is $favouriteMovie"
  }

  val mary = new Person("Mary", "Inception")
  println(mary + "the Rockstar")

  println((mary + "The Rockstar")())

  println((+mary).age)

  println(mary learnsScala)

  println(mary(2))

}
