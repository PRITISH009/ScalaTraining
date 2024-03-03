package lecture.part2oop

object AnonymousClass extends App {

  abstract class Animal {
    def eat: Unit
  }

  // not creating an instance of Animal class but creating an anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahahhaha")
  }

  /*
  *
    This is what happens behind the scenes -

    class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("hahahahahah")
    * }

    and when we instantiate it - this is what actually happens -

    val funnyAnimal: Animal = new AnonymousClasses$$anon$1

  * */

  println(funnyAnimal.getClass)

  // Anonymous class with non abstract classes. When we override a definition of something we do it
  // through anonymous things assigned to the name.
  class Person(name: String) {
    def sayHi: Unit = println(s"Hi My name is $name how can I help")
  }

  val jim = new Person("Jim (// this doesn't matter because it is not a parameter of the anonymous class being created)") {
    override def sayHi: Unit = {
      super.sayHi
      println(s"hi My name is Jim! How can I be of service")
    }
  }

  println(jim.getClass)
  jim.sayHi

  /*
  the above is equivalent to -

  class AnonymousClass$$anon$2 extends Person("useless parameter for anonymous class but needed") {
    override def sayHi: Unit = println("hi My name is Jim! How can I be of service")
  }

  val jim = new AnonymousClass$$anon$2

  */



}
