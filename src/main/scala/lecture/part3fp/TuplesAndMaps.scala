package lecture.part3fp

object TuplesAndMaps extends App {
  // tuples - finite ordered "lists"
  //val aTuple = new Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String) (syntactic sugar)
  val aTuple = (2, "hello, Scala")

  // Tuples can group at most 22 elements of different types. Since they are used in conjunction with function types which
  // also go from 0 to 22

  println(aTuple._1)
  println(aTuple._2)

  // we can create copies
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap)

  // Maps are collections used to associate things with other things.
  // key value pairs.

  val aMap: Map[String, Int] = Map()
  val aPhoneBook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1) // -> is a syntactic sugar for typle
  println(aPhoneBook)

  // map operations
  println(aPhoneBook.contains("Jim")) // Returns true
  println(aPhoneBook("Jim")) // prints 555
  println(aPhoneBook("Mary")) // No Such Element Exception if withDefaultValue is not applied

  // add a pairing
  val aPairing = "Mary" -> 678
  val aNewPhoneBook = aPhoneBook + aPairing

  // functionals on Map
  // map, flatMap, filter
  println(aPhoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  println(aPhoneBook.view.filterKeys(x => x.startsWith("J")).toMap) // Keys are filtered by this predicate
  // Since its a map view without toMap, its not computed hence we have to add a toMap to convert it into a Map
  // gives key value pairs
  println(aPhoneBook.keys.filter(x => x.startsWith("J"))) // Similar as above but just gives keys.

  // mapValues
  println(aPhoneBook.view.mapValues(x => x * 10).toMap) // Maps to values and returns key value pair results
  println(aPhoneBook.view.mapValues(x => "0245-" + x).toMap) // If all phone numbers are changed with a prefix added to them

  // conversions to other collections
  println(aPhoneBook.toList) // Creates a pairing of all the key value pairs in a list
  println(List(("Daniel", 555)).toMap) // Reverse Conversion
  // grouping
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0))) // Groups all the names in the list who have the same first character


}
