package excercise

object Inheritance extends App {

  abstract class MyList {
    def head: Int
    def tail: MyList
    def isEmpty: Boolean
    def add(ele: Int): MyList
    def printElements: String

    override def toString: String = "[" + printElements + "]"
  }

  object Empty extends MyList {
    def head: Int = throw new NoSuchElementException
    def tail: MyList = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add(ele: Int): MyList = new Cons(ele, Empty)

    override def printElements: String = ""
  }

  class Cons(h: Int, t: MyList) extends MyList {
    def head: Int = h
    def tail: MyList = t
    def isEmpty: Boolean = false
    def add(ele: Int): MyList = new Cons(ele, this)

    override def printElements: String = {
      if(t.isEmpty) "" + h
      else h + " " + t.printElements
    }
  }

  val list: Cons = new Cons(1, Empty)
  println(list.head)
  println(list.tail.isEmpty)
  println(list.add(4).head)
  println(list.isEmpty)
  println(list.printElements)
  println(list)

}
