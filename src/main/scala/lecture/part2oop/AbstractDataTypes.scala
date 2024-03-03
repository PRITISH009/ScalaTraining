package lecture.part2oop

object AbstractDataTypes extends App {

  // abstract

  // Abstract classes cannot be instantiated.
  abstract class Animal {
    // Abstract methods and methods
    val creatureType: String  // abstract val
    val isAnimal: Boolean = true // non abstract val
    def eat: Unit // abstract method
    def jump: Unit = println("Jump!") // non abstract method
  }

  class Dog extends Animal {
    override def eat: Unit = println("Crunch Crunch!!")
    override val creatureType: String = "Dog"
  }

  trait ColdBlooded

  trait Carnivore {
    val preferredMeal: String = "fresh meat"
    def eat(animal: Animal): Unit
  }

  class Croc extends Animal with Carnivore with ColdBlooded { // Multiple Inheritance with traits.
    override val creatureType: String = "Canine"
    override def eat: Unit = println("Nom Nom Nom!!")
    override def eat(animal: Animal): Unit = println(s"I am a croc and I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Croc

  croc.eat(dog)

  // Traits vs Abstract Classes -
  // Both traits and Abstract Classes can have abstract and non abstract members (different from Java)
  // 1. traits do not have constructor parameter.
  // 2. Multiple traits may be inherited by the same class.
  // 3. Choose a trait when it describes a behaviour but an abstract class is a type of thing.
  // Animals describe animals but Traits describe what they do.

}
