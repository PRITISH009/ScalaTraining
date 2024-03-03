package lecture.part2oop

object ScalaObjects extends App {
  // Objects are a dedicated concept in Scala. One of the fundamental concepts of Object Oriented programming is
  // something called Class level Functionality. i.e. Functionality that does not depends on an instance of a class.
  // if you notice everything that we have done so far had connection to an instance of a some class at any given point.

  // Now there are some cases where we shouldn't really do that, such as universal constants or universal functionality
  // that we should be able to access without relying on an instance of some class.

  // SCALA DOES NOT HAVE CLASS LEVEL FUNCTIONALITY!!!! ("Static")

  // It has something even better called Objects. It is a Static Like Functionality

  object Person {
    // An Object can have vals vars and method defs as well

    // By defining Person as an object, I define its type as well as its only instance.

    val N_EYES = 2
    def canFly: Boolean = false

    // Factory Method - Soul Purpose is to build Persons from input values.
    def apply(mother: Person, father: Person): Person = {
      new Person("Child")
    }

  }

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object is a singleton instance.

  val mary = new Person("Mary")
  val john = new Person("John")

  println(mary == john)

  // Objects are singleton instances by definition without any other code needed from you.
  // Now a pattern that is often used in practice is as follows -

  class Person(val name: String) {
    // Instance level functionality. where as Object person would contain Class Level functionality.

  }

  // This pattern of writing objects and classes of the same name together is called Companions.
  // These are companions of each other. The class Person and the Object Person have the same scope
  // and have the same name, hence they are Companions.

  val person1 = Person
  val person2 = Person

  println(person1 ==  person2)

  val child = Person(mary, john)

  println(child.name)

  // A Scala Application = Scala object with a very important methods - def main(args: Array[String]): Unit
  // This is because the scala application is also run by JVM which searches for a Static Main method with void return type.


}
