// @SOURCE:C:/first_play/conf/routes
// @HASH:2b107f31272dcba088a44cb66951e52267c07ed5
// @DATE:Wed Dec 09 22:04:09 CST 2015


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


// @LINE:5
private[this] lazy val controllers_Application_socket0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ws"))))
private[this] lazy val controllers_Application_socket0_invoker = createInvoker(
controllers.Application.socket,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "socket", Nil,"GET", """ Routes
 This file defines all application routes (Higher priority routes first)
 ~~~~
Web Socket management""", Routes.prefix + """ws"""))
        

// @LINE:9
private[this] lazy val controllers_Application_Front1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_Front1_invoker = createInvoker(
controllers.Application.Front,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Front", Nil,"GET", """""", Routes.prefix + """"""))
        

// @LINE:11
private[this] lazy val controllers_Application_Main2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("home"))))
private[this] lazy val controllers_Application_Main2_invoker = createInvoker(
controllers.Application.Main,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Main", Nil,"GET", """""", Routes.prefix + """home"""))
        

// @LINE:13
private[this] lazy val controllers_Application_ReceiveOauthData3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("display"))))
private[this] lazy val controllers_Application_ReceiveOauthData3_invoker = createInvoker(
controllers.Application.ReceiveOauthData(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ReceiveOauthData", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """display"""))
        

// @LINE:15
private[this] lazy val controllers_Application_getPortraitInfo4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("portrait"))))
private[this] lazy val controllers_Application_getPortraitInfo4_invoker = createInvoker(
controllers.Application.getPortraitInfo(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getPortraitInfo", Seq(classOf[Int]),"GET", """""", Routes.prefix + """portrait"""))
        

// @LINE:17
private[this] lazy val controllers_Application_getInfo5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getInfo"))))
private[this] lazy val controllers_Application_getInfo5_invoker = createInvoker(
controllers.Application.getInfo(fakeValue[String], fakeValue[String], fakeValue[Int], fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getInfo", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int]),"GET", """""", Routes.prefix + """getInfo"""))
        

// @LINE:19
private[this] lazy val controllers_Application_getPRF6_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getPRF"))))
private[this] lazy val controllers_Application_getPRF6_invoker = createInvoker(
controllers.Application.getPRF(fakeValue[String], fakeValue[String], fakeValue[Int], fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getPRF", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int]),"GET", """""", Routes.prefix + """getPRF"""))
        

// @LINE:21
private[this] lazy val controllers_Application_getPsycho7_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getPsycho"))))
private[this] lazy val controllers_Application_getPsycho7_invoker = createInvoker(
controllers.Application.getPsycho(fakeValue[String], fakeValue[String], fakeValue[Int], fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getPsycho", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int]),"GET", """""", Routes.prefix + """getPsycho"""))
        

// @LINE:23
private[this] lazy val controllers_Application_GetSignal8_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("GetSignal"))))
private[this] lazy val controllers_Application_GetSignal8_invoker = createInvoker(
controllers.Application.GetSignal(fakeValue[String], fakeValue[String], fakeValue[Int], fakeValue[Int], fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "GetSignal", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int], classOf[Int]),"GET", """""", Routes.prefix + """GetSignal"""))
        

// @LINE:25
private[this] lazy val controllers_Application_file9_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("file"))))
private[this] lazy val controllers_Application_file9_invoker = createInvoker(
controllers.Application.file(fakeValue[String], fakeValue[String], fakeValue[Int], fakeValue[Int], fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "file", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int], classOf[Int]),"GET", """""", Routes.prefix + """file"""))
        

// @LINE:27
private[this] lazy val controllers_Application_zip10_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("downloadStudy"))))
private[this] lazy val controllers_Application_zip10_invoker = createInvoker(
controllers.Application.zip(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "zip", Seq(classOf[Int]),"GET", """""", Routes.prefix + """downloadStudy"""))
        

// @LINE:29
private[this] lazy val controllers_Application_displayStudies11_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("allStudies"))))
private[this] lazy val controllers_Application_displayStudies11_invoker = createInvoker(
controllers.Application.displayStudies,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displayStudies", Nil,"GET", """""", Routes.prefix + """allStudies"""))
        

// @LINE:31
private[this] lazy val controllers_Application_showPyramid12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showPyramid"))))
private[this] lazy val controllers_Application_showPyramid12_invoker = createInvoker(
controllers.Application.showPyramid(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showPyramid", Seq(classOf[Int]),"GET", """""", Routes.prefix + """showPyramid"""))
        

// @LINE:33
private[this] lazy val controllers_Application_showStudy13_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showStudy/"),DynamicPart("studyNo", """[^/]+""",true))))
private[this] lazy val controllers_Application_showStudy13_invoker = createInvoker(
controllers.Application.showStudy(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showStudy", Seq(classOf[Int]),"GET", """""", Routes.prefix + """showStudy/$studyNo<[^/]+>"""))
        

// @LINE:35
private[this] lazy val controllers_Application_showStudySkipTop14_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showStudySkipTop/"),DynamicPart("studyNo", """[^/]+""",true))))
private[this] lazy val controllers_Application_showStudySkipTop14_invoker = createInvoker(
controllers.Application.showStudySkipTop(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showStudySkipTop", Seq(classOf[Int]),"GET", """""", Routes.prefix + """showStudySkipTop/$studyNo<[^/]+>"""))
        

// @LINE:37
private[this] lazy val controllers_Application_displaySubject15_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("displaySubject"))))
private[this] lazy val controllers_Application_displaySubject15_invoker = createInvoker(
controllers.Application.displaySubject(fakeValue[Int], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displaySubject", Seq(classOf[Int], classOf[String]),"GET", """""", Routes.prefix + """displaySubject"""))
        

// @LINE:39
private[this] lazy val controllers_Application_showRadar16_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showRadar"))))
private[this] lazy val controllers_Application_showRadar16_invoker = createInvoker(
controllers.Application.showRadar(fakeValue[Int], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showRadar", Seq(classOf[Int], classOf[String]),"GET", """""", Routes.prefix + """showRadar"""))
        

// @LINE:41
private[this] lazy val controllers_Application_getRadar17_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getRadar"))))
private[this] lazy val controllers_Application_getRadar17_invoker = createInvoker(
controllers.Application.getRadar(fakeValue[Int], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getRadar", Seq(classOf[Int], classOf[String]),"GET", """""", Routes.prefix + """getRadar"""))
        

// @LINE:43
private[this] lazy val controllers_Application_ShowCreateStudyForm18_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("createStudy"))))
private[this] lazy val controllers_Application_ShowCreateStudyForm18_invoker = createInvoker(
controllers.Application.ShowCreateStudyForm(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ShowCreateStudyForm", Nil,"GET", """""", Routes.prefix + """createStudy"""))
        

// @LINE:45
private[this] lazy val controllers_Application_InsertNewStudy19_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("createStudy"))))
private[this] lazy val controllers_Application_InsertNewStudy19_invoker = createInvoker(
controllers.Application.InsertNewStudy(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InsertNewStudy", Nil,"POST", """""", Routes.prefix + """createStudy"""))
        

// @LINE:47
private[this] lazy val controllers_Application_deleteStudy20_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("deleteStudy"))))
private[this] lazy val controllers_Application_deleteStudy20_invoker = createInvoker(
controllers.Application.deleteStudy(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "deleteStudy", Nil,"POST", """""", Routes.prefix + """deleteStudy"""))
        

// @LINE:49
private[this] lazy val controllers_Application_ProcessNewDataType21_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("processNewDataType"))))
private[this] lazy val controllers_Application_ProcessNewDataType21_invoker = createInvoker(
controllers.Application.ProcessNewDataType(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ProcessNewDataType", Nil,"POST", """""", Routes.prefix + """processNewDataType"""))
        

// @LINE:51
private[this] lazy val controllers_Application_ProcessNewPsychometric22_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("processNewPsychometric"))))
private[this] lazy val controllers_Application_ProcessNewPsychometric22_invoker = createInvoker(
controllers.Application.ProcessNewPsychometric(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ProcessNewPsychometric", Nil,"POST", """""", Routes.prefix + """processNewPsychometric"""))
        

// @LINE:53
private[this] lazy val controllers_Application_ProcessNewTopSummary23_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ProcessNewTopSummary"))))
private[this] lazy val controllers_Application_ProcessNewTopSummary23_invoker = createInvoker(
controllers.Application.ProcessNewTopSummary(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ProcessNewTopSummary", Nil,"POST", """""", Routes.prefix + """ProcessNewTopSummary"""))
        

// @LINE:55
private[this] lazy val controllers_Application_EditDataType24_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("editExistingDataType"))))
private[this] lazy val controllers_Application_EditDataType24_invoker = createInvoker(
controllers.Application.EditDataType(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "EditDataType", Nil,"POST", """""", Routes.prefix + """editExistingDataType"""))
        

// @LINE:57
private[this] lazy val controllers_Application_EditPsychometric25_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("editPsychometric"))))
private[this] lazy val controllers_Application_EditPsychometric25_invoker = createInvoker(
controllers.Application.EditPsychometric(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "EditPsychometric", Nil,"POST", """""", Routes.prefix + """editPsychometric"""))
        

// @LINE:59
private[this] lazy val controllers_Application_logout26_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
private[this] lazy val controllers_Application_logout26_invoker = createInvoker(
controllers.Application.logout,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "logout", Nil,"POST", """""", Routes.prefix + """logout"""))
        

// @LINE:61
private[this] lazy val controllers_Application_Front27_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("frontPage"))))
private[this] lazy val controllers_Application_Front27_invoker = createInvoker(
controllers.Application.Front,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Front", Nil,"GET", """""", Routes.prefix + """frontPage"""))
        

// @LINE:63
private[this] lazy val controllers_Application_setUpStudy28_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("setUpStudy"))))
private[this] lazy val controllers_Application_setUpStudy28_invoker = createInvoker(
controllers.Application.setUpStudy,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "setUpStudy", Nil,"GET", """""", Routes.prefix + """setUpStudy"""))
        

// @LINE:65
private[this] lazy val controllers_Application_howToCreateNewStudy29_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("howToCreateNewStudy"))))
private[this] lazy val controllers_Application_howToCreateNewStudy29_invoker = createInvoker(
controllers.Application.howToCreateNewStudy,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "howToCreateNewStudy", Nil,"GET", """""", Routes.prefix + """howToCreateNewStudy"""))
        

// @LINE:67
private[this] lazy val controllers_Application_showAddDataType30_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showAddDataType"))))
private[this] lazy val controllers_Application_showAddDataType30_invoker = createInvoker(
controllers.Application.showAddDataType,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAddDataType", Nil,"GET", """""", Routes.prefix + """showAddDataType"""))
        

// @LINE:69
private[this] lazy val controllers_Application_showAddPsychometric31_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showAddPsychometric"))))
private[this] lazy val controllers_Application_showAddPsychometric31_invoker = createInvoker(
controllers.Application.showAddPsychometric,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAddPsychometric", Nil,"GET", """""", Routes.prefix + """showAddPsychometric"""))
        

// @LINE:71
private[this] lazy val controllers_Application_showAddTopSummary32_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showAddTopSummary"))))
private[this] lazy val controllers_Application_showAddTopSummary32_invoker = createInvoker(
controllers.Application.showAddTopSummary(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAddTopSummary", Seq(classOf[Int]),"GET", """""", Routes.prefix + """showAddTopSummary"""))
        

// @LINE:73
private[this] lazy val controllers_Application_showAllDataTypes33_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showAllDataTypes"))))
private[this] lazy val controllers_Application_showAllDataTypes33_invoker = createInvoker(
controllers.Application.showAllDataTypes,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAllDataTypes", Nil,"GET", """""", Routes.prefix + """showAllDataTypes"""))
        

// @LINE:78
private[this] lazy val controllers_Application_Getdummy34_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getDummy"))))
private[this] lazy val controllers_Application_Getdummy34_invoker = createInvoker(
controllers.Application.Getdummy(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Getdummy", Nil,"GET", """""", Routes.prefix + """getDummy"""))
        

// @LINE:80
private[this] lazy val controllers_Application_showAllPsychometric35_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showAllPsychometric"))))
private[this] lazy val controllers_Application_showAllPsychometric35_invoker = createInvoker(
controllers.Application.showAllPsychometric,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAllPsychometric", Nil,"GET", """""", Routes.prefix + """showAllPsychometric"""))
        

// @LINE:82
private[this] lazy val controllers_Assets_at36_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at36_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:85
private[this] lazy val controllers_Application_signalDataExample37_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signalDataExample"))))
private[this] lazy val controllers_Application_signalDataExample37_invoker = createInvoker(
controllers.Application.signalDataExample(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "signalDataExample", Nil,"GET", """""", Routes.prefix + """signalDataExample"""))
        

// @LINE:87
private[this] lazy val controllers_Application_InfoExample38_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("InfoExample"))))
private[this] lazy val controllers_Application_InfoExample38_invoker = createInvoker(
controllers.Application.InfoExample(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InfoExample", Nil,"GET", """""", Routes.prefix + """InfoExample"""))
        

// @LINE:89
private[this] lazy val controllers_Application_PychometricExample39_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("PychometricExample"))))
private[this] lazy val controllers_Application_PychometricExample39_invoker = createInvoker(
controllers.Application.PychometricExample(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "PychometricExample", Nil,"GET", """""", Routes.prefix + """PychometricExample"""))
        

// @LINE:91
private[this] lazy val controllers_Application_tabularExample40_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tabularExample"))))
private[this] lazy val controllers_Application_tabularExample40_invoker = createInvoker(
controllers.Application.tabularExample(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "tabularExample", Nil,"GET", """""", Routes.prefix + """tabularExample"""))
        

// @LINE:93
private[this] lazy val controllers_Application_videoExample41_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("videoExample"))))
private[this] lazy val controllers_Application_videoExample41_invoker = createInvoker(
controllers.Application.videoExample(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "videoExample", Nil,"GET", """""", Routes.prefix + """videoExample"""))
        

// @LINE:96
private[this] lazy val controllers_Application_getVideo42_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("videoB"))))
private[this] lazy val controllers_Application_getVideo42_invoker = createInvoker(
controllers.Application.getVideo(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getVideo", Nil,"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """videoB"""))
        

// @LINE:98
private[this] lazy val controllers_Application_test43_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("test"))))
private[this] lazy val controllers_Application_test43_invoker = createInvoker(
controllers.Application.test(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "test", Nil,"GET", """""", Routes.prefix + """test"""))
        

// @LINE:103
private[this] lazy val controllers_Application_authentication44_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
private[this] lazy val controllers_Application_authentication44_invoker = createInvoker(
controllers.Application.authentication,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "authentication", Nil,"GET", """""", Routes.prefix + """login"""))
        

// @LINE:105
private[this] lazy val controllers_Application_authentication45_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
private[this] lazy val controllers_Application_authentication45_invoker = createInvoker(
controllers.Application.authentication,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "authentication", Nil,"POST", """""", Routes.prefix + """login"""))
        

// @LINE:107
private[this] lazy val controllers_Application_ShowDriveDialog46_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ShowDriveDialog"))))
private[this] lazy val controllers_Application_ShowDriveDialog46_invoker = createInvoker(
controllers.Application.ShowDriveDialog(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ShowDriveDialog", Nil,"GET", """""", Routes.prefix + """ShowDriveDialog"""))
        

// @LINE:109
private[this] lazy val controllers_Application_Register47_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signup"))))
private[this] lazy val controllers_Application_Register47_invoker = createInvoker(
controllers.Application.Register,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Register", Nil,"GET", """""", Routes.prefix + """signup"""))
        

// @LINE:111
private[this] lazy val controllers_Application_submitRegistration48_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signup"))))
private[this] lazy val controllers_Application_submitRegistration48_invoker = createInvoker(
controllers.Application.submitRegistration,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "submitRegistration", Nil,"POST", """""", Routes.prefix + """signup"""))
        

// @LINE:113
private[this] lazy val controllers_Application_shareMyStudy49_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("InsertStudy"))))
private[this] lazy val controllers_Application_shareMyStudy49_invoker = createInvoker(
controllers.Application.shareMyStudy,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "shareMyStudy", Nil,"POST", """""", Routes.prefix + """InsertStudy"""))
        

// @LINE:115
private[this] lazy val controllers_Application_insertSubject50_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("InsertSubject"))))
private[this] lazy val controllers_Application_insertSubject50_invoker = createInvoker(
controllers.Application.insertSubject,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "insertSubject", Nil,"POST", """""", Routes.prefix + """InsertSubject"""))
        

// @LINE:117
private[this] lazy val controllers_Application_InsertSession51_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("InsertSession"))))
private[this] lazy val controllers_Application_InsertSession51_invoker = createInvoker(
controllers.Application.InsertSession,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InsertSession", Nil,"POST", """""", Routes.prefix + """InsertSession"""))
        

// @LINE:119
private[this] lazy val controllers_Application_editSubject52_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("editSubject"))))
private[this] lazy val controllers_Application_editSubject52_invoker = createInvoker(
controllers.Application.editSubject(fakeValue[Int], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "editSubject", Seq(classOf[Int], classOf[String]),"GET", """""", Routes.prefix + """editSubject"""))
        

// @LINE:121
private[this] lazy val controllers_Application_uploadFile53_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("upload"))))
private[this] lazy val controllers_Application_uploadFile53_invoker = createInvoker(
controllers.Application.uploadFile,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "uploadFile", Nil,"POST", """""", Routes.prefix + """upload"""))
        

// @LINE:124
private[this] lazy val controllers_Application_video54_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("video"))))
private[this] lazy val controllers_Application_video54_invoker = createInvoker(
controllers.Application.video,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "video", Nil,"GET", """to be deleted""", Routes.prefix + """video"""))
        

// @LINE:126
private[this] lazy val controllers_Application_editStudy55_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("editStudy/"),DynamicPart("studyId", """[^/]+""",true))))
private[this] lazy val controllers_Application_editStudy55_invoker = createInvoker(
controllers.Application.editStudy(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "editStudy", Seq(classOf[Int]),"GET", """""", Routes.prefix + """editStudy/$studyId<[^/]+>"""))
        
def documentation = List(("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ws""","""controllers.Application.socket"""),("""GET""", prefix,"""controllers.Application.Front"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """home""","""controllers.Application.Main"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """display""","""controllers.Application.ReceiveOauthData(state:String, code:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """portrait""","""controllers.Application.getPortraitInfo(studyId:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getInfo""","""controllers.Application.getInfo(task:String, subject:String, studyId:Int, signal_seq:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getPRF""","""controllers.Application.getPRF(task:String, subject:String, studyId:Int, signal_type:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getPsycho""","""controllers.Application.getPsycho(task:String, subject:String, studyId:Int, signal_seq:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """GetSignal""","""controllers.Application.GetSignal(task:String, subject:String, studyId:Int, signal_type:Int, signal_sequence:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """file""","""controllers.Application.file(task:String, subject:String, studyId:Int, signal_type:Int, signal_sequence:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """downloadStudy""","""controllers.Application.zip(studyId:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """allStudies""","""controllers.Application.displayStudies"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showPyramid""","""controllers.Application.showPyramid(studyNo:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showStudy/$studyNo<[^/]+>""","""controllers.Application.showStudy(studyNo:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showStudySkipTop/$studyNo<[^/]+>""","""controllers.Application.showStudySkipTop(studyNo:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """displaySubject""","""controllers.Application.displaySubject(studyNo:Int, SubjectId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showRadar""","""controllers.Application.showRadar(studyNo:Int, SubjectId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getRadar""","""controllers.Application.getRadar(studyNo:Int, SubjectId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """createStudy""","""controllers.Application.ShowCreateStudyForm()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """createStudy""","""controllers.Application.InsertNewStudy()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """deleteStudy""","""controllers.Application.deleteStudy()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """processNewDataType""","""controllers.Application.ProcessNewDataType()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """processNewPsychometric""","""controllers.Application.ProcessNewPsychometric()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ProcessNewTopSummary""","""controllers.Application.ProcessNewTopSummary()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """editExistingDataType""","""controllers.Application.EditDataType()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """editPsychometric""","""controllers.Application.EditPsychometric()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.Application.logout"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """frontPage""","""controllers.Application.Front"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """setUpStudy""","""controllers.Application.setUpStudy"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """howToCreateNewStudy""","""controllers.Application.howToCreateNewStudy"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showAddDataType""","""controllers.Application.showAddDataType"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showAddPsychometric""","""controllers.Application.showAddPsychometric"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showAddTopSummary""","""controllers.Application.showAddTopSummary(studyId:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showAllDataTypes""","""controllers.Application.showAllDataTypes"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getDummy""","""controllers.Application.Getdummy()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showAllPsychometric""","""controllers.Application.showAllPsychometric"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signalDataExample""","""controllers.Application.signalDataExample()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """InfoExample""","""controllers.Application.InfoExample()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """PychometricExample""","""controllers.Application.PychometricExample()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tabularExample""","""controllers.Application.tabularExample()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """videoExample""","""controllers.Application.videoExample()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """videoB""","""controllers.Application.getVideo()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """test""","""controllers.Application.test()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.authentication"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.authentication"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ShowDriveDialog""","""controllers.Application.ShowDriveDialog()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""","""controllers.Application.Register"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""","""controllers.Application.submitRegistration"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """InsertStudy""","""controllers.Application.shareMyStudy"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """InsertSubject""","""controllers.Application.insertSubject"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """InsertSession""","""controllers.Application.InsertSession"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """editSubject""","""controllers.Application.editSubject(studyNo:Int, SubjectId:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """upload""","""controllers.Application.uploadFile"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """video""","""controllers.Application.video"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """editStudy/$studyId<[^/]+>""","""controllers.Application.editStudy(studyId:Int)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:5
case controllers_Application_socket0_route(params) => {
   call { 
        controllers_Application_socket0_invoker.call(controllers.Application.socket)
   }
}
        

// @LINE:9
case controllers_Application_Front1_route(params) => {
   call { 
        controllers_Application_Front1_invoker.call(controllers.Application.Front)
   }
}
        

// @LINE:11
case controllers_Application_Main2_route(params) => {
   call { 
        controllers_Application_Main2_invoker.call(controllers.Application.Main)
   }
}
        

// @LINE:13
case controllers_Application_ReceiveOauthData3_route(params) => {
   call(params.fromQuery[String]("state", None), params.fromQuery[String]("code", None)) { (state, code) =>
        controllers_Application_ReceiveOauthData3_invoker.call(controllers.Application.ReceiveOauthData(state, code))
   }
}
        

// @LINE:15
case controllers_Application_getPortraitInfo4_route(params) => {
   call(params.fromQuery[Int]("studyId", None)) { (studyId) =>
        controllers_Application_getPortraitInfo4_invoker.call(controllers.Application.getPortraitInfo(studyId))
   }
}
        

// @LINE:17
case controllers_Application_getInfo5_route(params) => {
   call(params.fromQuery[String]("task", None), params.fromQuery[String]("subject", None), params.fromQuery[Int]("studyId", None), params.fromQuery[Int]("signal_seq", None)) { (task, subject, studyId, signal_seq) =>
        controllers_Application_getInfo5_invoker.call(controllers.Application.getInfo(task, subject, studyId, signal_seq))
   }
}
        

// @LINE:19
case controllers_Application_getPRF6_route(params) => {
   call(params.fromQuery[String]("task", None), params.fromQuery[String]("subject", None), params.fromQuery[Int]("studyId", None), params.fromQuery[Int]("signal_type", None)) { (task, subject, studyId, signal_type) =>
        controllers_Application_getPRF6_invoker.call(controllers.Application.getPRF(task, subject, studyId, signal_type))
   }
}
        

// @LINE:21
case controllers_Application_getPsycho7_route(params) => {
   call(params.fromQuery[String]("task", None), params.fromQuery[String]("subject", None), params.fromQuery[Int]("studyId", None), params.fromQuery[Int]("signal_seq", None)) { (task, subject, studyId, signal_seq) =>
        controllers_Application_getPsycho7_invoker.call(controllers.Application.getPsycho(task, subject, studyId, signal_seq))
   }
}
        

// @LINE:23
case controllers_Application_GetSignal8_route(params) => {
   call(params.fromQuery[String]("task", None), params.fromQuery[String]("subject", None), params.fromQuery[Int]("studyId", None), params.fromQuery[Int]("signal_type", None), params.fromQuery[Int]("signal_sequence", None)) { (task, subject, studyId, signal_type, signal_sequence) =>
        controllers_Application_GetSignal8_invoker.call(controllers.Application.GetSignal(task, subject, studyId, signal_type, signal_sequence))
   }
}
        

// @LINE:25
case controllers_Application_file9_route(params) => {
   call(params.fromQuery[String]("task", None), params.fromQuery[String]("subject", None), params.fromQuery[Int]("studyId", None), params.fromQuery[Int]("signal_type", None), params.fromQuery[Int]("signal_sequence", None)) { (task, subject, studyId, signal_type, signal_sequence) =>
        controllers_Application_file9_invoker.call(controllers.Application.file(task, subject, studyId, signal_type, signal_sequence))
   }
}
        

// @LINE:27
case controllers_Application_zip10_route(params) => {
   call(params.fromQuery[Int]("studyId", None)) { (studyId) =>
        controllers_Application_zip10_invoker.call(controllers.Application.zip(studyId))
   }
}
        

// @LINE:29
case controllers_Application_displayStudies11_route(params) => {
   call { 
        controllers_Application_displayStudies11_invoker.call(controllers.Application.displayStudies)
   }
}
        

// @LINE:31
case controllers_Application_showPyramid12_route(params) => {
   call(params.fromQuery[Int]("studyNo", None)) { (studyNo) =>
        controllers_Application_showPyramid12_invoker.call(controllers.Application.showPyramid(studyNo))
   }
}
        

// @LINE:33
case controllers_Application_showStudy13_route(params) => {
   call(params.fromPath[Int]("studyNo", None)) { (studyNo) =>
        controllers_Application_showStudy13_invoker.call(controllers.Application.showStudy(studyNo))
   }
}
        

// @LINE:35
case controllers_Application_showStudySkipTop14_route(params) => {
   call(params.fromPath[Int]("studyNo", None)) { (studyNo) =>
        controllers_Application_showStudySkipTop14_invoker.call(controllers.Application.showStudySkipTop(studyNo))
   }
}
        

// @LINE:37
case controllers_Application_displaySubject15_route(params) => {
   call(params.fromQuery[Int]("studyNo", None), params.fromQuery[String]("SubjectId", None)) { (studyNo, SubjectId) =>
        controllers_Application_displaySubject15_invoker.call(controllers.Application.displaySubject(studyNo, SubjectId))
   }
}
        

// @LINE:39
case controllers_Application_showRadar16_route(params) => {
   call(params.fromQuery[Int]("studyNo", None), params.fromQuery[String]("SubjectId", None)) { (studyNo, SubjectId) =>
        controllers_Application_showRadar16_invoker.call(controllers.Application.showRadar(studyNo, SubjectId))
   }
}
        

// @LINE:41
case controllers_Application_getRadar17_route(params) => {
   call(params.fromQuery[Int]("studyNo", None), params.fromQuery[String]("SubjectId", None)) { (studyNo, SubjectId) =>
        controllers_Application_getRadar17_invoker.call(controllers.Application.getRadar(studyNo, SubjectId))
   }
}
        

// @LINE:43
case controllers_Application_ShowCreateStudyForm18_route(params) => {
   call { 
        controllers_Application_ShowCreateStudyForm18_invoker.call(controllers.Application.ShowCreateStudyForm())
   }
}
        

// @LINE:45
case controllers_Application_InsertNewStudy19_route(params) => {
   call { 
        controllers_Application_InsertNewStudy19_invoker.call(controllers.Application.InsertNewStudy())
   }
}
        

// @LINE:47
case controllers_Application_deleteStudy20_route(params) => {
   call { 
        controllers_Application_deleteStudy20_invoker.call(controllers.Application.deleteStudy())
   }
}
        

// @LINE:49
case controllers_Application_ProcessNewDataType21_route(params) => {
   call { 
        controllers_Application_ProcessNewDataType21_invoker.call(controllers.Application.ProcessNewDataType())
   }
}
        

// @LINE:51
case controllers_Application_ProcessNewPsychometric22_route(params) => {
   call { 
        controllers_Application_ProcessNewPsychometric22_invoker.call(controllers.Application.ProcessNewPsychometric())
   }
}
        

// @LINE:53
case controllers_Application_ProcessNewTopSummary23_route(params) => {
   call { 
        controllers_Application_ProcessNewTopSummary23_invoker.call(controllers.Application.ProcessNewTopSummary())
   }
}
        

// @LINE:55
case controllers_Application_EditDataType24_route(params) => {
   call { 
        controllers_Application_EditDataType24_invoker.call(controllers.Application.EditDataType())
   }
}
        

// @LINE:57
case controllers_Application_EditPsychometric25_route(params) => {
   call { 
        controllers_Application_EditPsychometric25_invoker.call(controllers.Application.EditPsychometric())
   }
}
        

// @LINE:59
case controllers_Application_logout26_route(params) => {
   call { 
        controllers_Application_logout26_invoker.call(controllers.Application.logout)
   }
}
        

// @LINE:61
case controllers_Application_Front27_route(params) => {
   call { 
        controllers_Application_Front27_invoker.call(controllers.Application.Front)
   }
}
        

// @LINE:63
case controllers_Application_setUpStudy28_route(params) => {
   call { 
        controllers_Application_setUpStudy28_invoker.call(controllers.Application.setUpStudy)
   }
}
        

// @LINE:65
case controllers_Application_howToCreateNewStudy29_route(params) => {
   call { 
        controllers_Application_howToCreateNewStudy29_invoker.call(controllers.Application.howToCreateNewStudy)
   }
}
        

// @LINE:67
case controllers_Application_showAddDataType30_route(params) => {
   call { 
        controllers_Application_showAddDataType30_invoker.call(controllers.Application.showAddDataType)
   }
}
        

// @LINE:69
case controllers_Application_showAddPsychometric31_route(params) => {
   call { 
        controllers_Application_showAddPsychometric31_invoker.call(controllers.Application.showAddPsychometric)
   }
}
        

// @LINE:71
case controllers_Application_showAddTopSummary32_route(params) => {
   call(params.fromQuery[Int]("studyId", None)) { (studyId) =>
        controllers_Application_showAddTopSummary32_invoker.call(controllers.Application.showAddTopSummary(studyId))
   }
}
        

// @LINE:73
case controllers_Application_showAllDataTypes33_route(params) => {
   call { 
        controllers_Application_showAllDataTypes33_invoker.call(controllers.Application.showAllDataTypes)
   }
}
        

// @LINE:78
case controllers_Application_Getdummy34_route(params) => {
   call { 
        controllers_Application_Getdummy34_invoker.call(controllers.Application.Getdummy())
   }
}
        

// @LINE:80
case controllers_Application_showAllPsychometric35_route(params) => {
   call { 
        controllers_Application_showAllPsychometric35_invoker.call(controllers.Application.showAllPsychometric)
   }
}
        

// @LINE:82
case controllers_Assets_at36_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at36_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:85
case controllers_Application_signalDataExample37_route(params) => {
   call { 
        controllers_Application_signalDataExample37_invoker.call(controllers.Application.signalDataExample())
   }
}
        

// @LINE:87
case controllers_Application_InfoExample38_route(params) => {
   call { 
        controllers_Application_InfoExample38_invoker.call(controllers.Application.InfoExample())
   }
}
        

// @LINE:89
case controllers_Application_PychometricExample39_route(params) => {
   call { 
        controllers_Application_PychometricExample39_invoker.call(controllers.Application.PychometricExample())
   }
}
        

// @LINE:91
case controllers_Application_tabularExample40_route(params) => {
   call { 
        controllers_Application_tabularExample40_invoker.call(controllers.Application.tabularExample())
   }
}
        

// @LINE:93
case controllers_Application_videoExample41_route(params) => {
   call { 
        controllers_Application_videoExample41_invoker.call(controllers.Application.videoExample())
   }
}
        

// @LINE:96
case controllers_Application_getVideo42_route(params) => {
   call { 
        controllers_Application_getVideo42_invoker.call(controllers.Application.getVideo())
   }
}
        

// @LINE:98
case controllers_Application_test43_route(params) => {
   call { 
        controllers_Application_test43_invoker.call(controllers.Application.test())
   }
}
        

// @LINE:103
case controllers_Application_authentication44_route(params) => {
   call { 
        controllers_Application_authentication44_invoker.call(controllers.Application.authentication)
   }
}
        

// @LINE:105
case controllers_Application_authentication45_route(params) => {
   call { 
        controllers_Application_authentication45_invoker.call(controllers.Application.authentication)
   }
}
        

// @LINE:107
case controllers_Application_ShowDriveDialog46_route(params) => {
   call { 
        controllers_Application_ShowDriveDialog46_invoker.call(controllers.Application.ShowDriveDialog())
   }
}
        

// @LINE:109
case controllers_Application_Register47_route(params) => {
   call { 
        controllers_Application_Register47_invoker.call(controllers.Application.Register)
   }
}
        

// @LINE:111
case controllers_Application_submitRegistration48_route(params) => {
   call { 
        controllers_Application_submitRegistration48_invoker.call(controllers.Application.submitRegistration)
   }
}
        

// @LINE:113
case controllers_Application_shareMyStudy49_route(params) => {
   call { 
        controllers_Application_shareMyStudy49_invoker.call(controllers.Application.shareMyStudy)
   }
}
        

// @LINE:115
case controllers_Application_insertSubject50_route(params) => {
   call { 
        controllers_Application_insertSubject50_invoker.call(controllers.Application.insertSubject)
   }
}
        

// @LINE:117
case controllers_Application_InsertSession51_route(params) => {
   call { 
        controllers_Application_InsertSession51_invoker.call(controllers.Application.InsertSession)
   }
}
        

// @LINE:119
case controllers_Application_editSubject52_route(params) => {
   call(params.fromQuery[Int]("studyNo", None), params.fromQuery[String]("SubjectId", None)) { (studyNo, SubjectId) =>
        controllers_Application_editSubject52_invoker.call(controllers.Application.editSubject(studyNo, SubjectId))
   }
}
        

// @LINE:121
case controllers_Application_uploadFile53_route(params) => {
   call { 
        controllers_Application_uploadFile53_invoker.call(controllers.Application.uploadFile)
   }
}
        

// @LINE:124
case controllers_Application_video54_route(params) => {
   call { 
        controllers_Application_video54_invoker.call(controllers.Application.video)
   }
}
        

// @LINE:126
case controllers_Application_editStudy55_route(params) => {
   call(params.fromPath[Int]("studyId", None)) { (studyId) =>
        controllers_Application_editStudy55_invoker.call(controllers.Application.editStudy(studyId))
   }
}
        
}

}
     