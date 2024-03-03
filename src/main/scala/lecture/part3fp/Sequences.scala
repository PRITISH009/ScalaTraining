package lecture.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  /*
    A (very) general interface for data structures that
      - have a well defined order
      - can be indexed
    Supports a lot of operations out of the box -
      - apply, iterator, length, reverse for indexing and iterating
      - concatenation, appending and prepending
      - a lot of other: grouping, sorting, zipping, searching, slicing
  */

  val aSeq = Seq(1,2,3,4)
  println(aSeq) // Actually a list
  // The Seq companion object has an apply factory method that can construct subclasses of Seq.
  // But the declared type of a Seq is Seq[Int]

  println(aSeq.reverse)
  println(aSeq(2)) // aSeq.get(index = 2) in a 0 based indexing
  println(aSeq ++ Seq(5,6,7)) // List(1,2,3,4,5,6,7)
  println((aSeq ++ Seq(7,6,5)).sorted)  // Sorted works out of the box
  // The sorted method works if the type is by default ordered.
  // More about this in the advanced scala course


  // Ranges -
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println) // will print all the numbers 1 to 10

  val aSecRange: Seq[Int] = 1 until 10 // excludes 10
  aSecRange.foreach(println)

  (1 to 10).foreach(x => print("Hello"))
  // Ranges are an extremely useful and will be discussed on more in advanced scala courses.

  // Lists
  // Lists are immutable and extends Linear Sequence
  // Since they extend linear Sequence, it contains -
  // head, tail, isEmpty methods which are fast with O(1) time complexity
  // But Most other operations like - length, reverse or getting an element at an index are of O(n) time complexity

  val aList = List(1, 2, 3)
  val aPrepended = 42 :: aList // 42 +: aList (colon always on the side of the list)
  println(aPrepended)

  // Note :: is not an like ++ it is the actual name of the List class like we had Cons.
  // and also a method defined in the abstract class List which prepends the element to a list
  //
  val appended = aPrepended :+ 43
  println(appended)

  val apples5 = List.fill(5)("Apple")
  println(apples5)

  println(aList.mkString("-")) // Makes a String out of a list with all elements separated by "-"

  // Arrays - Equivalent of Simple Java Arrays
  // -  can be manually constructed with predefined lengths
  // - can be mutated (updated in place)
  // interoperable with Java arrays
  // indexing is fast
  // Arrays is still a Seq.

  val numbers = Array(1, 2, 3, 4, 5)
  val threeElements = Array.ofDim[Int](3)
  threeElements.foreach(println) // For Primitive types we have default values but for Reference types values are initialized
  // with nulls

  // mutations
  numbers(2) = 0 // Why does this work -> this is because of the update method
  // numbers.update(2, 0) -> Similar to apply but it is rarely used and only used in mutable collections.
  println(numbers.mkString(" "))

  // Arrays and Seqs
  val numbersSeq: Seq[Int] = numbers // Although numbers is an array still the type conversion can be applied
  // even though numbers didn't have anything to do with the Seq.

  // this "= numbers" this part in the above code is called implicit conversion.
  // implicit conversions are an extremely advanced topic.
  // We can convert an Array to a Seq via implicit conversions.
  // This is how arrays are able to take advantage of Seq methods. (Contravariance probably)
  println(numbersSeq)

  // Vectors - Its in its own world and its a default implementation for immutable sequences.
  // - effectively constant indexed read and write: O(log32(n))
  // - fast element addition: append/prepend
  // implemented as a fixed-branch trie (branch factor 32)
  // good performance for large sizes (caches optimizations)

  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // Perf Test with Vectors vs Lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // Saves the reference to the tail
  // updating an element to the middle takes a long time
  println(getWriteTime(numbersList))

  // depth of the tree is small
  // needs to Replace an entire 32 element chunk everytime
  println(getWriteTime(numbersVector))
  
  // The difference is huge!!!!
}
