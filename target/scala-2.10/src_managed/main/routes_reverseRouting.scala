// @SOURCE:C:/first_play/conf/routes
// @HASH:5e8de2d46927a67fc2ee39292a93724c5c51e51a
// @DATE:Tue Oct 04 19:27:49 CDT 2016

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString


// @LINE:150
// @LINE:146
// @LINE:144
// @LINE:142
// @LINE:140
// @LINE:135
// @LINE:133
// @LINE:131
// @LINE:129
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:120
// @LINE:118
// @LINE:115
// @LINE:113
// @LINE:111
// @LINE:105
// @LINE:103
// @LINE:101
// @LINE:99
// @LINE:97
// @LINE:95
// @LINE:93
// @LINE:91
// @LINE:89
// @LINE:87
// @LINE:85
// @LINE:83
// @LINE:81
// @LINE:79
// @LINE:77
// @LINE:75
// @LINE:73
// @LINE:71
// @LINE:69
// @LINE:67
// @LINE:65
// @LINE:63
// @LINE:61
// @LINE:59
// @LINE:57
// @LINE:55
// @LINE:53
// @LINE:51
// @LINE:49
// @LINE:47
// @LINE:45
// @LINE:43
// @LINE:41
// @LINE:39
// @LINE:37
// @LINE:35
// @LINE:33
// @LINE:31
// @LINE:29
// @LINE:27
// @LINE:25
// @LINE:23
// @LINE:21
// @LINE:19
// @LINE:17
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:5
package controllers {

// @LINE:115
class ReverseAssets {


// @LINE:115
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:150
// @LINE:146
// @LINE:144
// @LINE:142
// @LINE:140
// @LINE:135
// @LINE:133
// @LINE:131
// @LINE:129
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:120
// @LINE:118
// @LINE:113
// @LINE:111
// @LINE:105
// @LINE:103
// @LINE:101
// @LINE:99
// @LINE:97
// @LINE:95
// @LINE:93
// @LINE:91
// @LINE:89
// @LINE:87
// @LINE:85
// @LINE:83
// @LINE:81
// @LINE:79
// @LINE:77
// @LINE:75
// @LINE:73
// @LINE:71
// @LINE:69
// @LINE:67
// @LINE:65
// @LINE:63
// @LINE:61
// @LINE:59
// @LINE:57
// @LINE:55
// @LINE:53
// @LINE:51
// @LINE:49
// @LINE:47
// @LINE:45
// @LINE:43
// @LINE:41
// @LINE:39
// @LINE:37
// @LINE:35
// @LINE:33
// @LINE:31
// @LINE:29
// @LINE:27
// @LINE:25
// @LINE:23
// @LINE:21
// @LINE:19
// @LINE:17
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:5
class ReverseApplication {


// @LINE:51
def ShowInitiateStudy(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "InitiateStudy")
}
                        

// @LINE:91
def showAddTopSummary(studyId:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showAddTopSummary" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)))))
}
                        

// @LINE:23
def GetSignal(task:String, subject:String, studyId:Int, signal_type:Int, signal_sequence:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "GetSignal" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("task", task)), Some(implicitly[QueryStringBindable[String]].unbind("subject", subject)), Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)), Some(implicitly[QueryStringBindable[Int]].unbind("signal_type", signal_type)), Some(implicitly[QueryStringBindable[Int]].unbind("signal_sequence", signal_sequence)))))
}
                        

// @LINE:101
def drp(studyNo:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "drp" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)))))
}
                        

// @LINE:126
def videoExample(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "videoExample")
}
                        

// @LINE:17
def getInfo(task:String, subject:String, studyId:Int, signal_seq:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "getInfo" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("task", task)), Some(implicitly[QueryStringBindable[String]].unbind("subject", subject)), Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)), Some(implicitly[QueryStringBindable[Int]].unbind("signal_seq", signal_seq)))))
}
                        

// @LINE:41
def displaySubjectInProgress(studyNo:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "displaySubjectsInProgress" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)))))
}
                        

// @LINE:75
def EditHideSubject(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "editHideSubject")
}
                        

// @LINE:142
// @LINE:140
def authentication(): Call = {
   () match {
// @LINE:140
case ()  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "login")
                                         
   }
}
                                                

// @LINE:89
def showAddPsychometric(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showAddPsychometric")
}
                        

// @LINE:85
def howToCreateNewStudy(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "howToCreateNewStudy")
}
                        

// @LINE:39
def displaySubject(studyNo:Int, SubjectId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "displaySubject" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)), Some(implicitly[QueryStringBindable[String]].unbind("SubjectId", SubjectId)))))
}
                        

// @LINE:135
def test(stydtNo:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "test" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("stydtNo", stydtNo)))))
}
                        

// @LINE:49
def InitiateNewStudy(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "InitiateStudy")
}
                        

// @LINE:77
def AddMoreSubjects(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "addMoreSubjects")
}
                        

// @LINE:95
def showAllDataTypes(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showAllDataTypes")
}
                        

// @LINE:35
def showStudy(studyNo:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showStudy/" + implicitly[PathBindable[Int]].unbind("studyNo", studyNo))
}
                        

// @LINE:124
def tabularExample(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "tabularExample")
}
                        

// @LINE:97
def showAllSubjectToHide(studyId:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showAllSubjectsHide" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)))))
}
                        

// @LINE:71
def EditDataType(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "editExistingDataType")
}
                        

// @LINE:21
def getPsycho(task:String, subject:String, studyId:Int, signal_seq:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "getPsycho" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("task", task)), Some(implicitly[QueryStringBindable[String]].unbind("subject", subject)), Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)), Some(implicitly[QueryStringBindable[Int]].unbind("signal_seq", signal_seq)))))
}
                        

// @LINE:103
def startNewSubject(loc:String, subjectLocation:String, subjectid:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "startNewSubject" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("loc", loc)), Some(implicitly[QueryStringBindable[String]].unbind("subjectLocation", subjectLocation)), Some(implicitly[QueryStringBindable[String]].unbind("subjectid", subjectid)))))
}
                        

// @LINE:113
def showAllPsychometric(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showAllPsychometric")
}
                        

// @LINE:81
// @LINE:9
def Front(): Call = {
   () match {
// @LINE:9
case ()  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix)
                                         
   }
}
                                                

// @LINE:45
def getRadar(studyNo:Int, SubjectId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "getRadar" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)), Some(implicitly[QueryStringBindable[String]].unbind("SubjectId", SubjectId)))))
}
                        

// @LINE:129
def getVideo(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "videoB")
}
                        

// @LINE:55
def RealTime(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "realTime")
}
                        

// @LINE:122
def PychometricExample(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "PychometricExample")
}
                        

// @LINE:33
def showPyramid(studyNo:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showPyramid" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)))))
}
                        

// @LINE:25
def showSignalRealTime2(fileId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "getRealTimeChart" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("fileId", fileId)))))
}
                        

// @LINE:43
def showRadar(studyNo:Int, SubjectId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showRadar" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)), Some(implicitly[QueryStringBindable[String]].unbind("SubjectId", SubjectId)))))
}
                        

// @LINE:120
def InfoExample(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "InfoExample")
}
                        

// @LINE:57
def showSignalRealTime(fileId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showSignalRealTime" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("fileId", fileId)))))
}
                        

// @LINE:111
def Getdummy(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "getDummy")
}
                        

// @LINE:53
def addNewSubject(studyNo:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "addNewSubject" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)))))
}
                        

// @LINE:63
def ProcessNewDataType(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "processNewDataType")
}
                        

// @LINE:105
def updateVidoefromServer(studyNo:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "updateVideos" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)))))
}
                        

// @LINE:67
def ProcessNewTopSummary(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "ProcessNewTopSummary")
}
                        

// @LINE:73
def EditPsychometric(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "editPsychometric")
}
                        

// @LINE:69
def ProcessNewDescriptor(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "processNewDescriptor")
}
                        

// @LINE:29
def zip(studyId:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "downloadStudy" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)))))
}
                        

// @LINE:59
def InsertNewStudy(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "createStudy")
}
                        

// @LINE:19
def getPRF(task:String, subject:String, studyId:Int, signal_type:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "getPRF" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("task", task)), Some(implicitly[QueryStringBindable[String]].unbind("subject", subject)), Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)), Some(implicitly[QueryStringBindable[Int]].unbind("signal_type", signal_type)))))
}
                        

// @LINE:150
def video(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "video")
}
                        

// @LINE:99
def CreateStudyTest(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "quickCreating")
}
                        

// @LINE:93
def showAddDescriptor(studyId:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showAddDescriptor" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)))))
}
                        

// @LINE:13
def ReceiveOauthData(state:String, code:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "display" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("state", state)), Some(implicitly[QueryStringBindable[String]].unbind("code", code)))))
}
                        

// @LINE:118
def signalDataExample(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "signalDataExample")
}
                        

// @LINE:144
def ShowDriveDialog(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "ShowDriveDialog")
}
                        

// @LINE:79
def logout(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "logout")
}
                        

// @LINE:11
def Main(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "home")
}
                        

// @LINE:5
def socket(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "ws")
}
                        

// @LINE:15
def getPortraitInfo(studyId:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "portrait" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)))))
}
                        

// @LINE:146
def shareMyStudy(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "InsertStudy")
}
                        

// @LINE:133
def getVideo3(studyNo:Int, subjectId:String, sessionName:String, fileName:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "videoC" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)), Some(implicitly[QueryStringBindable[String]].unbind("subjectId", subjectId)), Some(implicitly[QueryStringBindable[String]].unbind("sessionName", sessionName)), Some(implicitly[QueryStringBindable[String]].unbind("fileName", fileName)))))
}
                        

// @LINE:37
def showStudySkipTop(studyNo:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showStudySkipTop/" + implicitly[PathBindable[Int]].unbind("studyNo", studyNo))
}
                        

// @LINE:87
def showAddDataType(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showAddDataType")
}
                        

// @LINE:131
def getVideo2(studyNo:Int, subjectId:String, sessionName:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "videoD" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)), Some(implicitly[QueryStringBindable[String]].unbind("subjectId", subjectId)), Some(implicitly[QueryStringBindable[String]].unbind("sessionName", sessionName)))))
}
                        

// @LINE:83
def setUpStudy(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "setUpStudy")
}
                        

// @LINE:47
def ShowCreateStudyForm(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "createStudy")
}
                        

// @LINE:31
def displayStudies(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "allStudies")
}
                        

// @LINE:27
def file(task:String, subject:String, studyId:Int, signal_type:Int, signal_sequence:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "file" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("task", task)), Some(implicitly[QueryStringBindable[String]].unbind("subject", subject)), Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)), Some(implicitly[QueryStringBindable[Int]].unbind("signal_type", signal_type)), Some(implicitly[QueryStringBindable[Int]].unbind("signal_sequence", signal_sequence)))))
}
                        

// @LINE:61
def deleteStudy(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "deleteStudy")
}
                        

// @LINE:65
def ProcessNewPsychometric(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "processNewPsychometric")
}
                        

}
                          
}
                  


// @LINE:150
// @LINE:146
// @LINE:144
// @LINE:142
// @LINE:140
// @LINE:135
// @LINE:133
// @LINE:131
// @LINE:129
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:120
// @LINE:118
// @LINE:115
// @LINE:113
// @LINE:111
// @LINE:105
// @LINE:103
// @LINE:101
// @LINE:99
// @LINE:97
// @LINE:95
// @LINE:93
// @LINE:91
// @LINE:89
// @LINE:87
// @LINE:85
// @LINE:83
// @LINE:81
// @LINE:79
// @LINE:77
// @LINE:75
// @LINE:73
// @LINE:71
// @LINE:69
// @LINE:67
// @LINE:65
// @LINE:63
// @LINE:61
// @LINE:59
// @LINE:57
// @LINE:55
// @LINE:53
// @LINE:51
// @LINE:49
// @LINE:47
// @LINE:45
// @LINE:43
// @LINE:41
// @LINE:39
// @LINE:37
// @LINE:35
// @LINE:33
// @LINE:31
// @LINE:29
// @LINE:27
// @LINE:25
// @LINE:23
// @LINE:21
// @LINE:19
// @LINE:17
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:5
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:115
class ReverseAssets {


// @LINE:115
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:150
// @LINE:146
// @LINE:144
// @LINE:142
// @LINE:140
// @LINE:135
// @LINE:133
// @LINE:131
// @LINE:129
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:120
// @LINE:118
// @LINE:113
// @LINE:111
// @LINE:105
// @LINE:103
// @LINE:101
// @LINE:99
// @LINE:97
// @LINE:95
// @LINE:93
// @LINE:91
// @LINE:89
// @LINE:87
// @LINE:85
// @LINE:83
// @LINE:81
// @LINE:79
// @LINE:77
// @LINE:75
// @LINE:73
// @LINE:71
// @LINE:69
// @LINE:67
// @LINE:65
// @LINE:63
// @LINE:61
// @LINE:59
// @LINE:57
// @LINE:55
// @LINE:53
// @LINE:51
// @LINE:49
// @LINE:47
// @LINE:45
// @LINE:43
// @LINE:41
// @LINE:39
// @LINE:37
// @LINE:35
// @LINE:33
// @LINE:31
// @LINE:29
// @LINE:27
// @LINE:25
// @LINE:23
// @LINE:21
// @LINE:19
// @LINE:17
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:5
class ReverseApplication {


// @LINE:51
def ShowInitiateStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.ShowInitiateStudy",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "InitiateStudy"})
      }
   """
)
                        

// @LINE:91
def showAddTopSummary : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showAddTopSummary",
   """
      function(studyId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showAddTopSummary" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyId", studyId)])})
      }
   """
)
                        

// @LINE:23
def GetSignal : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.GetSignal",
   """
      function(task,subject,studyId,signal_type,signal_sequence) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "GetSignal" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("task", task), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("subject", subject), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyId", studyId), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("signal_type", signal_type), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("signal_sequence", signal_sequence)])})
      }
   """
)
                        

// @LINE:101
def drp : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.drp",
   """
      function(studyNo) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "drp" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo)])})
      }
   """
)
                        

// @LINE:126
def videoExample : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.videoExample",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "videoExample"})
      }
   """
)
                        

// @LINE:17
def getInfo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getInfo",
   """
      function(task,subject,studyId,signal_seq) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getInfo" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("task", task), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("subject", subject), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyId", studyId), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("signal_seq", signal_seq)])})
      }
   """
)
                        

// @LINE:41
def displaySubjectInProgress : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.displaySubjectInProgress",
   """
      function(studyNo) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "displaySubjectsInProgress" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo)])})
      }
   """
)
                        

// @LINE:75
def EditHideSubject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.EditHideSubject",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "editHideSubject"})
      }
   """
)
                        

// @LINE:142
// @LINE:140
def authentication : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.authentication",
   """
      function() {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
      }
   """
)
                        

// @LINE:89
def showAddPsychometric : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showAddPsychometric",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showAddPsychometric"})
      }
   """
)
                        

// @LINE:85
def howToCreateNewStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.howToCreateNewStudy",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "howToCreateNewStudy"})
      }
   """
)
                        

// @LINE:39
def displaySubject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.displaySubject",
   """
      function(studyNo,SubjectId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "displaySubject" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("SubjectId", SubjectId)])})
      }
   """
)
                        

// @LINE:135
def test : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.test",
   """
      function(stydtNo) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "test" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("stydtNo", stydtNo)])})
      }
   """
)
                        

// @LINE:49
def InitiateNewStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.InitiateNewStudy",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "InitiateStudy"})
      }
   """
)
                        

// @LINE:77
def AddMoreSubjects : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.AddMoreSubjects",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addMoreSubjects"})
      }
   """
)
                        

// @LINE:95
def showAllDataTypes : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showAllDataTypes",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showAllDataTypes"})
      }
   """
)
                        

// @LINE:35
def showStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showStudy",
   """
      function(studyNo) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showStudy/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("studyNo", studyNo)})
      }
   """
)
                        

// @LINE:124
def tabularExample : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.tabularExample",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tabularExample"})
      }
   """
)
                        

// @LINE:97
def showAllSubjectToHide : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showAllSubjectToHide",
   """
      function(studyId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showAllSubjectsHide" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyId", studyId)])})
      }
   """
)
                        

// @LINE:71
def EditDataType : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.EditDataType",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "editExistingDataType"})
      }
   """
)
                        

// @LINE:21
def getPsycho : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getPsycho",
   """
      function(task,subject,studyId,signal_seq) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getPsycho" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("task", task), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("subject", subject), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyId", studyId), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("signal_seq", signal_seq)])})
      }
   """
)
                        

// @LINE:103
def startNewSubject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.startNewSubject",
   """
      function(loc,subjectLocation,subjectid) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "startNewSubject" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("loc", loc), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("subjectLocation", subjectLocation), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("subjectid", subjectid)])})
      }
   """
)
                        

// @LINE:113
def showAllPsychometric : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showAllPsychometric",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showAllPsychometric"})
      }
   """
)
                        

// @LINE:81
// @LINE:9
def Front : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.Front",
   """
      function() {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "frontPage"})
      }
      }
   """
)
                        

// @LINE:45
def getRadar : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getRadar",
   """
      function(studyNo,SubjectId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getRadar" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("SubjectId", SubjectId)])})
      }
   """
)
                        

// @LINE:129
def getVideo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getVideo",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "videoB"})
      }
   """
)
                        

// @LINE:55
def RealTime : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.RealTime",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "realTime"})
      }
   """
)
                        

// @LINE:122
def PychometricExample : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.PychometricExample",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "PychometricExample"})
      }
   """
)
                        

// @LINE:33
def showPyramid : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showPyramid",
   """
      function(studyNo) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showPyramid" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo)])})
      }
   """
)
                        

// @LINE:25
def showSignalRealTime2 : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showSignalRealTime2",
   """
      function(fileId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getRealTimeChart" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("fileId", fileId)])})
      }
   """
)
                        

// @LINE:43
def showRadar : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showRadar",
   """
      function(studyNo,SubjectId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showRadar" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("SubjectId", SubjectId)])})
      }
   """
)
                        

// @LINE:120
def InfoExample : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.InfoExample",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "InfoExample"})
      }
   """
)
                        

// @LINE:57
def showSignalRealTime : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showSignalRealTime",
   """
      function(fileId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showSignalRealTime" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("fileId", fileId)])})
      }
   """
)
                        

// @LINE:111
def Getdummy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.Getdummy",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getDummy"})
      }
   """
)
                        

// @LINE:53
def addNewSubject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addNewSubject",
   """
      function(studyNo) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "addNewSubject" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo)])})
      }
   """
)
                        

// @LINE:63
def ProcessNewDataType : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.ProcessNewDataType",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "processNewDataType"})
      }
   """
)
                        

// @LINE:105
def updateVidoefromServer : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.updateVidoefromServer",
   """
      function(studyNo) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "updateVideos" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo)])})
      }
   """
)
                        

// @LINE:67
def ProcessNewTopSummary : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.ProcessNewTopSummary",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "ProcessNewTopSummary"})
      }
   """
)
                        

// @LINE:73
def EditPsychometric : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.EditPsychometric",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "editPsychometric"})
      }
   """
)
                        

// @LINE:69
def ProcessNewDescriptor : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.ProcessNewDescriptor",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "processNewDescriptor"})
      }
   """
)
                        

// @LINE:29
def zip : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.zip",
   """
      function(studyId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "downloadStudy" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyId", studyId)])})
      }
   """
)
                        

// @LINE:59
def InsertNewStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.InsertNewStudy",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "createStudy"})
      }
   """
)
                        

// @LINE:19
def getPRF : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getPRF",
   """
      function(task,subject,studyId,signal_type) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getPRF" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("task", task), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("subject", subject), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyId", studyId), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("signal_type", signal_type)])})
      }
   """
)
                        

// @LINE:150
def video : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.video",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "video"})
      }
   """
)
                        

// @LINE:99
def CreateStudyTest : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.CreateStudyTest",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "quickCreating"})
      }
   """
)
                        

// @LINE:93
def showAddDescriptor : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showAddDescriptor",
   """
      function(studyId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showAddDescriptor" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyId", studyId)])})
      }
   """
)
                        

// @LINE:13
def ReceiveOauthData : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.ReceiveOauthData",
   """
      function(state,code) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "display" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("state", state), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("code", code)])})
      }
   """
)
                        

// @LINE:118
def signalDataExample : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.signalDataExample",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signalDataExample"})
      }
   """
)
                        

// @LINE:144
def ShowDriveDialog : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.ShowDriveDialog",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ShowDriveDialog"})
      }
   """
)
                        

// @LINE:79
def logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.logout",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
      }
   """
)
                        

// @LINE:11
def Main : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.Main",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "home"})
      }
   """
)
                        

// @LINE:5
def socket : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.socket",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ws"})
      }
   """
)
                        

// @LINE:15
def getPortraitInfo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getPortraitInfo",
   """
      function(studyId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "portrait" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyId", studyId)])})
      }
   """
)
                        

// @LINE:146
def shareMyStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.shareMyStudy",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "InsertStudy"})
      }
   """
)
                        

// @LINE:133
def getVideo3 : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getVideo3",
   """
      function(studyNo,subjectId,sessionName,fileName) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "videoC" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("subjectId", subjectId), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("sessionName", sessionName), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("fileName", fileName)])})
      }
   """
)
                        

// @LINE:37
def showStudySkipTop : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showStudySkipTop",
   """
      function(studyNo) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showStudySkipTop/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("studyNo", studyNo)})
      }
   """
)
                        

// @LINE:87
def showAddDataType : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showAddDataType",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showAddDataType"})
      }
   """
)
                        

// @LINE:131
def getVideo2 : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getVideo2",
   """
      function(studyNo,subjectId,sessionName) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "videoD" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("subjectId", subjectId), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("sessionName", sessionName)])})
      }
   """
)
                        

// @LINE:83
def setUpStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.setUpStudy",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "setUpStudy"})
      }
   """
)
                        

// @LINE:47
def ShowCreateStudyForm : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.ShowCreateStudyForm",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "createStudy"})
      }
   """
)
                        

// @LINE:31
def displayStudies : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.displayStudies",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "allStudies"})
      }
   """
)
                        

// @LINE:27
def file : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.file",
   """
      function(task,subject,studyId,signal_type,signal_sequence) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "file" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("task", task), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("subject", subject), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyId", studyId), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("signal_type", signal_type), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("signal_sequence", signal_sequence)])})
      }
   """
)
                        

// @LINE:61
def deleteStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.deleteStudy",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "deleteStudy"})
      }
   """
)
                        

// @LINE:65
def ProcessNewPsychometric : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.ProcessNewPsychometric",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "processNewPsychometric"})
      }
   """
)
                        

}
              
}
        


// @LINE:150
// @LINE:146
// @LINE:144
// @LINE:142
// @LINE:140
// @LINE:135
// @LINE:133
// @LINE:131
// @LINE:129
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:120
// @LINE:118
// @LINE:115
// @LINE:113
// @LINE:111
// @LINE:105
// @LINE:103
// @LINE:101
// @LINE:99
// @LINE:97
// @LINE:95
// @LINE:93
// @LINE:91
// @LINE:89
// @LINE:87
// @LINE:85
// @LINE:83
// @LINE:81
// @LINE:79
// @LINE:77
// @LINE:75
// @LINE:73
// @LINE:71
// @LINE:69
// @LINE:67
// @LINE:65
// @LINE:63
// @LINE:61
// @LINE:59
// @LINE:57
// @LINE:55
// @LINE:53
// @LINE:51
// @LINE:49
// @LINE:47
// @LINE:45
// @LINE:43
// @LINE:41
// @LINE:39
// @LINE:37
// @LINE:35
// @LINE:33
// @LINE:31
// @LINE:29
// @LINE:27
// @LINE:25
// @LINE:23
// @LINE:21
// @LINE:19
// @LINE:17
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:5
package controllers.ref {


// @LINE:115
class ReverseAssets {


// @LINE:115
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:150
// @LINE:146
// @LINE:144
// @LINE:142
// @LINE:140
// @LINE:135
// @LINE:133
// @LINE:131
// @LINE:129
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:120
// @LINE:118
// @LINE:113
// @LINE:111
// @LINE:105
// @LINE:103
// @LINE:101
// @LINE:99
// @LINE:97
// @LINE:95
// @LINE:93
// @LINE:91
// @LINE:89
// @LINE:87
// @LINE:85
// @LINE:83
// @LINE:81
// @LINE:79
// @LINE:77
// @LINE:75
// @LINE:73
// @LINE:71
// @LINE:69
// @LINE:67
// @LINE:65
// @LINE:63
// @LINE:61
// @LINE:59
// @LINE:57
// @LINE:55
// @LINE:53
// @LINE:51
// @LINE:49
// @LINE:47
// @LINE:45
// @LINE:43
// @LINE:41
// @LINE:39
// @LINE:37
// @LINE:35
// @LINE:33
// @LINE:31
// @LINE:29
// @LINE:27
// @LINE:25
// @LINE:23
// @LINE:21
// @LINE:19
// @LINE:17
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:5
class ReverseApplication {


// @LINE:51
def ShowInitiateStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ShowInitiateStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ShowInitiateStudy", Seq(), "GET", """""", _prefix + """InitiateStudy""")
)
                      

// @LINE:91
def showAddTopSummary(studyId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showAddTopSummary(studyId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAddTopSummary", Seq(classOf[Int]), "GET", """""", _prefix + """showAddTopSummary""")
)
                      

// @LINE:23
def GetSignal(task:String, subject:String, studyId:Int, signal_type:Int, signal_sequence:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.GetSignal(task, subject, studyId, signal_type, signal_sequence), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "GetSignal", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int], classOf[Int]), "GET", """""", _prefix + """GetSignal""")
)
                      

// @LINE:101
def drp(studyNo:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.drp(studyNo), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "drp", Seq(classOf[Int]), "GET", """""", _prefix + """drp""")
)
                      

// @LINE:126
def videoExample(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.videoExample(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "videoExample", Seq(), "GET", """""", _prefix + """videoExample""")
)
                      

// @LINE:17
def getInfo(task:String, subject:String, studyId:Int, signal_seq:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getInfo(task, subject, studyId, signal_seq), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getInfo", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int]), "GET", """""", _prefix + """getInfo""")
)
                      

// @LINE:41
def displaySubjectInProgress(studyNo:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.displaySubjectInProgress(studyNo), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displaySubjectInProgress", Seq(classOf[Int]), "GET", """""", _prefix + """displaySubjectsInProgress""")
)
                      

// @LINE:75
def EditHideSubject(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.EditHideSubject(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "EditHideSubject", Seq(), "POST", """""", _prefix + """editHideSubject""")
)
                      

// @LINE:140
def authentication(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.authentication(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "authentication", Seq(), "GET", """""", _prefix + """login""")
)
                      

// @LINE:89
def showAddPsychometric(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showAddPsychometric(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAddPsychometric", Seq(), "GET", """""", _prefix + """showAddPsychometric""")
)
                      

// @LINE:85
def howToCreateNewStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.howToCreateNewStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "howToCreateNewStudy", Seq(), "GET", """""", _prefix + """howToCreateNewStudy""")
)
                      

// @LINE:39
def displaySubject(studyNo:Int, SubjectId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.displaySubject(studyNo, SubjectId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displaySubject", Seq(classOf[Int], classOf[String]), "GET", """""", _prefix + """displaySubject""")
)
                      

// @LINE:135
def test(stydtNo:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.test(stydtNo), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "test", Seq(classOf[Int]), "GET", """""", _prefix + """test""")
)
                      

// @LINE:49
def InitiateNewStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.InitiateNewStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InitiateNewStudy", Seq(), "POST", """""", _prefix + """InitiateStudy""")
)
                      

// @LINE:77
def AddMoreSubjects(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.AddMoreSubjects(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "AddMoreSubjects", Seq(), "POST", """""", _prefix + """addMoreSubjects""")
)
                      

// @LINE:95
def showAllDataTypes(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showAllDataTypes(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAllDataTypes", Seq(), "GET", """""", _prefix + """showAllDataTypes""")
)
                      

// @LINE:35
def showStudy(studyNo:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showStudy(studyNo), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showStudy", Seq(classOf[Int]), "GET", """""", _prefix + """showStudy/$studyNo<[^/]+>""")
)
                      

// @LINE:124
def tabularExample(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.tabularExample(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "tabularExample", Seq(), "GET", """""", _prefix + """tabularExample""")
)
                      

// @LINE:97
def showAllSubjectToHide(studyId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showAllSubjectToHide(studyId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAllSubjectToHide", Seq(classOf[Int]), "GET", """""", _prefix + """showAllSubjectsHide""")
)
                      

// @LINE:71
def EditDataType(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.EditDataType(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "EditDataType", Seq(), "POST", """""", _prefix + """editExistingDataType""")
)
                      

// @LINE:21
def getPsycho(task:String, subject:String, studyId:Int, signal_seq:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getPsycho(task, subject, studyId, signal_seq), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getPsycho", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int]), "GET", """""", _prefix + """getPsycho""")
)
                      

// @LINE:103
def startNewSubject(loc:String, subjectLocation:String, subjectid:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.startNewSubject(loc, subjectLocation, subjectid), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "startNewSubject", Seq(classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """startNewSubject""")
)
                      

// @LINE:113
def showAllPsychometric(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showAllPsychometric(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAllPsychometric", Seq(), "GET", """""", _prefix + """showAllPsychometric""")
)
                      

// @LINE:9
def Front(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.Front(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Front", Seq(), "GET", """""", _prefix + """""")
)
                      

// @LINE:45
def getRadar(studyNo:Int, SubjectId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getRadar(studyNo, SubjectId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getRadar", Seq(classOf[Int], classOf[String]), "GET", """""", _prefix + """getRadar""")
)
                      

// @LINE:129
def getVideo(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getVideo(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getVideo", Seq(), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """videoB""")
)
                      

// @LINE:55
def RealTime(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.RealTime(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "RealTime", Seq(), "GET", """""", _prefix + """realTime""")
)
                      

// @LINE:122
def PychometricExample(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.PychometricExample(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "PychometricExample", Seq(), "GET", """""", _prefix + """PychometricExample""")
)
                      

// @LINE:33
def showPyramid(studyNo:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showPyramid(studyNo), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showPyramid", Seq(classOf[Int]), "GET", """""", _prefix + """showPyramid""")
)
                      

// @LINE:25
def showSignalRealTime2(fileId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showSignalRealTime2(fileId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showSignalRealTime2", Seq(classOf[String]), "GET", """""", _prefix + """getRealTimeChart""")
)
                      

// @LINE:43
def showRadar(studyNo:Int, SubjectId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showRadar(studyNo, SubjectId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showRadar", Seq(classOf[Int], classOf[String]), "GET", """""", _prefix + """showRadar""")
)
                      

// @LINE:120
def InfoExample(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.InfoExample(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InfoExample", Seq(), "GET", """""", _prefix + """InfoExample""")
)
                      

// @LINE:57
def showSignalRealTime(fileId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showSignalRealTime(fileId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showSignalRealTime", Seq(classOf[String]), "GET", """""", _prefix + """showSignalRealTime""")
)
                      

// @LINE:111
def Getdummy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.Getdummy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Getdummy", Seq(), "GET", """""", _prefix + """getDummy""")
)
                      

// @LINE:53
def addNewSubject(studyNo:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addNewSubject(studyNo), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "addNewSubject", Seq(classOf[Int]), "GET", """""", _prefix + """addNewSubject""")
)
                      

// @LINE:63
def ProcessNewDataType(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ProcessNewDataType(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ProcessNewDataType", Seq(), "POST", """""", _prefix + """processNewDataType""")
)
                      

// @LINE:105
def updateVidoefromServer(studyNo:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.updateVidoefromServer(studyNo), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "updateVidoefromServer", Seq(classOf[Int]), "GET", """""", _prefix + """updateVideos""")
)
                      

// @LINE:67
def ProcessNewTopSummary(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ProcessNewTopSummary(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ProcessNewTopSummary", Seq(), "POST", """""", _prefix + """ProcessNewTopSummary""")
)
                      

// @LINE:73
def EditPsychometric(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.EditPsychometric(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "EditPsychometric", Seq(), "POST", """""", _prefix + """editPsychometric""")
)
                      

// @LINE:69
def ProcessNewDescriptor(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ProcessNewDescriptor(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ProcessNewDescriptor", Seq(), "POST", """""", _prefix + """processNewDescriptor""")
)
                      

// @LINE:29
def zip(studyId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.zip(studyId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "zip", Seq(classOf[Int]), "GET", """""", _prefix + """downloadStudy""")
)
                      

// @LINE:59
def InsertNewStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.InsertNewStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InsertNewStudy", Seq(), "POST", """""", _prefix + """createStudy""")
)
                      

// @LINE:19
def getPRF(task:String, subject:String, studyId:Int, signal_type:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getPRF(task, subject, studyId, signal_type), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getPRF", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int]), "GET", """""", _prefix + """getPRF""")
)
                      

// @LINE:150
def video(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.video(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "video", Seq(), "GET", """to be deleted""", _prefix + """video""")
)
                      

// @LINE:99
def CreateStudyTest(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.CreateStudyTest(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "CreateStudyTest", Seq(), "GET", """""", _prefix + """quickCreating""")
)
                      

// @LINE:93
def showAddDescriptor(studyId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showAddDescriptor(studyId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAddDescriptor", Seq(classOf[Int]), "GET", """""", _prefix + """showAddDescriptor""")
)
                      

// @LINE:13
def ReceiveOauthData(state:String, code:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ReceiveOauthData(state, code), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ReceiveOauthData", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """display""")
)
                      

// @LINE:118
def signalDataExample(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.signalDataExample(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "signalDataExample", Seq(), "GET", """""", _prefix + """signalDataExample""")
)
                      

// @LINE:144
def ShowDriveDialog(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ShowDriveDialog(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ShowDriveDialog", Seq(), "GET", """""", _prefix + """ShowDriveDialog""")
)
                      

// @LINE:79
def logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.logout(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "logout", Seq(), "POST", """""", _prefix + """logout""")
)
                      

// @LINE:11
def Main(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.Main(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Main", Seq(), "GET", """""", _prefix + """home""")
)
                      

// @LINE:5
def socket(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.socket(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "socket", Seq(), "GET", """ Routes
 This file defines all application routes (Higher priority routes first)
 ~~~~
Web Socket management""", _prefix + """ws""")
)
                      

// @LINE:15
def getPortraitInfo(studyId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getPortraitInfo(studyId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getPortraitInfo", Seq(classOf[Int]), "GET", """""", _prefix + """portrait""")
)
                      

// @LINE:146
def shareMyStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.shareMyStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "shareMyStudy", Seq(), "POST", """""", _prefix + """InsertStudy""")
)
                      

// @LINE:133
def getVideo3(studyNo:Int, subjectId:String, sessionName:String, fileName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getVideo3(studyNo, subjectId, sessionName, fileName), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getVideo3", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """videoC""")
)
                      

// @LINE:37
def showStudySkipTop(studyNo:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showStudySkipTop(studyNo), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showStudySkipTop", Seq(classOf[Int]), "GET", """""", _prefix + """showStudySkipTop/$studyNo<[^/]+>""")
)
                      

// @LINE:87
def showAddDataType(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showAddDataType(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAddDataType", Seq(), "GET", """""", _prefix + """showAddDataType""")
)
                      

// @LINE:131
def getVideo2(studyNo:Int, subjectId:String, sessionName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getVideo2(studyNo, subjectId, sessionName), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getVideo2", Seq(classOf[Int], classOf[String], classOf[String]), "GET", """""", _prefix + """videoD""")
)
                      

// @LINE:83
def setUpStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.setUpStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "setUpStudy", Seq(), "GET", """""", _prefix + """setUpStudy""")
)
                      

// @LINE:47
def ShowCreateStudyForm(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ShowCreateStudyForm(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ShowCreateStudyForm", Seq(), "GET", """""", _prefix + """createStudy""")
)
                      

// @LINE:31
def displayStudies(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.displayStudies(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displayStudies", Seq(), "GET", """""", _prefix + """allStudies""")
)
                      

// @LINE:27
def file(task:String, subject:String, studyId:Int, signal_type:Int, signal_sequence:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.file(task, subject, studyId, signal_type, signal_sequence), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "file", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int], classOf[Int]), "GET", """""", _prefix + """file""")
)
                      

// @LINE:61
def deleteStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.deleteStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "deleteStudy", Seq(), "POST", """""", _prefix + """deleteStudy""")
)
                      

// @LINE:65
def ProcessNewPsychometric(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ProcessNewPsychometric(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ProcessNewPsychometric", Seq(), "POST", """""", _prefix + """processNewPsychometric""")
)
                      

}
                          
}
        
    