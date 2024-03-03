package lecture.part3fp

object MapFlatMapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1,2,3,4)
  val characters = List("a", "b", "c", "d")
  val colors = List("White", "Black")

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
  println(list.map { x =>
    x * 2
  })
}
