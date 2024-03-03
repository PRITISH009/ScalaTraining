package excercise

import scala.:+

object TuplesAndMap extends App {
  // What would happen if we try to lowercase a map which had -
  // "Jim" -> 555 and "JIM" -> 900 as individual entries in it.

  val aPhoneBook = Map("Pritish" -> 100, "JIM" -> 999, "Jim" -> 500)
  println(aPhoneBook.map(pair => pair._1.toLowerCase -> pair._2)) // it overwrites

  // Design an overly simplified social network based on a map
  // Each person will have a name -> Person = String
  // Network association - add a person to the network.
  // remove a person
  // add a friend (mutual) and remove a friend
  // number of friends of a person
  // person with the most friends
  // How many people have no friends
  // if there is a social connection between 2 people (direct or not)

  case object Network {

    private var network: Map[String, List[String]] = Map.empty

    def addToNetwork(person: String): Unit = {
      network = network + (person -> List.empty)
    }

    def addToNetwork(list: Seq[String]):Unit = {
      list.foreach(addToNetwork)
    }

    def removeFromNetwork(person: String): Unit = {
      network(person).foreach(removeFriend(person, _))
      network = network.filter(x => x._1 != person)
    }

    def addFriend(person1: String, person2: String): Unit = {
      network = network +
        (person1 -> (person2 :: network(person1))) + (person2 -> (person1 :: network(person2)))
    }

    def addFriends(person: String, list: Seq[String]): Unit = {
      list.foreach(addFriend(person, _))
    }

    def removeFriend(person1: String, person2: String): Unit = {
      network = network +
        (person1 -> network(person1).filter(x => x != person2)) + (person2 -> network(person2).filter(x => x != person1))
    }

    def showFriends(person: String): Unit = {
      println(network(person))
    }

    def numFriends(person: String): Unit = {
      println(network(person).length)
    }

    def personWithMostFriends(): Unit = {
      val friendCountMap = network.groupBy(x => x._2.length).map(x => x._1 -> x._2.keys)
      val maxFriends = friendCountMap.keys.max
      println(friendCountMap(maxFriends))
    }

    def peopleWithNoFriends(): Unit = {
      println(network.filter(pair => pair._2.isEmpty).keys.size)
    }

    def connectionExists(person1: String, person2: String): Unit = {

    }

    def showNetwork(): Unit = println(network)
  }

  Network.showNetwork()

  Network.addToNetwork("Pritish")
  Network.showNetwork()

  Network.addToNetwork("Aanchal")
  Network.addFriend("Pritish", "Aanchal")
  Network.showNetwork()

  Network.addToNetwork(Seq("Kaustav", "Soham", "Arnav"))
  Network.showNetwork()

  Network.addFriends("Pritish", Seq("Kaustav", "Soham", "Arnav"))
  Network.showNetwork()

  Network.addToNetwork("RandomPerson")
  Network.addFriend("Pritish", "RandomPerson")
  Network.showFriends("Pritish")

  Network.removeFriend("Pritish", "RandomPerson")
  Network.showFriends("Pritish")

  Network.peopleWithNoFriends()
  Network.personWithMostFriends()

  Network.connectionExists("Pritish", "Arnav")
  Network.connectionExists("RandomPerson", "Pritish")

}
