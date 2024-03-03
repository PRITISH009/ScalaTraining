package excercise

object MyGenericList extends App {

  abstract class MyList[+A] {
    def isEmpty: Boolean
    def head: A
    def tail: MyList[A]
    def add[B >: A](ele: B): MyList[B]
  }

  case object Empty extends MyList[Nothing] {
    def isEmpty: Boolean = true
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def add[B >: Nothing](ele: B): MyList[B] = new Cons(ele, Empty)
  }

  case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](ele: B): MyList[B] = new Cons(ele, this)
  }

  val newListOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val newListOfStrings = new Cons("Hello", new Cons("Scala", Empty))

  println(newListOfIntegers)
  println(newListOfStrings)

}
