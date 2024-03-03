package lecture.part2oop

object CaseClasses extends App {

  /*
    For light weight data structures like Person a lot of boiler plate has to be re implemented a lot of the times.
    e.g. implementing - equals, hashCode, toString etc. Case Classes are an ideal solution to all this.
  */


  case class Person(name: String, age: Int)

  val jim = new Person("Jim", 34)
  // 1. class parameters are fields
  println(jim.name)

  // 2. sensible toString
  println(jim)

  // 3. equals and hashCode implemented out of the box.
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. Case Classes have handy copy methods
  val jim3 = jim.copy(age=45)
  println(jim3)

  // 5. Case Classes have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. Case Classes are serializable.
  // very very imp for Akka

  // 7. Case Classes have extractor patterns = CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The United Kingdom of GB and NI"
  }



}
