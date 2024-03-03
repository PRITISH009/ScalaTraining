package lecture.part2oop

object Generics extends App {

  class MyList[+A] {
    def add[B >: A](element: B): MyList[B] = ???
  }

  // classes and traits can be type parameterised but not Objects.

  object MyList {
    def empty[A]: MyList[A] = ???
  }


  val listOfIntegers = new MyList[Int]

  val listOfStrings = new MyList[String]

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // if cat extends animal -> does List[Cat] extends List[Animal]
  // yes -> covariance
  class CovariantList[+A] // declaration of covariance
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // should we be able to add a dog to animalList which is in fact a list of Cats.
  // animalList.add(new Dog)

  // no -> invariance
  // List[Cat] and List[Animal] are 2 separate things.

  class InvariantList[A] // declaration of invariant
  // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] gives error.
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // only this is possible

  // hell no -> contravariance
  class ContravariantList[-A] // declaration of contravariance
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal] // Very Counter Intuitive.

  class Trainer[-A]
  val catTrainer: Trainer[Cat] = new Trainer[Animal] // This seems better. as a trainer of animals can be a trainer of cats.

  // bounded types.
  class Cage[A <: Animal](animal: A) // Class cage only accepts type parameters A which are sub types of Animal.
  val cage = new Cage(new Dog)

  class Car
  // val cage2 = new Cage(new Car) this gives error since Car is not a subtype of Animal

  // >: is the sign for super type


}
