package creational.builder

/*
Builder pattern from Wikipedia: https://en.wikipedia.org/wiki/Builder_pattern
*/

/**
  * Represents the product created by the builder.
  */
case class Car(wheels: Int, color: String)

/**
  * The builder abstraction.
  */
trait CarBuilder {
  def setWheels(wheels:Int) : CarBuilder
  def setColor(color:String) : CarBuilder
  def build() : Car
}

class CarBuilderImpl extends CarBuilder {
  private var wheels: Int = 0
  private var color: String = ""

  override def setWheels(wheels: Int) = {
    this.wheels = wheels
    this
  }

  override def setColor(color: String) = {
    this.color = color
    this
  }

  override def build = Car(wheels,color)
}
