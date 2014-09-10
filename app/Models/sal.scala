package Models

/**
 * Created by staamneh on 9/10/2014.
 */
object sal {

  def main(args: Array[String])
  {
    var newjs: ReadExcelScala = new ReadExcelScala("MV", "S002");
    var js = newjs.fromExcel;
  }
}
