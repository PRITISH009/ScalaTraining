package excercise

import scala.annotation.tailrec

object OOExcercise extends App {

  // Question 1 -
  val author = new Writer("Charles", "Dickens",  1812)
  val novel = new Novel("Great Expectations", 1861, author)

  val imposter = new Writer("Charles", "Dickens", 1812)

  println(novel.authAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  // Question 2 -
  val counter = new Counter()
  println(counter.currCount)

  val incCounter4 = counter.inc(4)
  println(incCounter4.currCount)

  val decCounter4 = incCounter4.dec(4)
  println(decCounter4.currCount)
}

class Writer(val firstName: String, val surName: String, val yearOfBirth: Int) {
  def fullName: String = s"$firstName $surName"
}

class Novel(val name: String, val yearOfRelease:Int, val author: Writer) {
  def authAge: Int = yearOfRelease - author.yearOfBirth
  def isWrittenBy(author: Writer): Boolean = author == this.author
  def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}

class Counter(val currCount: Int = 0) {
  def inc: Counter = {
    println("incrementing")
    new Counter(currCount + 1)
  }

  def dec: Counter = {
    println("decrementing")
    new Counter(currCount - 1)
  }

  @tailrec
  final def inc(amount: Int): Counter = {
    if(amount <= 0) this
    else inc.inc(amount - 1)
  }

  @tailrec
  final def dec(amount: Int): Counter = {
    if(amount <= 0) this
    else dec.dec(amount - 1)
  }
}