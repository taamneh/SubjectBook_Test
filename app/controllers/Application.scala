package controllers

import Models.{DB, Person, ReadExcelScala}
import play.api.Play.current
import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.iteratee.Enumerator
import play.api.libs.json._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Salah Taamneh."))
    //Ok(views.html.salah());

  }
  val personFrom : Form[Person] = Form {
   mapping(
   "name" -> text
   )(Person.apply)(Person.unapply)
  }
  def addPerson = Action { implicit request =>
    val person = personFrom.bindFromRequest.get
    DB.save(person)
    Redirect(routes.Application.index())
  }
  def file = Action {
    Ok.sendFile(
      content = Play.application.getFile("/target/web/public/main/images/c64.pdf"),
      inline = true
    )
  }

  def video = Action {
    Ok.sendFile(
      content = Play.application.getFile("/target/web/public/main/images/v.avi"),
      inline = true
    )

    /*val app = Play.application
    var file = Play.application.getFile("/target/web/public/main/images/v.mp4")
    val source = scala.io.Source.fromFile(file)(scala.io.Codec.ISO8859)
    val byteArray = source.map(_.toByte).toArray
    source.close()
    //Ok(byteArray).as("image/jpeg")
    Result(
      header = ResponseHeader(200, Map(CONTENT_LENGTH -> file.length.toString)),
      body = Enumerator(byteArray)
    ).as("video/mp4")
    */
  }

  def getPersons = Action {
    val persons = DB.query[Person].fetch
    Ok(Json.toJson(persons))
  }
  def findImage(img_no : Int) = Action{

    val app = Play.application
    var file = Play.application.getFile("/target/web/public/main/images/" + img_no + ".jpg")
    val source = scala.io.Source.fromFile(file)(scala.io.Codec.ISO8859)
    val byteArray = source.map(_.toByte).toArray
    source.close()
    //Ok(byteArray).as("image/jpeg")
    Result(
      header = ResponseHeader(200, Map(CONTENT_LENGTH -> file.length.toString)),
      body = Enumerator(byteArray)
    )


  }
  def test = Action {
    val temp = """{
      "cols": [
      {"id":"","label":"Time","pattern":"","type":"number"},
      {"id":"","label":"Signal","pattern":"","type":"number"}
      ],
      "rows": [
      {"c":[{"v":1,"f":null},{"v":90,"f":null}]},
      {"c":[{"v":2,"f":null},{"v":106,"f":null}]},
      {"c":[{"v":3,"f":null},{"v":160,"f":null}]},
      {"c":[{"v":4,"f":null},{"v":180,"f":null}]},
      {"c":[{"v":5,"f":null},{"v":300,"f":null}]},
      {"c":[{"v":6,"f":null},{"v":90,"f":null}]},
      {"c":[{"v":7,"f":null},{"v":106,"f":null}]},
      {"c":[{"v":8,"f":null},{"v":160,"f":null}]},
      {"c":[{"v":9,"f":null},{"v":180,"f":null}]},
      {"c":[{"v":10,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":11,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":12,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":13,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":14,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":15,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":16,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":17,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":18,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":19,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":20,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":21,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":22,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":23,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":24,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":25,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":26,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":27,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":28,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":29,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":30,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":31,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":32,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":33,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":34,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":35,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":36,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":37,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":38,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":39,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":40,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":41,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":42,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":43,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":44,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":45,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":46,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":47,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":48,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":49,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":50,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":51,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":52,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":53,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":54,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":55,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":56,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":57,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":58,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":59,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":60,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":61,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":62,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":63,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":64,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":65,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":66,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":67,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":68,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":69,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":70,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":71,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":72,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":73,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":74,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":75,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":76,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":77,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":78,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":79,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":80,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":81,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":82,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":83,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":84,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":85,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":86,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":87,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":88,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":89,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":90,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":91,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":92,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":93,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":94,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":95,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":96,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":97,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":98,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":99,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":100,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":101,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":102,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":103,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":104,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":105,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":106,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":107,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":108,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":109,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":110,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":111,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":112,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":113,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":114,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":115,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":116,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":117,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":118,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":119,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":120,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":121,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":122,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":123,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":124,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":125,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":126,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":127,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":128,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":129,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":130,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":131,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":132,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":133,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":134,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":135,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":136,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":137,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":138,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":139,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":140,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":141,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":142,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":143,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":144,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":145,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":146,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":147,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":148,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":149,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":150,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":151,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":152,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":153,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":154,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":155,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":156,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":157,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":158,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":159,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":160,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":161,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":162,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":163,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":164,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":165,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":166,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":167,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":168,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":169,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":170,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":171,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":172,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":173,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":174,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":175,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":176,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":177,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":178,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":179,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":180,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":181,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":182,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":183,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":184,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":185,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":186,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":187,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":188,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":189,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":190,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":191,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":192,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":193,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":194,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":195,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":196,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":197,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":198,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":199,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":200,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":201,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":202,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":203,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":204,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":205,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":206,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":207,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":208,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":209,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":210,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":211,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":212,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":213,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":214,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":215,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":216,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":217,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":218,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":219,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":220,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":221,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":222,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":223,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":224,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":225,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":226,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":227,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":228,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":229,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":220,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":231,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":232,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":233,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":234,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":235,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":236,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":237,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":238,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":239,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":240,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":241,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":242,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":243,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":244,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":245,"f":null},{"v":300,"f":null}]},
    {"c":[{"v":246,"f":null},{"v":90,"f":null}]},
    {"c":[{"v":247,"f":null},{"v":106,"f":null}]},
    {"c":[{"v":248,"f":null},{"v":160,"f":null}]},
    {"c":[{"v":249,"f":null},{"v":180,"f":null}]},
    {"c":[{"v":250,"f":null},{"v":300,"f":null}]}
    ]
    }
   """
    //Ok(Json.parse(temp))

   // var js = Json.arr();
    var newjs: ReadExcelScala = new ReadExcelScala();
    var js = newjs.fromExcel;

    Ok(Json.obj(
      "cols" -> Json.arr(
        Json.obj(
           "id" -> "",
          "label" -> "Times",
          "patter" -> 31,
          "type" -> "number"
        ),
        Json.obj(
          "id" -> "",
          "label" -> "EDA Palm",
          "patter" -> 31,
          "type" -> "number"
        ),
        Json.obj(
          "id" -> "",
          "label" -> "EDA Ankle",
          "patter" -> 31,
          "type" -> "number"
        )
      ),
      "rows" -> js
    ))
  }



  //TODO fucntion that return the single as josn, the input will be stuy id, subject id, session id, and signal id
  //this mehtod will be clled when a suer clikc on the single, and then a jquery file or "coffescript" that is assinged to taht link
  // will be executed and called this link using get and the name of the method
}