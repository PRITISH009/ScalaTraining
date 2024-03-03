package lecture.part1basics

object ValuesVariablesTypes extends App {

  val x: Int = 42
  println(x)
  
  lazy val x2 = 42
  
  def x1 : Int = 42

  val aString: String = "Pritish"
  print(aString)

  val aBoolean: Boolean = true
  val aChar: Char = 'a'
  val aShort: Short = 4613
  val aLong: Long = 1282299292912302030L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14
  
  println(x2)
  println(x2)

  // Vals are immutable
  // cannot do x = 2

}
