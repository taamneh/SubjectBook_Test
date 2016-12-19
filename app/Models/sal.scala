package Models

/**
 * Created by staamneh on 9/10/2014.
 */

import java.io.{IOException, File}
import java.util

import anorm._
import controllers.{SharedData, DataBaseOperations, GoogleDrive}
import org.apache.poi.openxml4j.exceptions.{InvalidFormatException, InvalidOperationException}
import org.json.simple.{JSONObject, JSONArray}
import org.json.simple.parser.{ParseException, JSONParser}
import play.Logger
import play.api.db.DB

import scala.util.Random

//import play.api.Play.current;

object sal {

  def computePercentile(vals: Seq[Double], tile: Double, unsorted: Boolean = true): Double = {
    assert(tile >= 0 && tile <= 100)
    if (vals.isEmpty) Double.NaN
    else {
      assert(vals.nonEmpty)
      // Make sure the list is sorted, if that's what we've been told
      if (!unsorted && vals.length >= 2) vals.sliding(2).foreach(l => assert(l(0) <= l(1))) else {}
      // NIST method; data to be sorted in ascending order
      val r =
        if (unsorted) vals.sorted
        else vals
      val length = r.length
      if (length == 1) r.head
      else {
        val n = (tile / 100d) * (length - 1)
        val k = math.floor(n).toInt
        val d = n - k
        if (k <= 0) r.head
        else {
          val last = length
          if (k + 1 >= length) {
            r.last
          } else {
            r(k) + d * (r(k + 1) - r(k))
          }
        }
      }
    }
  }




  var perm = List.empty[String]
  def permutation(str: String): List[String] = {
    perm = List.empty[String]
    permutation("", str)
  }


  def permutation(prefix: String, str: String): List[String] = {
    val n = str.length
    if(n==0) perm = prefix :: perm
    else
    {
      for(i<- 0 to n-1){
        permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n))
      }
    }
    perm
  }

  def main(args: Array[String]) {



    var mp :Map[String,(String,Boolean,Int, Int, Boolean)] = Map.empty

    mp += "s1" ->("s1", true, 1, 0, true)
    mp += "G2b" ->("G2b", true, 4, 2, false)



    println(mp)


   /*println(String.format("%1$05d", i))




    val file: File = new File("C:\\Users\\staamneh\\Downloads\\study.xlsx")
    val jsonForChart: StudyDescriptionFromExcel = new StudyDescriptionFromExcel("C:\\Users\\staamneh\\Downloads\\study.xlsx", 4)

    println("what is going on")

    try {
      println("what is going on 2")
      val newF: NewExcelFormatForBarData = new NewExcelFormatForBarData(jsonForChart)
      val oldF: OldExcelFormat = new OldExcelFormat(jsonForChart)
      try {
        newF.processAllSheets
      }
      catch {
        case ioe: InvalidOperationException => {
          oldF.readSheet
        }
      }
      jsonForChart.finalize()
    }
    catch {
      case e: InvalidFormatException => {
        return null
      }
      case e: IOException => {
        e.printStackTrace
      }
    } finally {
    }

    jsonForChart.getDescriptor();
*/

  }


}
