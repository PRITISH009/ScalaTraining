package lecture.part2oop

object OOBasics extends App {

  val person = new Person("Pritish", 24, 24)

  println(person)
  println(person.ageField)
  println(person.x)
  person.greet("Danial")
  person.greet()

}

// class Person -> creation of a class
// Passing parameters to this class. This is also a constructor.
// Since age and name are passed into the constructor, these are class parameters but not class members.
// class members are created by assigning val or var to the variable names. Hence cannot call person.age
// CLASS PARAMETERS ARE NOT FIELDS
// Default Parameters works for constructors as well.
class Person(name: String, age: Int, val ageField:Int = 0) {

  // body
  val x = 2
  println(1 + 3)
  // We can have expressions, methods, other definitions (like packages and other classes and more complex stuff)
  // and the Value of this block is ignored. We can do anything inside this block that can be done in any other
  // code block.

  def greet(name:String): Unit = println(s"${this.name} says Hi, $name")

  // overloading
  def greet(): Unit = println(s"Hi, says $name")

  // Overloading Constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe", 0)

  // We can add default values for parameters which are optional, then we wont have to write
  // overloaded constructors in the class. But just in case if it is needed, "this" (pun intended)
  // is the way forward. The definition of an overloaded constructor can only be an instance of a
  // default constructor.


}


