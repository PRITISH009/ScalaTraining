package lecture.part2oop

object Enums extends App {

  enum Permissions {
    case READ, EXECUTE, NONE, WRITE

    // add fields and methods -
    def openDocument(): Unit = {
      if this == READ then println("Open Document") else println("Reading Not Allowed")
    }
  }


  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4)
    case WRITE extends PermissionsWithBits(2)
    case EXECUTE extends PermissionsWithBits(1)
    case NONE extends PermissionsWithBits(0)
  }

  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = PermissionsWithBits.NONE
  }

  val somePermissions: Permissions = Permissions.WRITE
  somePermissions.openDocument()

  println(somePermissions)

  // standard APIs of ENUMS.
  val somePermissionOrdinals = somePermissions.ordinal
  println(somePermissionOrdinals)

  val allPermissions = PermissionsWithBits.values
  println(allPermissions)

  Permissions.values.foreach(println)
}
