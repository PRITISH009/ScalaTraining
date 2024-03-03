package lecture.part2oop

import playground.{Cinderella => Princess, PrinceCharming}
// import playground._ if you need all.
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // Package members are accessible by their simple names
  Enums.Permissions.values.foreach(println)

  // in order to be able to use a top level definition by their name
  // we need to either import the package or be in the same package
  // or use their fully qualified class name like Permissions from Enums.

  // Packages are ordered hierarchy
  // matching folder structures as the name of package which is inside file system
  // done by IDEs by default

  // Package Object
  // We Might need Universal Constants or Universal Methods
  // Refer Package Object part2oop.scala
  hello // from package object
  println(speedOfLight)

  val prince = new PrinceCharming
  val princess = new Princess // Aliasing for an imported class. Useful if we have more than one imported classes with the same name

  val date = new Date()
  // val sqlDate = new java.sql.Date(2018, 5, 4) one way

  val sqlDate = new SqlDate(2018, 5, 4)

  // default imports - packages that are automatically imported without any intention of import from our side.
  // e.g. - java.lang - String, Object, Exceptions etc.
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
