// @SOURCE:C:/Users/staamneh/Desktop/CPL-Lab/System Desgin/first_play/conf/routes
// @HASH:8880bfe3af6092291ef611215f9b171f24dca80d
// @DATE:Mon Sep 08 15:33:51 CDT 2014


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
controllers.Application.index,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
        

// @LINE:8
private[this] lazy val controllers_Application_addPerson1_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("person"))))
private[this] lazy val controllers_Application_addPerson1_invoker = createInvoker(
controllers.Application.addPerson,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "addPerson", Nil,"POST", """""", Routes.prefix + """person"""))
        

// @LINE:11
private[this] lazy val controllers_Application_getPersons2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("persons"))))
private[this] lazy val controllers_Application_getPersons2_invoker = createInvoker(
controllers.Application.getPersons,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getPersons", Nil,"GET", """""", Routes.prefix + """persons"""))
        

// @LINE:13
private[this] lazy val controllers_Application_test3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("test"))))
private[this] lazy val controllers_Application_test3_invoker = createInvoker(
controllers.Application.test,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "test", Nil,"GET", """""", Routes.prefix + """test"""))
        

// @LINE:15
private[this] lazy val controllers_Application_file4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("file"))))
private[this] lazy val controllers_Application_file4_invoker = createInvoker(
controllers.Application.file,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "file", Nil,"GET", """""", Routes.prefix + """file"""))
        

// @LINE:17
private[this] lazy val controllers_Application_video5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("video"))))
private[this] lazy val controllers_Application_video5_invoker = createInvoker(
controllers.Application.video,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "video", Nil,"GET", """""", Routes.prefix + """video"""))
        

// @LINE:19
private[this] lazy val controllers_Application_findImage6_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("test/"),DynamicPart("img_no", """[^/]+""",true))))
private[this] lazy val controllers_Application_findImage6_invoker = createInvoker(
controllers.Application.findImage(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "findImage", Seq(classOf[Int]),"GET", """""", Routes.prefix + """test/$img_no<[^/]+>"""))
        

// @LINE:21
private[this] lazy val controllers_Assets_at7_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at7_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """assets/$file<.+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """person""","""controllers.Application.addPerson"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """persons""","""controllers.Application.getPersons"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """test""","""controllers.Application.test"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """file""","""controllers.Application.file"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """video""","""controllers.Application.video"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """test/$img_no<[^/]+>""","""controllers.Application.findImage(img_no:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0_route(params) => {
   call { 
        controllers_Application_index0_invoker.call(controllers.Application.index)
   }
}
        

// @LINE:8
case controllers_Application_addPerson1_route(params) => {
   call { 
        controllers_Application_addPerson1_invoker.call(controllers.Application.addPerson)
   }
}
        

// @LINE:11
case controllers_Application_getPersons2_route(params) => {
   call { 
        controllers_Application_getPersons2_invoker.call(controllers.Application.getPersons)
   }
}
        

// @LINE:13
case controllers_Application_test3_route(params) => {
   call { 
        controllers_Application_test3_invoker.call(controllers.Application.test)
   }
}
        

// @LINE:15
case controllers_Application_file4_route(params) => {
   call { 
        controllers_Application_file4_invoker.call(controllers.Application.file)
   }
}
        

// @LINE:17
case controllers_Application_video5_route(params) => {
   call { 
        controllers_Application_video5_invoker.call(controllers.Application.video)
   }
}
        

// @LINE:19
case controllers_Application_findImage6_route(params) => {
   call(params.fromPath[Int]("img_no", None)) { (img_no) =>
        controllers_Application_findImage6_invoker.call(controllers.Application.findImage(img_no))
   }
}
        

// @LINE:21
case controllers_Assets_at7_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at7_invoker.call(controllers.Assets.at(path, file))
   }
}
        
}

}
     