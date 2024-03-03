package lecture.part2oop

object Inheritance extends App {
  // Scala has single class inheritance much like other languages.

  class Animal {
    val creatureType: String = "Wild"
    protected def eat: Unit = println("Nom Nom Nom!!")
    final def callEat: Unit = eat
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("Crunch Crunch")
    }
  }

  val cat = new Cat

  // Since inheritance means extending all the non private fields.
  cat.crunch


  // Constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // Overriding

  class Dog(override val creatureType: String) extends Animal {
    //override val creatureType: String = "Domestic"
    override def eat: Unit = {
      super.eat
      println("Crunch Crunch")
    }
  }

  val dog = new Dog("K9")

  dog.eat

  println(dog.creatureType)

  // Type substitution (Called Polymorphism in a very broad sense)
  val unknownAnimal: Animal = new Dog("K9")

  val unknownAnimal2: Dog = new Dog("K9")

  // "unknownAnimal.eat" doesn't work because of the protected keyword. Since essentially we are expecting
  // the unknownAnimal to be an Animal type object hence the eat method is supposed to be protected.
  // Whereas just declaring it to be a Dog type allows us to use the eat method.

  unknownAnimal.callEat
  unknownAnimal2.eat

  // super
  // check dog class eat method.

  // preventing overrides -
  // 1. Use the keyword final. The final keyword will prevent

  // 2. Final can be used in class itself. so if we do a final class Animal, then we cannot extend Dog from Animal Class.
  // A lot of classes in Scala are final. eg. String type.

  // 3. Seal the class = extend classes in THIS FILE, prevents extension in other files.

}
