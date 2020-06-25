package creational.builder

//make constructor private to enforce using companion's apply
class House2 private[builder](val color: String, val heaterSystem: String) {
  override def toString: String = {
    "A " + color + " house with " + heaterSystem
  }
}

object House2 {

  //state phantom types
  sealed trait HouseConstructionState
  sealed trait HouseFoundation extends HouseConstructionState
  sealed trait Painted extends HouseConstructionState
  sealed trait Heating extends HouseConstructionState
  type FinishedHouse = HouseFoundation with Painted with Heating

  private[builder] class HouseConstruction[HouseState <: HouseConstructionState](private val theHouse: House2) {

    def withColor(color: String): HouseConstruction[HouseState with Painted] =
      new HouseConstruction[HouseState with Painted](new House2(color, theHouse.heaterSystem))

    def withHeatingType(hType: String): HouseConstruction[HouseState with Heating] =
      new HouseConstruction[HouseState with Heating](new House2(theHouse.color, hType))

    def build(implicit evidence: HouseState =:= FinishedHouse): House2 = this.theHouse
  }

  def apply() = new HouseConstruction[HouseFoundation](new House2("placeholder", "placeholder"))

}
