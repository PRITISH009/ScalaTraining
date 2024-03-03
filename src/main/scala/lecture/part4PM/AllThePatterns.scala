package lecture.part4PM

import excercise.ExpandingMyListHofs.{Cons, Empty, MyList}

object AllThePatterns extends App {
  // 1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a Number"
    case "Scala" => "The Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - match anything -> implementation of that is wild card _
  // 2.1 wildcard
  val matchAnything = x match {
    case _ => ""
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've Found $something"
  }

  // 3. Tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1, 1) => ""
    case (something, 2) => s"Ive Found $something" // This guy will try to extract this "something" if the rest of the pattern matches
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) => ""
  }

  // Pattern matches can be nested

  // 4 - Biggest uses of Pattern Matching - Case Classes
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty => "Empty"
    case Cons(h, Cons(subhead, subtail)) => s"$h $subhead $subtail"
  }

  // 5 - list patterns
  val aStandardList = List(1,2,3,42)
  val matchAStandardList = aStandardList match {
    case List(1, _, _, _) => "Extractor"// extractor - even though List is not a case class the List constructor extractor exists in Scala std lib
    case List(1, _*) => "List of Arbitrary" // This is also advanced, matches list of any length starting with 1
    case 1 :: List(_) => "Infix Pattern"
    case List(1, 2, 3) :+ 42 => "Another Infix Patter"
  }

//  // 6 - type specifiers
//  val unknown: Any = 2
//  val unknownMatch = unknown match {
//    case list: List[Int] => // explicit type specifiers
//    case _ =>
//  }
//
//  // 7 - Name Binding
//  val nameBindingMatch = aList match {
//    case nonEmptyList @ Cons(_,_) => // This will name a pattern
//    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns
//  }
//
//  // 8 - Multi Patterns
//  val multiPattern = aList match {
//    case Empty | Cons(0, _) => // Compound patterns (multi-patterns)
//  }
//
//  // 9 - if gaurds
//  val secondElementSpecial = aList match {
//    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
//  }

  // A Question -
  //

  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfString: List[String] => "A List of Strings" // Matches this wow.. // JVM Trick Question - removes Types from matches-
    case listOfNumbers: List[Int] => "A List of Numbers"  // Type erasure 
    case _ => ""
  }

  val anotherNumbersMatch = numbers match {
    case listOfNumbers: List[Int] => "A List of Numbers"
    case listOfString: List[String] => "A List of Strings"
    case _ => ""
  }

  println(numbersMatch)
  println(anotherNumbersMatch)
}

