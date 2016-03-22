// @SOURCE:C:/first_play/conf/routes
// @HASH:83aab6c063d03debc3cdf27a0b51c076474e93f0
// @DATE:Fri Mar 11 14:58:28 CST 2016


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
private[this] lazy val controllers_Application_showSignalRealTime29_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getRealTimeChart"))))
private[this] lazy val controllers_Application_showSignalRealTime29_invoker = createInvoker(
controllers.Application.showSignalRealTime2(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showSignalRealTime2", Seq(classOf[String]),"GET", """""", Routes.prefix + """getRealTimeChart"""))
        

// @LINE:27
private[this] lazy val controllers_Application_file10_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("file"))))
private[this] lazy val controllers_Application_file10_invoker = createInvoker(
controllers.Application.file(fakeValue[String], fakeValue[String], fakeValue[Int], fakeValue[Int], fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "file", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int], classOf[Int]),"GET", """""", Routes.prefix + """file"""))
        

// @LINE:29
private[this] lazy val controllers_Application_zip11_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("downloadStudy"))))
private[this] lazy val controllers_Application_zip11_invoker = createInvoker(
controllers.Application.zip(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "zip", Seq(classOf[Int]),"GET", """""", Routes.prefix + """downloadStudy"""))
        

// @LINE:31
private[this] lazy val controllers_Application_displayStudies12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("allStudies"))))
private[this] lazy val controllers_Application_displayStudies12_invoker = createInvoker(
controllers.Application.displayStudies,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displayStudies", Nil,"GET", """""", Routes.prefix + """allStudies"""))
        

// @LINE:33
private[this] lazy val controllers_Application_showPyramid13_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showPyramid"))))
private[this] lazy val controllers_Application_showPyramid13_invoker = createInvoker(
controllers.Application.showPyramid(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showPyramid", Seq(classOf[Int]),"GET", """""", Routes.prefix + """showPyramid"""))
        

// @LINE:35
private[this] lazy val controllers_Application_showStudy14_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showStudy/"),DynamicPart("studyNo", """[^/]+""",true))))
private[this] lazy val controllers_Application_showStudy14_invoker = createInvoker(
controllers.Application.showStudy(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showStudy", Seq(classOf[Int]),"GET", """""", Routes.prefix + """showStudy/$studyNo<[^/]+>"""))
        

// @LINE:37
private[this] lazy val controllers_Application_showStudySkipTop15_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showStudySkipTop/"),DynamicPart("studyNo", """[^/]+""",true))))
private[this] lazy val controllers_Application_showStudySkipTop15_invoker = createInvoker(
controllers.Application.showStudySkipTop(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showStudySkipTop", Seq(classOf[Int]),"GET", """""", Routes.prefix + """showStudySkipTop/$studyNo<[^/]+>"""))
        

// @LINE:39
private[this] lazy val controllers_Application_displaySubject16_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("displaySubject"))))
private[this] lazy val controllers_Application_displaySubject16_invoker = createInvoker(
controllers.Application.displaySubject(fakeValue[Int], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displaySubject", Seq(classOf[Int], classOf[String]),"GET", """""", Routes.prefix + """displaySubject"""))
        

// @LINE:41
private[this] lazy val controllers_Application_showRadar17_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showRadar"))))
private[this] lazy val controllers_Application_showRadar17_invoker = createInvoker(
controllers.Application.showRadar(fakeValue[Int], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showRadar", Seq(classOf[Int], classOf[String]),"GET", """""", Routes.prefix + """showRadar"""))
        

// @LINE:43
private[this] lazy val controllers_Application_getRadar18_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getRadar"))))
private[this] lazy val controllers_Application_getRadar18_invoker = createInvoker(
controllers.Application.getRadar(fakeValue[Int], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getRadar", Seq(classOf[Int], classOf[String]),"GET", """""", Routes.prefix + """getRadar"""))
        

// @LINE:45
private[this] lazy val controllers_Application_ShowCreateStudyForm19_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("createStudy"))))
private[this] lazy val controllers_Application_ShowCreateStudyForm19_invoker = createInvoker(
controllers.Application.ShowCreateStudyForm(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ShowCreateStudyForm", Nil,"GET", """""", Routes.prefix + """createStudy"""))
        

// @LINE:47
private[this] lazy val controllers_Application_RealTime20_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("realTime"))))
private[this] lazy val controllers_Application_RealTime20_invoker = createInvoker(
controllers.Application.RealTime(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "RealTime", Nil,"GET", """""", Routes.prefix + """realTime"""))
        

// @LINE:49
private[this] lazy val controllers_Application_showSignalRealTime21_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showSignalRealTime"))))
private[this] lazy val controllers_Application_showSignalRealTime21_invoker = createInvoker(
controllers.Application.showSignalRealTime(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showSignalRealTime", Seq(classOf[String]),"GET", """""", Routes.prefix + """showSignalRealTime"""))
        

// @LINE:51
private[this] lazy val controllers_Application_InsertNewStudy22_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("createStudy"))))
private[this] lazy val controllers_Application_InsertNewStudy22_invoker = createInvoker(
controllers.Application.InsertNewStudy(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InsertNewStudy", Nil,"POST", """""", Routes.prefix + """createStudy"""))
        

// @LINE:53
private[this] lazy val controllers_Application_deleteStudy23_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("deleteStudy"))))
private[this] lazy val controllers_Application_deleteStudy23_invoker = createInvoker(
controllers.Application.deleteStudy(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "deleteStudy", Nil,"POST", """""", Routes.prefix + """deleteStudy"""))
        

// @LINE:55
private[this] lazy val controllers_Application_ProcessNewDataType24_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("processNewDataType"))))
private[this] lazy val controllers_Application_ProcessNewDataType24_invoker = createInvoker(
controllers.Application.ProcessNewDataType(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ProcessNewDataType", Nil,"POST", """""", Routes.prefix + """processNewDataType"""))
        

// @LINE:57
private[this] lazy val controllers_Application_ProcessNewPsychometric25_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("processNewPsychometric"))))
private[this] lazy val controllers_Application_ProcessNewPsychometric25_invoker = createInvoker(
controllers.Application.ProcessNewPsychometric(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ProcessNewPsychometric", Nil,"POST", """""", Routes.prefix + """processNewPsychometric"""))
        

// @LINE:59
private[this] lazy val controllers_Application_ProcessNewTopSummary26_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ProcessNewTopSummary"))))
private[this] lazy val controllers_Application_ProcessNewTopSummary26_invoker = createInvoker(
controllers.Application.ProcessNewTopSummary(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ProcessNewTopSummary", Nil,"POST", """""", Routes.prefix + """ProcessNewTopSummary"""))
        

// @LINE:61
private[this] lazy val controllers_Application_EditDataType27_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("editExistingDataType"))))
private[this] lazy val controllers_Application_EditDataType27_invoker = createInvoker(
controllers.Application.EditDataType(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "EditDataType", Nil,"POST", """""", Routes.prefix + """editExistingDataType"""))
        

// @LINE:63
private[this] lazy val controllers_Application_EditPsychometric28_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("editPsychometric"))))
private[this] lazy val controllers_Application_EditPsychometric28_invoker = createInvoker(
controllers.Application.EditPsychometric(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "EditPsychometric", Nil,"POST", """""", Routes.prefix + """editPsychometric"""))
        

// @LINE:65
private[this] lazy val controllers_Application_EditHideSubject29_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("editHideSubject"))))
private[this] lazy val controllers_Application_EditHideSubject29_invoker = createInvoker(
controllers.Application.EditHideSubject(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "EditHideSubject", Nil,"POST", """""", Routes.prefix + """editHideSubject"""))
        

// @LINE:67
private[this] lazy val controllers_Application_logout30_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
private[this] lazy val controllers_Application_logout30_invoker = createInvoker(
controllers.Application.logout,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "logout", Nil,"POST", """""", Routes.prefix + """logout"""))
        

// @LINE:69
private[this] lazy val controllers_Application_Front31_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("frontPage"))))
private[this] lazy val controllers_Application_Front31_invoker = createInvoker(
controllers.Application.Front,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Front", Nil,"GET", """""", Routes.prefix + """frontPage"""))
        

// @LINE:71
private[this] lazy val controllers_Application_setUpStudy32_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("setUpStudy"))))
private[this] lazy val controllers_Application_setUpStudy32_invoker = createInvoker(
controllers.Application.setUpStudy,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "setUpStudy", Nil,"GET", """""", Routes.prefix + """setUpStudy"""))
        

// @LINE:73
private[this] lazy val controllers_Application_howToCreateNewStudy33_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("howToCreateNewStudy"))))
private[this] lazy val controllers_Application_howToCreateNewStudy33_invoker = createInvoker(
controllers.Application.howToCreateNewStudy,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "howToCreateNewStudy", Nil,"GET", """""", Routes.prefix + """howToCreateNewStudy"""))
        

// @LINE:75
private[this] lazy val controllers_Application_showAddDataType34_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showAddDataType"))))
private[this] lazy val controllers_Application_showAddDataType34_invoker = createInvoker(
controllers.Application.showAddDataType,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAddDataType", Nil,"GET", """""", Routes.prefix + """showAddDataType"""))
        

// @LINE:77
private[this] lazy val controllers_Application_showAddPsychometric35_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showAddPsychometric"))))
private[this] lazy val controllers_Application_showAddPsychometric35_invoker = createInvoker(
controllers.Application.showAddPsychometric,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAddPsychometric", Nil,"GET", """""", Routes.prefix + """showAddPsychometric"""))
        

// @LINE:79
private[this] lazy val controllers_Application_showAddTopSummary36_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showAddTopSummary"))))
private[this] lazy val controllers_Application_showAddTopSummary36_invoker = createInvoker(
controllers.Application.showAddTopSummary(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAddTopSummary", Seq(classOf[Int]),"GET", """""", Routes.prefix + """showAddTopSummary"""))
        

// @LINE:81
private[this] lazy val controllers_Application_showAllDataTypes37_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showAllDataTypes"))))
private[this] lazy val controllers_Application_showAllDataTypes37_invoker = createInvoker(
controllers.Application.showAllDataTypes,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAllDataTypes", Nil,"GET", """""", Routes.prefix + """showAllDataTypes"""))
        

// @LINE:83
private[this] lazy val controllers_Application_showAllSubjectToHide38_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showAllSubjectsHide"))))
private[this] lazy val controllers_Application_showAllSubjectToHide38_invoker = createInvoker(
controllers.Application.showAllSubjectToHide(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAllSubjectToHide", Seq(classOf[Int]),"GET", """""", Routes.prefix + """showAllSubjectsHide"""))
        

// @LINE:89
private[this] lazy val controllers_Application_Getdummy39_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getDummy"))))
private[this] lazy val controllers_Application_Getdummy39_invoker = createInvoker(
controllers.Application.Getdummy(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Getdummy", Nil,"GET", """""", Routes.prefix + """getDummy"""))
        

// @LINE:91
private[this] lazy val controllers_Application_showAllPsychometric40_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("showAllPsychometric"))))
private[this] lazy val controllers_Application_showAllPsychometric40_invoker = createInvoker(
controllers.Application.showAllPsychometric,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAllPsychometric", Nil,"GET", """""", Routes.prefix + """showAllPsychometric"""))
        

// @LINE:93
private[this] lazy val controllers_Assets_at41_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at41_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:96
private[this] lazy val controllers_Application_signalDataExample42_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signalDataExample"))))
private[this] lazy val controllers_Application_signalDataExample42_invoker = createInvoker(
controllers.Application.signalDataExample(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "signalDataExample", Nil,"GET", """""", Routes.prefix + """signalDataExample"""))
        

// @LINE:98
private[this] lazy val controllers_Application_InfoExample43_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("InfoExample"))))
private[this] lazy val controllers_Application_InfoExample43_invoker = createInvoker(
controllers.Application.InfoExample(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InfoExample", Nil,"GET", """""", Routes.prefix + """InfoExample"""))
        

// @LINE:100
private[this] lazy val controllers_Application_PychometricExample44_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("PychometricExample"))))
private[this] lazy val controllers_Application_PychometricExample44_invoker = createInvoker(
controllers.Application.PychometricExample(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "PychometricExample", Nil,"GET", """""", Routes.prefix + """PychometricExample"""))
        

// @LINE:102
private[this] lazy val controllers_Application_tabularExample45_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tabularExample"))))
private[this] lazy val controllers_Application_tabularExample45_invoker = createInvoker(
controllers.Application.tabularExample(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "tabularExample", Nil,"GET", """""", Routes.prefix + """tabularExample"""))
        

// @LINE:104
private[this] lazy val controllers_Application_videoExample46_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("videoExample"))))
private[this] lazy val controllers_Application_videoExample46_invoker = createInvoker(
controllers.Application.videoExample(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "videoExample", Nil,"GET", """""", Routes.prefix + """videoExample"""))
        

// @LINE:107
private[this] lazy val controllers_Application_getVideo47_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("videoB"))))
private[this] lazy val controllers_Application_getVideo47_invoker = createInvoker(
controllers.Application.getVideo(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getVideo", Nil,"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """videoB"""))
        

// @LINE:109
private[this] lazy val controllers_Application_test48_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("test"))))
private[this] lazy val controllers_Application_test48_invoker = createInvoker(
controllers.Application.test(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "test", Nil,"GET", """""", Routes.prefix + """test"""))
        

// @LINE:114
private[this] lazy val controllers_Application_authentication49_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
private[this] lazy val controllers_Application_authentication49_invoker = createInvoker(
controllers.Application.authentication,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "authentication", Nil,"GET", """""", Routes.prefix + """login"""))
        

// @LINE:116
private[this] lazy val controllers_Application_authentication50_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
private[this] lazy val controllers_Application_authentication50_invoker = createInvoker(
controllers.Application.authentication,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "authentication", Nil,"POST", """""", Routes.prefix + """login"""))
        

// @LINE:118
private[this] lazy val controllers_Application_ShowDriveDialog51_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ShowDriveDialog"))))
private[this] lazy val controllers_Application_ShowDriveDialog51_invoker = createInvoker(
controllers.Application.ShowDriveDialog(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ShowDriveDialog", Nil,"GET", """""", Routes.prefix + """ShowDriveDialog"""))
        

// @LINE:120
private[this] lazy val controllers_Application_Register52_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signup"))))
private[this] lazy val controllers_Application_Register52_invoker = createInvoker(
controllers.Application.Register,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Register", Nil,"GET", """""", Routes.prefix + """signup"""))
        

// @LINE:122
private[this] lazy val controllers_Application_submitRegistration53_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signup"))))
private[this] lazy val controllers_Application_submitRegistration53_invoker = createInvoker(
controllers.Application.submitRegistration,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "submitRegistration", Nil,"POST", """""", Routes.prefix + """signup"""))
        

// @LINE:124
private[this] lazy val controllers_Application_shareMyStudy54_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("InsertStudy"))))
private[this] lazy val controllers_Application_shareMyStudy54_invoker = createInvoker(
controllers.Application.shareMyStudy,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "shareMyStudy", Nil,"POST", """""", Routes.prefix + """InsertStudy"""))
        

// @LINE:126
private[this] lazy val controllers_Application_insertSubject55_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("InsertSubject"))))
private[this] lazy val controllers_Application_insertSubject55_invoker = createInvoker(
controllers.Application.insertSubject,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "insertSubject", Nil,"POST", """""", Routes.prefix + """InsertSubject"""))
        

// @LINE:128
private[this] lazy val controllers_Application_InsertSession56_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("InsertSession"))))
private[this] lazy val controllers_Application_InsertSession56_invoker = createInvoker(
controllers.Application.InsertSession,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InsertSession", Nil,"POST", """""", Routes.prefix + """InsertSession"""))
        

// @LINE:130
private[this] lazy val controllers_Application_editSubject57_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("editSubject"))))
private[this] lazy val controllers_Application_editSubject57_invoker = createInvoker(
controllers.Application.editSubject(fakeValue[Int], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "editSubject", Seq(classOf[Int], classOf[String]),"GET", """""", Routes.prefix + """editSubject"""))
        

// @LINE:132
private[this] lazy val controllers_Application_uploadFile58_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("upload"))))
private[this] lazy val controllers_Application_uploadFile58_invoker = createInvoker(
controllers.Application.uploadFile,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "uploadFile", Nil,"POST", """""", Routes.prefix + """upload"""))
        

// @LINE:135
private[this] lazy val controllers_Application_video59_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("video"))))
private[this] lazy val controllers_Application_video59_invoker = createInvoker(
controllers.Application.video,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "video", Nil,"GET", """to be deleted""", Routes.prefix + """video"""))
        

// @LINE:137
private[this] lazy val controllers_Application_editStudy60_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("editStudy/"),DynamicPart("studyId", """[^/]+""",true))))
private[this] lazy val controllers_Application_editStudy60_invoker = createInvoker(
controllers.Application.editStudy(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "editStudy", Seq(classOf[Int]),"GET", """""", Routes.prefix + """editStudy/$studyId<[^/]+>"""))
        
def documentation = List(("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ws""","""controllers.Application.socket"""),("""GET""", prefix,"""controllers.Application.Front"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """home""","""controllers.Application.Main"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """display""","""controllers.Application.ReceiveOauthData(state:String, code:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """portrait""","""controllers.Application.getPortraitInfo(studyId:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getInfo""","""controllers.Application.getInfo(task:String, subject:String, studyId:Int, signal_seq:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getPRF""","""controllers.Application.getPRF(task:String, subject:String, studyId:Int, signal_type:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getPsycho""","""controllers.Application.getPsycho(task:String, subject:String, studyId:Int, signal_seq:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """GetSignal""","""controllers.Application.GetSignal(task:String, subject:String, studyId:Int, signal_type:Int, signal_sequence:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getRealTimeChart""","""controllers.Application.showSignalRealTime2(fileId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """file""","""controllers.Application.file(task:String, subject:String, studyId:Int, signal_type:Int, signal_sequence:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """downloadStudy""","""controllers.Application.zip(studyId:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """allStudies""","""controllers.Application.displayStudies"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showPyramid""","""controllers.Application.showPyramid(studyNo:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showStudy/$studyNo<[^/]+>""","""controllers.Application.showStudy(studyNo:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showStudySkipTop/$studyNo<[^/]+>""","""controllers.Application.showStudySkipTop(studyNo:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """displaySubject""","""controllers.Application.displaySubject(studyNo:Int, SubjectId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showRadar""","""controllers.Application.showRadar(studyNo:Int, SubjectId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getRadar""","""controllers.Application.getRadar(studyNo:Int, SubjectId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """createStudy""","""controllers.Application.ShowCreateStudyForm()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """realTime""","""controllers.Application.RealTime()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showSignalRealTime""","""controllers.Application.showSignalRealTime(fileId:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """createStudy""","""controllers.Application.InsertNewStudy()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """deleteStudy""","""controllers.Application.deleteStudy()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """processNewDataType""","""controllers.Application.ProcessNewDataType()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """processNewPsychometric""","""controllers.Application.ProcessNewPsychometric()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ProcessNewTopSummary""","""controllers.Application.ProcessNewTopSummary()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """editExistingDataType""","""controllers.Application.EditDataType()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """editPsychometric""","""controllers.Application.EditPsychometric()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """editHideSubject""","""controllers.Application.EditHideSubject()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.Application.logout"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """frontPage""","""controllers.Application.Front"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """setUpStudy""","""controllers.Application.setUpStudy"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """howToCreateNewStudy""","""controllers.Application.howToCreateNewStudy"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showAddDataType""","""controllers.Application.showAddDataType"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showAddPsychometric""","""controllers.Application.showAddPsychometric"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showAddTopSummary""","""controllers.Application.showAddTopSummary(studyId:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showAllDataTypes""","""controllers.Application.showAllDataTypes"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showAllSubjectsHide""","""controllers.Application.showAllSubjectToHide(studyId:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getDummy""","""controllers.Application.Getdummy()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """showAllPsychometric""","""controllers.Application.showAllPsychometric"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signalDataExample""","""controllers.Application.signalDataExample()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """InfoExample""","""controllers.Application.InfoExample()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """PychometricExample""","""controllers.Application.PychometricExample()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tabularExample""","""controllers.Application.tabularExample()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """videoExample""","""controllers.Application.videoExample()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """videoB""","""controllers.Application.getVideo()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """test""","""controllers.Application.test()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.authentication"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.authentication"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ShowDriveDialog""","""controllers.Application.ShowDriveDialog()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""","""controllers.Application.Register"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signup""","""controllers.Application.submitRegistration"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """InsertStudy""","""controllers.Application.shareMyStudy"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """InsertSubject""","""controllers.Application.insertSubject"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """InsertSession""","""controllers.Application.InsertSession"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """editSubject""","""controllers.Application.editSubject(studyNo:Int, SubjectId:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """upload""","""controllers.Application.uploadFile"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """video""","""controllers.Application.video"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """editStudy/$studyId<[^/]+>""","""controllers.Application.editStudy(studyId:Int)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
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
case controllers_Application_showSignalRealTime29_route(params) => {
   call(params.fromQuery[String]("fileId", None)) { (fileId) =>
        controllers_Application_showSignalRealTime29_invoker.call(controllers.Application.showSignalRealTime2(fileId))
   }
}
        

// @LINE:27
case controllers_Application_file10_route(params) => {
   call(params.fromQuery[String]("task", None), params.fromQuery[String]("subject", None), params.fromQuery[Int]("studyId", None), params.fromQuery[Int]("signal_type", None), params.fromQuery[Int]("signal_sequence", None)) { (task, subject, studyId, signal_type, signal_sequence) =>
        controllers_Application_file10_invoker.call(controllers.Application.file(task, subject, studyId, signal_type, signal_sequence))
   }
}
        

// @LINE:29
case controllers_Application_zip11_route(params) => {
   call(params.fromQuery[Int]("studyId", None)) { (studyId) =>
        controllers_Application_zip11_invoker.call(controllers.Application.zip(studyId))
   }
}
        

// @LINE:31
case controllers_Application_displayStudies12_route(params) => {
   call { 
        controllers_Application_displayStudies12_invoker.call(controllers.Application.displayStudies)
   }
}
        

// @LINE:33
case controllers_Application_showPyramid13_route(params) => {
   call(params.fromQuery[Int]("studyNo", None)) { (studyNo) =>
        controllers_Application_showPyramid13_invoker.call(controllers.Application.showPyramid(studyNo))
   }
}
        

// @LINE:35
case controllers_Application_showStudy14_route(params) => {
   call(params.fromPath[Int]("studyNo", None)) { (studyNo) =>
        controllers_Application_showStudy14_invoker.call(controllers.Application.showStudy(studyNo))
   }
}
        

// @LINE:37
case controllers_Application_showStudySkipTop15_route(params) => {
   call(params.fromPath[Int]("studyNo", None)) { (studyNo) =>
        controllers_Application_showStudySkipTop15_invoker.call(controllers.Application.showStudySkipTop(studyNo))
   }
}
        

// @LINE:39
case controllers_Application_displaySubject16_route(params) => {
   call(params.fromQuery[Int]("studyNo", None), params.fromQuery[String]("SubjectId", None)) { (studyNo, SubjectId) =>
        controllers_Application_displaySubject16_invoker.call(controllers.Application.displaySubject(studyNo, SubjectId))
   }
}
        

// @LINE:41
case controllers_Application_showRadar17_route(params) => {
   call(params.fromQuery[Int]("studyNo", None), params.fromQuery[String]("SubjectId", None)) { (studyNo, SubjectId) =>
        controllers_Application_showRadar17_invoker.call(controllers.Application.showRadar(studyNo, SubjectId))
   }
}
        

// @LINE:43
case controllers_Application_getRadar18_route(params) => {
   call(params.fromQuery[Int]("studyNo", None), params.fromQuery[String]("SubjectId", None)) { (studyNo, SubjectId) =>
        controllers_Application_getRadar18_invoker.call(controllers.Application.getRadar(studyNo, SubjectId))
   }
}
        

// @LINE:45
case controllers_Application_ShowCreateStudyForm19_route(params) => {
   call { 
        controllers_Application_ShowCreateStudyForm19_invoker.call(controllers.Application.ShowCreateStudyForm())
   }
}
        

// @LINE:47
case controllers_Application_RealTime20_route(params) => {
   call { 
        controllers_Application_RealTime20_invoker.call(controllers.Application.RealTime())
   }
}
        

// @LINE:49
case controllers_Application_showSignalRealTime21_route(params) => {
   call(params.fromQuery[String]("fileId", None)) { (fileId) =>
        controllers_Application_showSignalRealTime21_invoker.call(controllers.Application.showSignalRealTime(fileId))
   }
}
        

// @LINE:51
case controllers_Application_InsertNewStudy22_route(params) => {
   call { 
        controllers_Application_InsertNewStudy22_invoker.call(controllers.Application.InsertNewStudy())
   }
}
        

// @LINE:53
case controllers_Application_deleteStudy23_route(params) => {
   call { 
        controllers_Application_deleteStudy23_invoker.call(controllers.Application.deleteStudy())
   }
}
        

// @LINE:55
case controllers_Application_ProcessNewDataType24_route(params) => {
   call { 
        controllers_Application_ProcessNewDataType24_invoker.call(controllers.Application.ProcessNewDataType())
   }
}
        

// @LINE:57
case controllers_Application_ProcessNewPsychometric25_route(params) => {
   call { 
        controllers_Application_ProcessNewPsychometric25_invoker.call(controllers.Application.ProcessNewPsychometric())
   }
}
        

// @LINE:59
case controllers_Application_ProcessNewTopSummary26_route(params) => {
   call { 
        controllers_Application_ProcessNewTopSummary26_invoker.call(controllers.Application.ProcessNewTopSummary())
   }
}
        

// @LINE:61
case controllers_Application_EditDataType27_route(params) => {
   call { 
        controllers_Application_EditDataType27_invoker.call(controllers.Application.EditDataType())
   }
}
        

// @LINE:63
case controllers_Application_EditPsychometric28_route(params) => {
   call { 
        controllers_Application_EditPsychometric28_invoker.call(controllers.Application.EditPsychometric())
   }
}
        

// @LINE:65
case controllers_Application_EditHideSubject29_route(params) => {
   call { 
        controllers_Application_EditHideSubject29_invoker.call(controllers.Application.EditHideSubject())
   }
}
        

// @LINE:67
case controllers_Application_logout30_route(params) => {
   call { 
        controllers_Application_logout30_invoker.call(controllers.Application.logout)
   }
}
        

// @LINE:69
case controllers_Application_Front31_route(params) => {
   call { 
        controllers_Application_Front31_invoker.call(controllers.Application.Front)
   }
}
        

// @LINE:71
case controllers_Application_setUpStudy32_route(params) => {
   call { 
        controllers_Application_setUpStudy32_invoker.call(controllers.Application.setUpStudy)
   }
}
        

// @LINE:73
case controllers_Application_howToCreateNewStudy33_route(params) => {
   call { 
        controllers_Application_howToCreateNewStudy33_invoker.call(controllers.Application.howToCreateNewStudy)
   }
}
        

// @LINE:75
case controllers_Application_showAddDataType34_route(params) => {
   call { 
        controllers_Application_showAddDataType34_invoker.call(controllers.Application.showAddDataType)
   }
}
        

// @LINE:77
case controllers_Application_showAddPsychometric35_route(params) => {
   call { 
        controllers_Application_showAddPsychometric35_invoker.call(controllers.Application.showAddPsychometric)
   }
}
        

// @LINE:79
case controllers_Application_showAddTopSummary36_route(params) => {
   call(params.fromQuery[Int]("studyId", None)) { (studyId) =>
        controllers_Application_showAddTopSummary36_invoker.call(controllers.Application.showAddTopSummary(studyId))
   }
}
        

// @LINE:81
case controllers_Application_showAllDataTypes37_route(params) => {
   call { 
        controllers_Application_showAllDataTypes37_invoker.call(controllers.Application.showAllDataTypes)
   }
}
        

// @LINE:83
case controllers_Application_showAllSubjectToHide38_route(params) => {
   call(params.fromQuery[Int]("studyId", None)) { (studyId) =>
        controllers_Application_showAllSubjectToHide38_invoker.call(controllers.Application.showAllSubjectToHide(studyId))
   }
}
        

// @LINE:89
case controllers_Application_Getdummy39_route(params) => {
   call { 
        controllers_Application_Getdummy39_invoker.call(controllers.Application.Getdummy())
   }
}
        

// @LINE:91
case controllers_Application_showAllPsychometric40_route(params) => {
   call { 
        controllers_Application_showAllPsychometric40_invoker.call(controllers.Application.showAllPsychometric)
   }
}
        

// @LINE:93
case controllers_Assets_at41_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at41_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:96
case controllers_Application_signalDataExample42_route(params) => {
   call { 
        controllers_Application_signalDataExample42_invoker.call(controllers.Application.signalDataExample())
   }
}
        

// @LINE:98
case controllers_Application_InfoExample43_route(params) => {
   call { 
        controllers_Application_InfoExample43_invoker.call(controllers.Application.InfoExample())
   }
}
        

// @LINE:100
case controllers_Application_PychometricExample44_route(params) => {
   call { 
        controllers_Application_PychometricExample44_invoker.call(controllers.Application.PychometricExample())
   }
}
        

// @LINE:102
case controllers_Application_tabularExample45_route(params) => {
   call { 
        controllers_Application_tabularExample45_invoker.call(controllers.Application.tabularExample())
   }
}
        

// @LINE:104
case controllers_Application_videoExample46_route(params) => {
   call { 
        controllers_Application_videoExample46_invoker.call(controllers.Application.videoExample())
   }
}
        

// @LINE:107
case controllers_Application_getVideo47_route(params) => {
   call { 
        controllers_Application_getVideo47_invoker.call(controllers.Application.getVideo())
   }
}
        

// @LINE:109
case controllers_Application_test48_route(params) => {
   call { 
        controllers_Application_test48_invoker.call(controllers.Application.test())
   }
}
        

// @LINE:114
case controllers_Application_authentication49_route(params) => {
   call { 
        controllers_Application_authentication49_invoker.call(controllers.Application.authentication)
   }
}
        

// @LINE:116
case controllers_Application_authentication50_route(params) => {
   call { 
        controllers_Application_authentication50_invoker.call(controllers.Application.authentication)
   }
}
        

// @LINE:118
case controllers_Application_ShowDriveDialog51_route(params) => {
   call { 
        controllers_Application_ShowDriveDialog51_invoker.call(controllers.Application.ShowDriveDialog())
   }
}
        

// @LINE:120
case controllers_Application_Register52_route(params) => {
   call { 
        controllers_Application_Register52_invoker.call(controllers.Application.Register)
   }
}
        

// @LINE:122
case controllers_Application_submitRegistration53_route(params) => {
   call { 
        controllers_Application_submitRegistration53_invoker.call(controllers.Application.submitRegistration)
   }
}
        

// @LINE:124
case controllers_Application_shareMyStudy54_route(params) => {
   call { 
        controllers_Application_shareMyStudy54_invoker.call(controllers.Application.shareMyStudy)
   }
}
        

// @LINE:126
case controllers_Application_insertSubject55_route(params) => {
   call { 
        controllers_Application_insertSubject55_invoker.call(controllers.Application.insertSubject)
   }
}
        

// @LINE:128
case controllers_Application_InsertSession56_route(params) => {
   call { 
        controllers_Application_InsertSession56_invoker.call(controllers.Application.InsertSession)
   }
}
        

// @LINE:130
case controllers_Application_editSubject57_route(params) => {
   call(params.fromQuery[Int]("studyNo", None), params.fromQuery[String]("SubjectId", None)) { (studyNo, SubjectId) =>
        controllers_Application_editSubject57_invoker.call(controllers.Application.editSubject(studyNo, SubjectId))
   }
}
        

// @LINE:132
case controllers_Application_uploadFile58_route(params) => {
   call { 
        controllers_Application_uploadFile58_invoker.call(controllers.Application.uploadFile)
   }
}
        

// @LINE:135
case controllers_Application_video59_route(params) => {
   call { 
        controllers_Application_video59_invoker.call(controllers.Application.video)
   }
}
        

// @LINE:137
case controllers_Application_editStudy60_route(params) => {
   call(params.fromPath[Int]("studyId", None)) { (studyId) =>
        controllers_Application_editStudy60_invoker.call(controllers.Application.editStudy(studyId))
   }
}
        
}

}
     