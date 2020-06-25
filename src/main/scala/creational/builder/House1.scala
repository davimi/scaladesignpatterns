package creational.builder

import creational.builder.House1.{HouseConstructionState, HouseFoundation}

/* source:
  https://medium.com/@maximilianofelice/builder-pattern-in-scala-with-phantom-types-3e29a167e863
*/
class House1 private[builder] (val color: String, val heaterSystem: String) {

  override def toString: String = {
    "A " + color + " house with " + heaterSystem
  }
}

object House1 {

  //phantom state types
  sealed trait HouseConstructionState
  sealed trait HouseFoundation extends HouseConstructionState
  sealed trait Painted extends HouseConstructionState
  sealed trait Heater extends HouseConstructionState
  type FinishedHouse = HouseFoundation with Painted with Heater
}

class HouseBuilder[HouseState <: HouseConstructionState] private[builder] (private val theHouse: House1) {
  import House1._

  def withColor(color: String): HouseBuilder[HouseState with Painted] =
    new HouseBuilder[HouseState with Painted](new House1(color, theHouse.heaterSystem))

  def withHeatingType(hType: String): HouseBuilder[HouseState with Heater] =
    new HouseBuilder[HouseState with Heater](new House1(theHouse.color, hType))

  def build(implicit evidence: HouseState =:= FinishedHouse): House1 = this.theHouse

}

object HouseBuilder {
  def apply() = new HouseBuilder[HouseFoundation](new House1("placeholder", "placeholder"))
}

