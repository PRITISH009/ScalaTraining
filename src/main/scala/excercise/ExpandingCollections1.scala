package excercise

object ExpandingCollections1 extends App {

  class Animal(name: String)
  class Dog(name: String) extends Animal(name)
  class GoldenRetriever(name: String) extends Dog(name)

  trait MyPredicate[+A] { // Can Also be an Abstract Class
    def func: Unit
  }

  val animalObject: MyPredicate[Animal] = new MyPredicate[Animal] {
    override def func: Unit = {
      println("This is Animal")
      println(this.isInstanceOf[MyPredicate[Animal]])
    }
  }

  /**
   * This here above is working like the following -
   *
   * class ExpandingCollections$$anon$1 extends MyPredicate[Animal] {
   *  override def func: Unit = println(param.isInstanceOf[Animal])
   * }
   *
   */

  val dogObject: MyPredicate[Animal] = new MyPredicate[Dog] {
    override def func: Unit = {
      println("This is Dog")
      println(this.isInstanceOf[MyPredicate[Animal]])
      println(this.isInstanceOf[MyPredicate[Dog]])
    }
  }


  println(animalObject.getClass)
  animalObject.func

  dogObject.func

  val golderRetrieverObj: MyPredicate[Animal] = new MyPredicate[GoldenRetriever] {
    override def func: Unit = {
      println("This is a Golder Retriever")
      println(this.isInstanceOf[MyPredicate[Animal]])
      println(this.isInstanceOf[MyPredicate[Dog]])
      println(this.isInstanceOf[MyPredicate[GoldenRetriever]])
    }
  }

  golderRetrieverObj.func

}
