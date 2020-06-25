package creational.builder

object Demo extends App {


  val theCar: Car = (new CarBuilderImpl)
    .setWheels(4)
    .setColor("red")
    .build()

  println(theCar)
  println()

  /*
  Builder with phantom types
  */
  val firstHouse: House1 = HouseBuilder()
    .withColor("red")
    .withHeatingType("fireplace")
    .build

  //clients can still mess up:
  //val firstHouse = new House1("coal oven", "orange")
  println(firstHouse)
  println()

  /*
  Builder with phantom types in companion object and private constructor
  */
  val secondHouse: House2 =
    House2()
    .withColor("green")
    .withHeatingType("floor heating")
    .build

  println(secondHouse)
}
