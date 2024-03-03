package lecture.part4PM

object PatternsEverywhere extends App {
  // big idea number 1
  try {
    // Some code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // Catches are actually matches

  // big idea 2 -
  val list = List(1,2,3,4)
  val evenOnes = for {
    x <- list if(x % 2 == 0) // Generators are also based on Pattern matching
  } yield 10 * x

  val alistOfTuples = List((1,2), (3,4))
  val filterTuples = for {
    (first, second) <- alistOfTuples
  } yield first * second // Also based on pattern matching

  // The other patterns are available as well
  // case classes, :: operators etc.

  // big idea 3 -
  val tuple = (1,2,3)
  val (a,b,c) = tuple // initialize multiple values by exploiting the name binding property of pattern matching.
  println(a)
  println(b)
  println(c)

  // Multiple value definitions based on PATTERN MATCHING
  // ALL THE POWER IS AVAILABLE

  val head :: tail = list
  println(head)  // 1
  println(tail)  // 2 3 4

  // Big idea number 4
  // partial function (are based on pattern matching)
  val mappedList = list.map {
    case v if v % 2 == 0 => "v is even"
    case 1 => "The One"
    case _ => "something else"
  } // partial function literal

  // This is equivalent to the above expression
  val mappedList2 = list.map { x =>
    x match
      case v if v % 2 == 0 => "v is even"
      case 1 => "The One"
      case _ => "something else"
  }

  println(mappedList)
  println(mappedList2)
}
