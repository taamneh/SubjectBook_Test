package Models

/**
 * Created by staamneh on 9/10/2014.
 */

import anorm._
import play.api.db.DB
//import play.api.Play.current;

object sal {

  def main(args: Array[String]) {

    /*val conn = play.api.db.DB.getConnection();
    play.api.db.DB.withConnection { implicit c =>
      val id: Option[Long] =
        SQL("insert into user(ID, PASSWORD) values ({name}, {country})")
          .on('name -> "Salah", 'country -> "Taamneh").executeInsert()
    }*/
  }
    println("Salah");

  val fruit: List[(String, String)] = List("apples" -> "sfsd", "oranges" -> " dfs", "pears" -> "sfds")

  println(fruit(1)._2);


  val ss: List[(Int, String, Int)] = List((1,"sss",3))
  println(ss(0)._2);

  var test: List[(Int, List[Int])] = List(1->List(1,2,17));

  val test2: List[Int] = (test(0)._2)
  println((test2(2)));

  val test3: List[(String, String)] = List("S001" -> "1", "S001" ->"2", "S3"->"6");

  var maptest : Map[String, List[String]] = Map();

  maptest = maptest + ("ss" -> List("22", "44"), "aa" -> List("1","r"));



  /*for(item <- maptest)
     for(sub <- item)
         print(sub)*/

  for((k,v) <- maptest)
  {
    //println(k);
    for(x <- v)
      println(x)
  }



  //GoogleDrive.FindStudyLocalServer("","",1);
  //for(ctr <- test3)

}
