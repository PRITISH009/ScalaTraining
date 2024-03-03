package excercise

abstract class MyList {

  // head = first element of this list.
  // tail = remainder of the list.
  // isEmpty = is this list Empty
  // add(int) = new list with this element added.
  // to String = a string representation of the list.

  def isEmpty: Boolean
  def head: Int
  def tail: MyList
  def add(ele: Int): MyList
  def printElements: String
  override def toString: String = "[" + printElements + "]"

}

case object Empty extends MyList {
  override def isEmpty: Boolean = true
  override def head: Int = throw new NoSuchElementException("No Head Found! List is Empty")
  override def tail: MyList= throw new NoSuchElementException("No Tail Found! List is Empty")
  override def add(ele: Int): MyList = Cons(ele, Empty)
  override def printElements: String = ""
}

case class Cons(h: Int, t: MyList) extends MyList {
  override def isEmpty: Boolean = false
  override def head: Int = h
  override def tail: MyList = t
  override def add(ele: Int): MyList = Cons(ele, this)
  override def printElements: String = s"$h" + (if(t.isEmpty) then "" else ", ") + t.printElements
}

object ListTest extends App {

  val list = new Cons(1, Empty)

  println(list)

  val newList = list.add(4)

  println(newList.head)

  println(newList.add(1).tail.head)

  println(newList.toString)

}
