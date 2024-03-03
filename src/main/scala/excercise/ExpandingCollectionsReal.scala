package excercise

object ExpandingCollectionsReal extends App {

  abstract class MyList[+A] {
    def isEmpty: Boolean
    def head: A
    def tail: MyList[A]
    def add[B >: A](e: B): MyList[B]
    def printElements: Unit = println("[" + this + "]")
    def map[B](transformer: MyTransFormer[A, B]): MyList[B]
    def flatMap[B](transFormer: MyTransFormer[A, MyList[B]]): MyList[B]
    def filter(predicate: MyPredicate[A]): MyList[A]
    def ++[B >: A](list: MyList[B]): MyList[B]
  }

  case object Empty extends MyList[Nothing] {
    override def isEmpty: Boolean = true
    override def head: Nothing = throw new NoSuchElementException
    override def tail: Nothing = throw new NoSuchElementException
    override def add[B >: Nothing](ele: B) = Cons(ele, Empty)
    override def map[B](transformer: MyTransFormer[Nothing, B]): MyList[B] = Empty
    override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
    override def flatMap[B](transFormer: MyTransFormer[Nothing, MyList[B]]): MyList[B] = Empty
    override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
    override def toString: String = ""
  }

  case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    override def isEmpty: Boolean = false
    override def head: A = h
    override def tail: MyList[A] = t
    override def add[B >: A](ele: B): MyList[B] = Cons(ele, this)
    override def toString: String = s"$h" + (if t.isEmpty then "" else ", ") + t
    override def filter(predicate: MyPredicate[A]): MyList[A] = {
      if(predicate.test(h)) then Cons(h, t.filter(predicate)) else t.filter(predicate)
    }
    override def map[B](transformer: MyTransFormer[A, B]): MyList[B] = {
      Cons(transformer.transform(h), t.map(transformer))
    }
    override def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)
    override def flatMap[B](transFormer: MyTransFormer[A, MyList[B]]): MyList[B] = {
      transFormer.transform(h) ++ t.flatMap(transFormer)
    }
  }

  trait MyPredicate[-T] {
    def test(ele: T): Boolean
  }

  trait MyTransFormer[-A, B] {
    def transform(ele: A): B
  }

  val listOfIntegers = Cons(1, Cons(2, Cons(3, Empty)))
  val listOfStrings = Cons("1", Cons("2", Cons("3", Empty)))

  listOfIntegers.printElements
  listOfStrings.printElements

  listOfIntegers.map(new MyTransFormer[Int, Int]{
    override def transform(ele: Int): Int = ele * 2
  }).printElements

  listOfStrings.map(new MyTransFormer[String, Int]{
    override def transform(ele: String): Int = ele.toInt
  }).printElements

  listOfIntegers.filter(new MyPredicate[Int]{
    override def test(ele: Int): Boolean = ele % 2 == 0
  }).printElements

  listOfIntegers.flatMap(new MyTransFormer[Int, MyList[Int]]{
    override def transform(ele: Int): MyList[Int] = Cons(ele, Cons(ele + 1, Empty))
  }).printElements

}
