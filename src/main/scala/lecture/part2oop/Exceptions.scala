package lecture.part2oop

object Exceptions extends App {

  val x: String = null
  // println(x.length)
  // ^^this will crash with a null pointer exception


  // 1. Throwing exceptions
  //val aWierdValue: String = throw new NullPointerException // this expression returns a Nothing.

  // throwable classes extend the Throwable class and can be thrown
  // Exception and Errors are the major Throwable subtypes

  // 2. How to catch exceptions
  def getInt(withExceptions: Boolean): Int = {
    if(withExceptions) then throw new NullPointerException("No Int for you") else 42
  }

  // try-catch-finally
  val potentialFail = try {
    getInt(true)
  } catch {
    case _: RuntimeException => println("Caught a runtime exception!")
    case _: Exception => println("Caught an unknown Exception!")
  } finally {
    // Code that will execute no matter what
    // optional
    // does not influence the return type of this expression
    println("Finally")
  }

  // 3. How to define your own exception
  class MyException(msg: String) extends Exception(msg)
  val exception = new MyException("My Own Exception")

  println(exception.getMessage)
  throw exception

}
