package functionalcombinators


class A private (b1: Boolean, b2: Boolean) { //make constructor private to enforce using companion's apply

  override def toString: String = {
    "" + b1 + "-" + b2
  }

  def withB1: A = {
    new A(true, this.b2)
  }
  def withB2: A = {
    new A(this.b1, true)
  }
}

object A {
  def apply(): A = new A(false, false)
}

object MyConverter extends App{

  //case class seems not to work as apply is already taken ???
  val t1 = A()
  println(t1)

  val t2 = A().withB1.withB2
  // Would it be possible to create a compile error if withB* is not specified?
  // If not, the withs should be really be optional, otherwise confusion possible
  println(t2)
}
