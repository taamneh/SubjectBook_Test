package Models

/**
 * Created by Salah on 8/30/2014.
 */
import sorm._

object DB extends Instance(entities = Seq(Entity[Person]()), url = "jdbc:h2:mem:test") {

}
