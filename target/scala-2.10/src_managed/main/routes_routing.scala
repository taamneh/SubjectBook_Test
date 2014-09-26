// @SOURCE:C:/Users/staamneh/Desktop/CPL-Lab/System Desgin/first_play/conf/routes
// @HASH:fd295015f99af27c80fc46a80607b75c22ab9063
// @DATE:Thu Sep 25 23:51:32 CDT 2014


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
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("index"))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
controllers.Application.index,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """index"""))
        

// @LINE:11
private[this] lazy val controllers_Application_test1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("test"))))
private[this] lazy val controllers_Application_test1_invoker = createInvoker(
controllers.Application.test(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "test", Seq(classOf[String], classOf[String]),"GET", """change the name""", Routes.prefix + """test"""))
        

// @LINE:14
private[this] lazy val controllers_Application_file2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("file"))))
private[this] lazy val controllers_Application_file2_invoker = createInvoker(
controllers.Application.file,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "file", Nil,"GET", """to be deleted""", Routes.prefix + """file"""))
        

// @LINE:16
private[this] lazy val controllers_Application_Login3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_Login3_invoker = createInvoker(
controllers.Application.Login,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Login", Nil,"GET", """""", Routes.prefix + """"""))
        

// @LINE:18
private[this] lazy val controllers_Application_authentication4_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
private[this] lazy val controllers_Application_authentication4_invoker = createInvoker(
controllers.Application.authentication,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "authentication", Nil,"POST", """""", Routes.prefix + """login"""))
        

// @LINE:20
private[this] lazy val controllers_Application_logout5_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
private[this] lazy val controllers_Application_logout5_invoker = createInvoker(
controllers.Application.logout,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "logout", Nil,"POST", """""", Routes.prefix + """logout"""))
        

// @LINE:22
private[this] lazy val controllers_Application_Register6_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signup"))))
private[this] lazy val controllers_Application_Register6_invoker = createInvoker(
controllers.Application.Register,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Register", Nil,"GET", """""", Routes.prefix + """signup"""))
        

// @LINE:24
private[this] lazy val controllers_Application_submitRegistration7_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signup"))))
private[this] lazy val controllers_Application_submitRegistration7_invoker = createInvoker(
controllers.Application.submitRegistration,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "submitRegistration", Nil,"POST", """""", Routes.prefix + """signup"""))
        

// @LINE:26
private[this] lazy val controllers_Application_shareMyStudy8_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("editStudy"))))
private[this] lazy val controllers_Application_shareMyStudy8_invoker = createInvoker(
controllers.Application.shareMyStudy,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "shareMyStudy", Nil,"POST", """""", Routes.prefix + """editStudy"""))
        

// @LINE:28
private[this] lazy val controllers_Application_displayStudies9_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("allStudies"))))
private[this] lazy val controllers_Application_displayStudies9_invoker = createInvoker(
controllers.Application.displayStudies,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displayStudies", Nil,"POST", """""", Routes.prefix + """allStudies"""))
        

// @LINE:30
private[this] lazy val controllers_Application_displaySubject10_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("displaySubject"))))
private[this] lazy val controllers_Application_displaySubject10_invoker = createInvoker(
controllers.Application.displaySubject(fakeValue[Int], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displaySubject", Seq(classOf[Int], classOf[String]),"GET", """""", Routes.prefix + """displaySubject"""))
        

// @LINE:32
private[this] lazy val controllers_Application_displayFortest11_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("display"))))
private[this] lazy val controllers_Application_displayFortest11_invoker = createInvoker(
controllers.Application.displayFortest,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displayFortest", Nil,"GET", """""", Routes.prefix + """display"""))
        

// @LINE:35
private[this] lazy val controllers_Application_video12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("video"))))
private[this] lazy val controllers_Application_video12_invoker = createInvoker(
controllers.Application.video,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "video", Nil,"GET", """to be deleted""", Routes.prefix + """video"""))
        

// @LINE:37
private[this] lazy val controllers_Application_editStudy13_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("editStudy/"),DynamicPart("studyId", """[^/]+""",true))))
private[this] lazy val controllers_Application_editStudy13_invoker = createInvoker(
controllers.Application.editStudy(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "editStudy", Seq(classOf[Int]),"GET", """""", Routes.prefix + """editStudy/$studyId<[^/]+>"""))
        

// @LINE:40
private[this] lazy val controllers_Application_findImage14_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("test/"),DynamicPart("img_no", """[^/]+""",true))))
private[this] lazy val controllers_Application_findImage14_invoker = createInvoker(
controllers.Application.findImage(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "findImage", Seq(classOf[Int]),"GET", """to be deleted""", Routes.prefix + """test/$img_no<[^/]+>"""))
        

// @LINE:42
private[this] lazy val controllers_Assets_at15_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at15_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:47
private[this] lazy val controllers_Application_showStudy16_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showStudy/"),DynamicPart("studyNo", """[^/]+""",true))))
private[this] lazy val controllers_Application_showStudy16_invoker = createInvoker(
controllers.Application.showStudy(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showStudy", Seq(classOf[Int]),"GET", """""", Routes.prefix + """showStudy/$studyNo<[^/]+>"""))
        
def documentation = List(("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """index""","""controllers.Application.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """test""","""controllers.Application.test(task:String, subject:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """file""","""controllers.Application.file"""),("""GET""", prefix,"""controllers.Application.Login"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.authentication"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.Application.logout"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""","""controllers.Application.Register"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""","""controllers.Application.submitRegistration"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """editStudy""","""controllers.Application.shareMyStudy"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """allStudies""","""controllers.Application.displayStudies"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """displaySubject""","""controllers.Application.displaySubject(studyNo:Int, SubjectId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """display""","""controllers.Application.displayFortest"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """video""","""controllers.Application.video"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """editStudy/$studyId<[^/]+>""","""controllers.Application.editStudy(studyId:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """test/$img_no<[^/]+>""","""controllers.Application.findImage(img_no:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showStudy/$studyNo<[^/]+>""","""controllers.Application.showStudy(studyNo:Int)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
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
        

// @LINE:11
case controllers_Application_test1_route(params) => {
   call(params.fromQuery[String]("task", None), params.fromQuery[String]("subject", None)) { (task, subject) =>
        controllers_Application_test1_invoker.call(controllers.Application.test(task, subject))
   }
}
        

// @LINE:14
case controllers_Application_file2_route(params) => {
   call { 
        controllers_Application_file2_invoker.call(controllers.Application.file)
   }
}
        

// @LINE:16
case controllers_Application_Login3_route(params) => {
   call { 
        controllers_Application_Login3_invoker.call(controllers.Application.Login)
   }
}
        

// @LINE:18
case controllers_Application_authentication4_route(params) => {
   call { 
        controllers_Application_authentication4_invoker.call(controllers.Application.authentication)
   }
}
        

// @LINE:20
case controllers_Application_logout5_route(params) => {
   call { 
        controllers_Application_logout5_invoker.call(controllers.Application.logout)
   }
}
        

// @LINE:22
case controllers_Application_Register6_route(params) => {
   call { 
        controllers_Application_Register6_invoker.call(controllers.Application.Register)
   }
}
        

// @LINE:24
case controllers_Application_submitRegistration7_route(params) => {
   call { 
        controllers_Application_submitRegistration7_invoker.call(controllers.Application.submitRegistration)
   }
}
        

// @LINE:26
case controllers_Application_shareMyStudy8_route(params) => {
   call { 
        controllers_Application_shareMyStudy8_invoker.call(controllers.Application.shareMyStudy)
   }
}
        

// @LINE:28
case controllers_Application_displayStudies9_route(params) => {
   call { 
        controllers_Application_displayStudies9_invoker.call(controllers.Application.displayStudies)
   }
}
        

// @LINE:30
case controllers_Application_displaySubject10_route(params) => {
   call(params.fromQuery[Int]("studyNo", None), params.fromQuery[String]("SubjectId", None)) { (studyNo, SubjectId) =>
        controllers_Application_displaySubject10_invoker.call(controllers.Application.displaySubject(studyNo, SubjectId))
   }
}
        

// @LINE:32
case controllers_Application_displayFortest11_route(params) => {
   call { 
        controllers_Application_displayFortest11_invoker.call(controllers.Application.displayFortest)
   }
}
        

// @LINE:35
case controllers_Application_video12_route(params) => {
   call { 
        controllers_Application_video12_invoker.call(controllers.Application.video)
   }
}
        

// @LINE:37
case controllers_Application_editStudy13_route(params) => {
   call(params.fromPath[Int]("studyId", None)) { (studyId) =>
        controllers_Application_editStudy13_invoker.call(controllers.Application.editStudy(studyId))
   }
}
        

// @LINE:40
case controllers_Application_findImage14_route(params) => {
   call(params.fromPath[Int]("img_no", None)) { (img_no) =>
        controllers_Application_findImage14_invoker.call(controllers.Application.findImage(img_no))
   }
}
        

// @LINE:42
case controllers_Assets_at15_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at15_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:47
case controllers_Application_showStudy16_route(params) => {
   call(params.fromPath[Int]("studyNo", None)) { (studyNo) =>
        controllers_Application_showStudy16_invoker.call(controllers.Application.showStudy(studyNo))
   }
}
        
}

}
     