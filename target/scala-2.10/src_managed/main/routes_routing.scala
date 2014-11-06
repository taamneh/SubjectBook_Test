// @SOURCE:C:/Users/staamneh/Desktop/CPL-Lab/System Desgin/first_play/conf/routes
// @HASH:cb47c338536c883d1798de3140e0fbd99237874a
// @DATE:Wed Nov 05 19:32:26 CST 2014


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


// @LINE:7
private[this] lazy val controllers_Application_Main0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_Main0_invoker = createInvoker(
controllers.Application.Main,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Main", Nil,"GET", """""", Routes.prefix + """"""))
        

// @LINE:9
private[this] lazy val controllers_Application_ReceiveOauthData1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("display"))))
private[this] lazy val controllers_Application_ReceiveOauthData1_invoker = createInvoker(
controllers.Application.ReceiveOauthData(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ReceiveOauthData", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """display"""))
        

// @LINE:11
private[this] lazy val controllers_Application_getInfo2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getInfo"))))
private[this] lazy val controllers_Application_getInfo2_invoker = createInvoker(
controllers.Application.getInfo(fakeValue[String], fakeValue[String], fakeValue[Int], fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getInfo", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int]),"GET", """""", Routes.prefix + """getInfo"""))
        

// @LINE:13
private[this] lazy val controllers_Application_getPsycho3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getPsycho"))))
private[this] lazy val controllers_Application_getPsycho3_invoker = createInvoker(
controllers.Application.getPsycho(fakeValue[String], fakeValue[String], fakeValue[Int], fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getPsycho", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int]),"GET", """""", Routes.prefix + """getPsycho"""))
        

// @LINE:15
private[this] lazy val controllers_Application_GetSignal4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("GetSignal"))))
private[this] lazy val controllers_Application_GetSignal4_invoker = createInvoker(
controllers.Application.GetSignal(fakeValue[String], fakeValue[String], fakeValue[Int], fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "GetSignal", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int]),"GET", """""", Routes.prefix + """GetSignal"""))
        

// @LINE:17
private[this] lazy val controllers_Application_displayStudies5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("allStudies"))))
private[this] lazy val controllers_Application_displayStudies5_invoker = createInvoker(
controllers.Application.displayStudies,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displayStudies", Nil,"GET", """""", Routes.prefix + """allStudies"""))
        

// @LINE:19
private[this] lazy val controllers_Application_showStudy6_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showStudy/"),DynamicPart("studyNo", """[^/]+""",true))))
private[this] lazy val controllers_Application_showStudy6_invoker = createInvoker(
controllers.Application.showStudy(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showStudy", Seq(classOf[Int]),"GET", """""", Routes.prefix + """showStudy/$studyNo<[^/]+>"""))
        

// @LINE:21
private[this] lazy val controllers_Application_displaySubject7_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("displaySubject"))))
private[this] lazy val controllers_Application_displaySubject7_invoker = createInvoker(
controllers.Application.displaySubject(fakeValue[Int], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displaySubject", Seq(classOf[Int], classOf[String]),"GET", """""", Routes.prefix + """displaySubject"""))
        

// @LINE:23
private[this] lazy val controllers_Application_ShowCreateStudyForm8_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("createStudy"))))
private[this] lazy val controllers_Application_ShowCreateStudyForm8_invoker = createInvoker(
controllers.Application.ShowCreateStudyForm(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ShowCreateStudyForm", Nil,"GET", """""", Routes.prefix + """createStudy"""))
        

// @LINE:25
private[this] lazy val controllers_Application_InsertNewStudy9_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("createStudy"))))
private[this] lazy val controllers_Application_InsertNewStudy9_invoker = createInvoker(
controllers.Application.InsertNewStudy(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InsertNewStudy", Nil,"POST", """""", Routes.prefix + """createStudy"""))
        

// @LINE:27
private[this] lazy val controllers_Application_logout10_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
private[this] lazy val controllers_Application_logout10_invoker = createInvoker(
controllers.Application.logout,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "logout", Nil,"POST", """""", Routes.prefix + """logout"""))
        

// @LINE:29
private[this] lazy val controllers_Assets_at11_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at11_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:34
private[this] lazy val controllers_Application_getVideo12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("videoB"))))
private[this] lazy val controllers_Application_getVideo12_invoker = createInvoker(
controllers.Application.getVideo(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getVideo", Nil,"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """videoB"""))
        

// @LINE:36
private[this] lazy val controllers_Application_getVideo213_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("video"))))
private[this] lazy val controllers_Application_getVideo213_invoker = createInvoker(
controllers.Application.getVideo2(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getVideo2", Nil,"GET", """""", Routes.prefix + """video"""))
        

// @LINE:39
private[this] lazy val controllers_Application_file14_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("file"))))
private[this] lazy val controllers_Application_file14_invoker = createInvoker(
controllers.Application.file,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "file", Nil,"GET", """ Orphans...""", Routes.prefix + """file"""))
        

// @LINE:41
private[this] lazy val controllers_Application_authentication15_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
private[this] lazy val controllers_Application_authentication15_invoker = createInvoker(
controllers.Application.authentication,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "authentication", Nil,"GET", """""", Routes.prefix + """login"""))
        

// @LINE:43
private[this] lazy val controllers_Application_authentication16_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
private[this] lazy val controllers_Application_authentication16_invoker = createInvoker(
controllers.Application.authentication,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "authentication", Nil,"POST", """""", Routes.prefix + """login"""))
        

// @LINE:45
private[this] lazy val controllers_Application_ShowDriveDialog17_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ShowDriveDialog"))))
private[this] lazy val controllers_Application_ShowDriveDialog17_invoker = createInvoker(
controllers.Application.ShowDriveDialog(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ShowDriveDialog", Nil,"GET", """""", Routes.prefix + """ShowDriveDialog"""))
        

// @LINE:47
private[this] lazy val controllers_Application_Register18_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signup"))))
private[this] lazy val controllers_Application_Register18_invoker = createInvoker(
controllers.Application.Register,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Register", Nil,"GET", """""", Routes.prefix + """signup"""))
        

// @LINE:49
private[this] lazy val controllers_Application_submitRegistration19_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signup"))))
private[this] lazy val controllers_Application_submitRegistration19_invoker = createInvoker(
controllers.Application.submitRegistration,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "submitRegistration", Nil,"POST", """""", Routes.prefix + """signup"""))
        

// @LINE:51
private[this] lazy val controllers_Application_shareMyStudy20_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("InsertStudy"))))
private[this] lazy val controllers_Application_shareMyStudy20_invoker = createInvoker(
controllers.Application.shareMyStudy,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "shareMyStudy", Nil,"POST", """""", Routes.prefix + """InsertStudy"""))
        

// @LINE:53
private[this] lazy val controllers_Application_insertSubject21_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("InsertSubject"))))
private[this] lazy val controllers_Application_insertSubject21_invoker = createInvoker(
controllers.Application.insertSubject,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "insertSubject", Nil,"POST", """""", Routes.prefix + """InsertSubject"""))
        

// @LINE:55
private[this] lazy val controllers_Application_InsertSession22_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("InsertSession"))))
private[this] lazy val controllers_Application_InsertSession22_invoker = createInvoker(
controllers.Application.InsertSession,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InsertSession", Nil,"POST", """""", Routes.prefix + """InsertSession"""))
        

// @LINE:57
private[this] lazy val controllers_Application_editSubject23_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("editSubject"))))
private[this] lazy val controllers_Application_editSubject23_invoker = createInvoker(
controllers.Application.editSubject(fakeValue[Int], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "editSubject", Seq(classOf[Int], classOf[String]),"GET", """""", Routes.prefix + """editSubject"""))
        

// @LINE:59
private[this] lazy val controllers_Application_uploadFile24_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("upload"))))
private[this] lazy val controllers_Application_uploadFile24_invoker = createInvoker(
controllers.Application.uploadFile,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "uploadFile", Nil,"POST", """""", Routes.prefix + """upload"""))
        

// @LINE:62
private[this] lazy val controllers_Application_video25_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("video"))))
private[this] lazy val controllers_Application_video25_invoker = createInvoker(
controllers.Application.video,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "video", Nil,"GET", """to be deleted""", Routes.prefix + """video"""))
        

// @LINE:64
private[this] lazy val controllers_Application_editStudy26_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("editStudy/"),DynamicPart("studyId", """[^/]+""",true))))
private[this] lazy val controllers_Application_editStudy26_invoker = createInvoker(
controllers.Application.editStudy(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "editStudy", Seq(classOf[Int]),"GET", """""", Routes.prefix + """editStudy/$studyId<[^/]+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.Main"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """display""","""controllers.Application.ReceiveOauthData(state:String, code:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getInfo""","""controllers.Application.getInfo(task:String, subject:String, studyId:Int, signal_type:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getPsycho""","""controllers.Application.getPsycho(task:String, subject:String, studyId:Int, signal_type:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """GetSignal""","""controllers.Application.GetSignal(task:String, subject:String, studyId:Int, signal_type:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """allStudies""","""controllers.Application.displayStudies"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showStudy/$studyNo<[^/]+>""","""controllers.Application.showStudy(studyNo:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """displaySubject""","""controllers.Application.displaySubject(studyNo:Int, SubjectId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """createStudy""","""controllers.Application.ShowCreateStudyForm()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """createStudy""","""controllers.Application.InsertNewStudy()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.Application.logout"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """videoB""","""controllers.Application.getVideo()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """video""","""controllers.Application.getVideo2()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """file""","""controllers.Application.file"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.authentication"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.authentication"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ShowDriveDialog""","""controllers.Application.ShowDriveDialog()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""","""controllers.Application.Register"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""","""controllers.Application.submitRegistration"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """InsertStudy""","""controllers.Application.shareMyStudy"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """InsertSubject""","""controllers.Application.insertSubject"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """InsertSession""","""controllers.Application.InsertSession"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """editSubject""","""controllers.Application.editSubject(studyNo:Int, SubjectId:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """upload""","""controllers.Application.uploadFile"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """video""","""controllers.Application.video"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """editStudy/$studyId<[^/]+>""","""controllers.Application.editStudy(studyId:Int)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:7
case controllers_Application_Main0_route(params) => {
   call { 
        controllers_Application_Main0_invoker.call(controllers.Application.Main)
   }
}
        

// @LINE:9
case controllers_Application_ReceiveOauthData1_route(params) => {
   call(params.fromQuery[String]("state", None), params.fromQuery[String]("code", None)) { (state, code) =>
        controllers_Application_ReceiveOauthData1_invoker.call(controllers.Application.ReceiveOauthData(state, code))
   }
}
        

// @LINE:11
case controllers_Application_getInfo2_route(params) => {
   call(params.fromQuery[String]("task", None), params.fromQuery[String]("subject", None), params.fromQuery[Int]("studyId", None), params.fromQuery[Int]("signal_type", None)) { (task, subject, studyId, signal_type) =>
        controllers_Application_getInfo2_invoker.call(controllers.Application.getInfo(task, subject, studyId, signal_type))
   }
}
        

// @LINE:13
case controllers_Application_getPsycho3_route(params) => {
   call(params.fromQuery[String]("task", None), params.fromQuery[String]("subject", None), params.fromQuery[Int]("studyId", None), params.fromQuery[Int]("signal_type", None)) { (task, subject, studyId, signal_type) =>
        controllers_Application_getPsycho3_invoker.call(controllers.Application.getPsycho(task, subject, studyId, signal_type))
   }
}
        

// @LINE:15
case controllers_Application_GetSignal4_route(params) => {
   call(params.fromQuery[String]("task", None), params.fromQuery[String]("subject", None), params.fromQuery[Int]("studyId", None), params.fromQuery[Int]("signal_type", None)) { (task, subject, studyId, signal_type) =>
        controllers_Application_GetSignal4_invoker.call(controllers.Application.GetSignal(task, subject, studyId, signal_type))
   }
}
        

// @LINE:17
case controllers_Application_displayStudies5_route(params) => {
   call { 
        controllers_Application_displayStudies5_invoker.call(controllers.Application.displayStudies)
   }
}
        

// @LINE:19
case controllers_Application_showStudy6_route(params) => {
   call(params.fromPath[Int]("studyNo", None)) { (studyNo) =>
        controllers_Application_showStudy6_invoker.call(controllers.Application.showStudy(studyNo))
   }
}
        

// @LINE:21
case controllers_Application_displaySubject7_route(params) => {
   call(params.fromQuery[Int]("studyNo", None), params.fromQuery[String]("SubjectId", None)) { (studyNo, SubjectId) =>
        controllers_Application_displaySubject7_invoker.call(controllers.Application.displaySubject(studyNo, SubjectId))
   }
}
        

// @LINE:23
case controllers_Application_ShowCreateStudyForm8_route(params) => {
   call { 
        controllers_Application_ShowCreateStudyForm8_invoker.call(controllers.Application.ShowCreateStudyForm())
   }
}
        

// @LINE:25
case controllers_Application_InsertNewStudy9_route(params) => {
   call { 
        controllers_Application_InsertNewStudy9_invoker.call(controllers.Application.InsertNewStudy())
   }
}
        

// @LINE:27
case controllers_Application_logout10_route(params) => {
   call { 
        controllers_Application_logout10_invoker.call(controllers.Application.logout)
   }
}
        

// @LINE:29
case controllers_Assets_at11_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at11_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:34
case controllers_Application_getVideo12_route(params) => {
   call { 
        controllers_Application_getVideo12_invoker.call(controllers.Application.getVideo())
   }
}
        

// @LINE:36
case controllers_Application_getVideo213_route(params) => {
   call { 
        controllers_Application_getVideo213_invoker.call(controllers.Application.getVideo2())
   }
}
        

// @LINE:39
case controllers_Application_file14_route(params) => {
   call { 
        controllers_Application_file14_invoker.call(controllers.Application.file)
   }
}
        

// @LINE:41
case controllers_Application_authentication15_route(params) => {
   call { 
        controllers_Application_authentication15_invoker.call(controllers.Application.authentication)
   }
}
        

// @LINE:43
case controllers_Application_authentication16_route(params) => {
   call { 
        controllers_Application_authentication16_invoker.call(controllers.Application.authentication)
   }
}
        

// @LINE:45
case controllers_Application_ShowDriveDialog17_route(params) => {
   call { 
        controllers_Application_ShowDriveDialog17_invoker.call(controllers.Application.ShowDriveDialog())
   }
}
        

// @LINE:47
case controllers_Application_Register18_route(params) => {
   call { 
        controllers_Application_Register18_invoker.call(controllers.Application.Register)
   }
}
        

// @LINE:49
case controllers_Application_submitRegistration19_route(params) => {
   call { 
        controllers_Application_submitRegistration19_invoker.call(controllers.Application.submitRegistration)
   }
}
        

// @LINE:51
case controllers_Application_shareMyStudy20_route(params) => {
   call { 
        controllers_Application_shareMyStudy20_invoker.call(controllers.Application.shareMyStudy)
   }
}
        

// @LINE:53
case controllers_Application_insertSubject21_route(params) => {
   call { 
        controllers_Application_insertSubject21_invoker.call(controllers.Application.insertSubject)
   }
}
        

// @LINE:55
case controllers_Application_InsertSession22_route(params) => {
   call { 
        controllers_Application_InsertSession22_invoker.call(controllers.Application.InsertSession)
   }
}
        

// @LINE:57
case controllers_Application_editSubject23_route(params) => {
   call(params.fromQuery[Int]("studyNo", None), params.fromQuery[String]("SubjectId", None)) { (studyNo, SubjectId) =>
        controllers_Application_editSubject23_invoker.call(controllers.Application.editSubject(studyNo, SubjectId))
   }
}
        

// @LINE:59
case controllers_Application_uploadFile24_route(params) => {
   call { 
        controllers_Application_uploadFile24_invoker.call(controllers.Application.uploadFile)
   }
}
        

// @LINE:62
case controllers_Application_video25_route(params) => {
   call { 
        controllers_Application_video25_invoker.call(controllers.Application.video)
   }
}
        

// @LINE:64
case controllers_Application_editStudy26_route(params) => {
   call(params.fromPath[Int]("studyId", None)) { (studyId) =>
        controllers_Application_editStudy26_invoker.call(controllers.Application.editStudy(studyId))
   }
}
        
}

}
     