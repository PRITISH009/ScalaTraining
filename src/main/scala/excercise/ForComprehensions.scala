package excercise

object ForComprehensions extends App {

  abstract class MyList[+A] {
    val length: Int
    def isEmpty: Boolean
    def head: A
    def tail: MyList[A]
    def add[B >: A](ele: B): MyList[B]
    def printElements(): Unit = println("[" + this + "]")
    def map[B](transformer: A => B): MyList[B]
    def filter(predicate: A => Boolean): MyList[A]
    def ++[B >: A](list: MyList[B]): MyList[B]
    def flatMap[B](transformer: A => MyList[B]): MyList[B]
    def foreach(sideEffect: A => Unit): Unit
    def sort(comparator: (A, A) => Int): MyList[A]
    def zipWith[B,C](list: MyList[B], zip: (A, B) => C): MyList[C]
    def fold[B](init:B)(f: (B,A) => B): B
    def withFilter(predicate: A => Boolean): MyList[A]
  }

  case object Empty extends MyList[Nothing] {
    override val length: Int = 0
    override def isEmpty: Boolean = true
    override def head: Nothing = throw new NoSuchElementException("No Head Found. List is Empty")
    override def tail: Nothing = throw new NoSuchElementException("No Tail Found. List is Empty")
    override def add[B >: Nothing](ele: B): MyList[B] = Cons(ele, Empty)
    override def toString: String = ""
    override def map[B](transformer: Nothing => B): MyList[B] = Empty
    override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
    override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
    override def flatMap[B](transformer: Nothing => MyList[B]): MyList[Nothing] = Empty
    override def foreach(sideEffect: Nothing => Unit): Unit = ()
    override def sort(comparator: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
    override def zipWith[B,C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
      if(!list.isEmpty) throw new RuntimeException("Lists Do not have the same length")
      else Empty
    }
    override def fold[B](init: B)(f: (B, Nothing) => B): B = init
    override def withFilter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  }

  case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    override val length: Int = 1 + t.length
    override def isEmpty: Boolean = false
    override def head: A = h
    override def tail: MyList[A] = t
    override def add[B >: A](ele: B): MyList[B] = Cons(ele, this)
    override def toString: String = s"$h${if(t.isEmpty) then t else ", " + t}"
    override def map[B](transformer: A => B): MyList[B] = Cons(transformer(h), t.map(transformer))
    override def filter(predicate: A => Boolean): MyList[A] =
      if(predicate(h)) then Cons(h, t.filter(predicate)) else t.filter(predicate)
    override def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)
    override def flatMap[B](transformer: A => MyList[B]): MyList[B] = transformer(h) ++ t.flatMap(transformer)
    override def foreach(sideEffect: A => Unit): Unit = {
      sideEffect(h)
      t.foreach(sideEffect)
    }
    // Can I make this tail Recursive
    override def sort(comparator: (A, A) => Int): MyList[A] = {
      if(t.isEmpty) Cons(h, Empty)
      else {
        t.filter(ele => comparator(h, ele) > 0).sort(comparator) ++ Cons(h, Empty) ++ t.filter(ele => comparator(h, ele) < 0).sort(comparator)
      }
    }
    override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
      if(list.length != this.length) throw new RuntimeException("Lists Do not have the same length")
      else Cons(zip(h, list.head), t.zipWith(list.tail, zip))
    }
    override def fold[B](init: B)(f: (B,A) => B): B = t.fold(f(init,h))(f)
    override def withFilter(predicate: A => Boolean): MyList[A] = this.filter(predicate)
  }

  val list = Cons(1,Cons(2,Cons(3,Empty)))
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => Cons(x, Cons(x + 1, Empty))
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))
  val characters = Cons("a", Cons("b", Cons("c", Cons("d", Empty))))
  val colors = Cons("White", Cons("Black", Empty))

  // iterations
  val combinations = numbers.flatMap(x => characters.flatMap(y => colors.map(z => x + y + z)))
  println(combinations)

  // foreach
  list.foreach(println)

  // Short hands for all these chains
  val forCombination = for {
    x <- numbers if x % 2 == 0 // guard -> Compilers re write this thing as a filter on numbers
    y <- characters
    z <- colors
  } yield x + y + "-" + z

  for {
    n <- numbers
  } println(n) // Compiler rewrites this thing to an actual functional call. its identical to numbers.foreach(println)

  // syntax overload
  list.map { x =>
    x * 2
  }.printElements()


  // 2. A Small Collection of at most ONE element - Maybe[+T]
  //  - map, flatMap, filter

  abstract class Maybe[+T] {
    def map[B](f: T => B):Maybe[B]
    def flatMap[B](f: T => Maybe[B]): Maybe[B]
    def filter(p: T => Boolean): Maybe[T]
  }

  case object MaybeNot extends Maybe[Nothing] {
    override def map[B](f: Nothing => B): Maybe[B] = MaybeNot
    override def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = MaybeNot
    override def filter(p: Nothing => Boolean): Maybe[Nothing] = MaybeNot
  }

  case class Just[+T](value: T) extends Maybe[T] {
    override def map[B](f: T => B): Maybe[B] = Just(f(value))
    override def flatMap[B](f: T => Maybe[B]): Maybe[B] = f(value)
    override def filter(p: T => Boolean): Maybe[T] = {
      if (p(value)) then this else MaybeNot
    }
  }

  val just3 = Just(3)
  println(just3)
  println(just3.map(_ * 2))
  println(just3.flatMap(x => Just(x % 2 == 0)))
  println(just3.filter(_ % 2 == 0))
}
