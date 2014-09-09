package Models

import play.api.libs.json.Json

/**
 * Created by Salah on 8/30/2014.
 */
case class Person (name: String)

object Person {
  implicit  val personFormat = Json.format[Person]
}
