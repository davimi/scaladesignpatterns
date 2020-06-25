package functionalcombinators

import util.Random._

/* source:
   http://blog.leifbattermann.de/2017/09/05/use-lambdas-and-combinators-to-improve-your-api/
 */


case class ContactInfo(name: String, email: String) {
}

object ContactInfoGenerator {

  def toContactInfoListWithBools(
                         csv: Seq[String],
                         nameRequired: Boolean,
                         emailRequired: Boolean): Seq[ContactInfo] = {
    csv
      .map(_.split(';'))
      .map(tokens =>
        (tokens.headOption.getOrElse(""), tokens.drop(1).headOption.getOrElse("")))
      .flatMap {
        case (name, email) =>
          if ((name == "" && nameRequired) || (email == "" && emailRequired)) {
            None
          } else {
            Some(ContactInfo(name, email))
          }
      }
  }

  type Converter = (String, String) => Option[ContactInfo]

  def toContactInfoList(
                         csv: Seq[String],
                         convert: Converter): Seq[ContactInfo] = {
    csv
      .map(_.split(';'))
      .map(tokens =>
        (tokens.headOption.getOrElse(""), tokens.drop(1).headOption.getOrElse("")))
      .flatMap { case (name, email) => convert(name, email) }
  }

  /*def noEmptyNameOrEmail: Converter = {
    case ("", _) | (_, "") =>
      None
    case (name, email) =>
      Some(ContactInfo(name, email))
  }*/

  def makeContactInfo: Converter = { //base converter
    case (name, email) => Some(ContactInfo(name, email))
  }

  def noEmptyEmail: Converter => Converter = {
    converter => {
      case (_, "") =>
        None
      case (name, email) =>
        converter(name, email)
    }
  }

  def noEmptyName: Converter => Converter = {
    converter => {
      case ("", _) =>
        None
      case (name, email) =>
        converter(name, email)
    }
  }

  implicit class ConverterSyntax(convert: Converter) {
    def noEmptyName = ContactInfoGenerator.noEmptyName(convert)
    def noEmptyEmail = ContactInfoGenerator.noEmptyEmail(convert)
  }
}

object ContactRunner extends App {
  import  ContactInfoGenerator._

  val csv = Array("Erika Mustermann;someEmail", "Max Mustermann;", ";")

  val nothingMissing = noEmptyEmail(noEmptyName(makeContactInfo))
  val contacts = toContactInfoList(csv, nothingMissing)

  contacts.foreach(println)

  toContactInfoList(csv, makeContactInfo.noEmptyEmail.noEmptyName)

}