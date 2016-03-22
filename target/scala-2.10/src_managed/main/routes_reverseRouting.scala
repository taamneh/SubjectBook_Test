// @SOURCE:C:/first_play/conf/routes
// @HASH:83aab6c063d03debc3cdf27a0b51c076474e93f0
// @DATE:Fri Mar 11 14:58:28 CST 2016

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString


// @LINE:137
// @LINE:135
// @LINE:132
// @LINE:130
// @LINE:128
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:120
// @LINE:118
// @LINE:116
// @LINE:114
// @LINE:109
// @LINE:107
// @LINE:104
// @LINE:102
// @LINE:100
// @LINE:98
// @LINE:96
// @LINE:93
// @LINE:91
// @LINE:89
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

// @LINE:93
class ReverseAssets {


// @LINE:93
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:137
// @LINE:135
// @LINE:132
// @LINE:130
// @LINE:128
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:120
// @LINE:118
// @LINE:116
// @LINE:114
// @LINE:109
// @LINE:107
// @LINE:104
// @LINE:102
// @LINE:100
// @LINE:98
// @LINE:96
// @LINE:91
// @LINE:89
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


// @LINE:79
def showAddTopSummary(studyId:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showAddTopSummary" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)))))
}
                        

// @LINE:23
def GetSignal(task:String, subject:String, studyId:Int, signal_type:Int, signal_sequence:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "GetSignal" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("task", task)), Some(implicitly[QueryStringBindable[String]].unbind("subject", subject)), Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)), Some(implicitly[QueryStringBindable[Int]].unbind("signal_type", signal_type)), Some(implicitly[QueryStringBindable[Int]].unbind("signal_sequence", signal_sequence)))))
}
                        

// @LINE:104
def videoExample(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "videoExample")
}
                        

// @LINE:17
def getInfo(task:String, subject:String, studyId:Int, signal_seq:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "getInfo" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("task", task)), Some(implicitly[QueryStringBindable[String]].unbind("subject", subject)), Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)), Some(implicitly[QueryStringBindable[Int]].unbind("signal_seq", signal_seq)))))
}
                        

// @LINE:65
def EditHideSubject(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "editHideSubject")
}
                        

// @LINE:116
// @LINE:114
def authentication(): Call = {
   () match {
// @LINE:114
case ()  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "login")
                                         
   }
}
                                                

// @LINE:77
def showAddPsychometric(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showAddPsychometric")
}
                        

// @LINE:73
def howToCreateNewStudy(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "howToCreateNewStudy")
}
                        

// @LINE:39
def displaySubject(studyNo:Int, SubjectId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "displaySubject" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)), Some(implicitly[QueryStringBindable[String]].unbind("SubjectId", SubjectId)))))
}
                        

// @LINE:128
def InsertSession(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "InsertSession")
}
                        

// @LINE:132
def uploadFile(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "upload")
}
                        

// @LINE:81
def showAllDataTypes(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showAllDataTypes")
}
                        

// @LINE:35
def showStudy(studyNo:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showStudy/" + implicitly[PathBindable[Int]].unbind("studyNo", studyNo))
}
                        

// @LINE:102
def tabularExample(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "tabularExample")
}
                        

// @LINE:137
def editStudy(studyId:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "editStudy/" + implicitly[PathBindable[Int]].unbind("studyId", studyId))
}
                        

// @LINE:83
def showAllSubjectToHide(studyId:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showAllSubjectsHide" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)))))
}
                        

// @LINE:61
def EditDataType(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "editExistingDataType")
}
                        

// @LINE:21
def getPsycho(task:String, subject:String, studyId:Int, signal_seq:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "getPsycho" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("task", task)), Some(implicitly[QueryStringBindable[String]].unbind("subject", subject)), Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)), Some(implicitly[QueryStringBindable[Int]].unbind("signal_seq", signal_seq)))))
}
                        

// @LINE:91
def showAllPsychometric(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showAllPsychometric")
}
                        

// @LINE:69
// @LINE:9
def Front(): Call = {
   () match {
// @LINE:9
case ()  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix)
                                         
   }
}
                                                

// @LINE:43
def getRadar(studyNo:Int, SubjectId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "getRadar" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)), Some(implicitly[QueryStringBindable[String]].unbind("SubjectId", SubjectId)))))
}
                        

// @LINE:107
def getVideo(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "videoB")
}
                        

// @LINE:47
def RealTime(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "realTime")
}
                        

// @LINE:100
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
                        

// @LINE:41
def showRadar(studyNo:Int, SubjectId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showRadar" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)), Some(implicitly[QueryStringBindable[String]].unbind("SubjectId", SubjectId)))))
}
                        

// @LINE:98
def InfoExample(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "InfoExample")
}
                        

// @LINE:49
def showSignalRealTime(fileId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showSignalRealTime" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("fileId", fileId)))))
}
                        

// @LINE:89
def Getdummy(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "getDummy")
}
                        

// @LINE:55
def ProcessNewDataType(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "processNewDataType")
}
                        

// @LINE:59
def ProcessNewTopSummary(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "ProcessNewTopSummary")
}
                        

// @LINE:63
def EditPsychometric(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "editPsychometric")
}
                        

// @LINE:29
def zip(studyId:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "downloadStudy" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)))))
}
                        

// @LINE:51
def InsertNewStudy(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "createStudy")
}
                        

// @LINE:19
def getPRF(task:String, subject:String, studyId:Int, signal_type:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "getPRF" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("task", task)), Some(implicitly[QueryStringBindable[String]].unbind("subject", subject)), Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)), Some(implicitly[QueryStringBindable[Int]].unbind("signal_type", signal_type)))))
}
                        

// @LINE:135
def video(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "video")
}
                        

// @LINE:122
def submitRegistration(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "signup")
}
                        

// @LINE:13
def ReceiveOauthData(state:String, code:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "display" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("state", state)), Some(implicitly[QueryStringBindable[String]].unbind("code", code)))))
}
                        

// @LINE:96
def signalDataExample(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "signalDataExample")
}
                        

// @LINE:118
def ShowDriveDialog(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "ShowDriveDialog")
}
                        

// @LINE:67
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
                        

// @LINE:109
def test(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "test")
}
                        

// @LINE:130
def editSubject(studyNo:Int, SubjectId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "editSubject" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)), Some(implicitly[QueryStringBindable[String]].unbind("SubjectId", SubjectId)))))
}
                        

// @LINE:120
def Register(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "signup")
}
                        

// @LINE:15
def getPortraitInfo(studyId:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "portrait" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)))))
}
                        

// @LINE:126
def insertSubject(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "InsertSubject")
}
                        

// @LINE:124
def shareMyStudy(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "InsertStudy")
}
                        

// @LINE:37
def showStudySkipTop(studyNo:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showStudySkipTop/" + implicitly[PathBindable[Int]].unbind("studyNo", studyNo))
}
                        

// @LINE:75
def showAddDataType(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showAddDataType")
}
                        

// @LINE:71
def setUpStudy(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "setUpStudy")
}
                        

// @LINE:45
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
                        

// @LINE:53
def deleteStudy(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "deleteStudy")
}
                        

// @LINE:57
def ProcessNewPsychometric(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "processNewPsychometric")
}
                        

}
                          
}
                  


// @LINE:137
// @LINE:135
// @LINE:132
// @LINE:130
// @LINE:128
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:120
// @LINE:118
// @LINE:116
// @LINE:114
// @LINE:109
// @LINE:107
// @LINE:104
// @LINE:102
// @LINE:100
// @LINE:98
// @LINE:96
// @LINE:93
// @LINE:91
// @LINE:89
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

// @LINE:93
class ReverseAssets {


// @LINE:93
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:137
// @LINE:135
// @LINE:132
// @LINE:130
// @LINE:128
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:120
// @LINE:118
// @LINE:116
// @LINE:114
// @LINE:109
// @LINE:107
// @LINE:104
// @LINE:102
// @LINE:100
// @LINE:98
// @LINE:96
// @LINE:91
// @LINE:89
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


// @LINE:79
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
                        

// @LINE:104
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
                        

// @LINE:65
def EditHideSubject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.EditHideSubject",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "editHideSubject"})
      }
   """
)
                        

// @LINE:116
// @LINE:114
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
                        

// @LINE:77
def showAddPsychometric : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showAddPsychometric",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showAddPsychometric"})
      }
   """
)
                        

// @LINE:73
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
                        

// @LINE:128
def InsertSession : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.InsertSession",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "InsertSession"})
      }
   """
)
                        

// @LINE:132
def uploadFile : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.uploadFile",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "upload"})
      }
   """
)
                        

// @LINE:81
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
                        

// @LINE:102
def tabularExample : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.tabularExample",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tabularExample"})
      }
   """
)
                        

// @LINE:137
def editStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.editStudy",
   """
      function(studyId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "editStudy/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("studyId", studyId)})
      }
   """
)
                        

// @LINE:83
def showAllSubjectToHide : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showAllSubjectToHide",
   """
      function(studyId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showAllSubjectsHide" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyId", studyId)])})
      }
   """
)
                        

// @LINE:61
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
                        

// @LINE:91
def showAllPsychometric : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showAllPsychometric",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showAllPsychometric"})
      }
   """
)
                        

// @LINE:69
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
                        

// @LINE:43
def getRadar : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getRadar",
   """
      function(studyNo,SubjectId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getRadar" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("SubjectId", SubjectId)])})
      }
   """
)
                        

// @LINE:107
def getVideo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getVideo",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "videoB"})
      }
   """
)
                        

// @LINE:47
def RealTime : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.RealTime",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "realTime"})
      }
   """
)
                        

// @LINE:100
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
                        

// @LINE:41
def showRadar : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showRadar",
   """
      function(studyNo,SubjectId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showRadar" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("SubjectId", SubjectId)])})
      }
   """
)
                        

// @LINE:98
def InfoExample : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.InfoExample",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "InfoExample"})
      }
   """
)
                        

// @LINE:49
def showSignalRealTime : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showSignalRealTime",
   """
      function(fileId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showSignalRealTime" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("fileId", fileId)])})
      }
   """
)
                        

// @LINE:89
def Getdummy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.Getdummy",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getDummy"})
      }
   """
)
                        

// @LINE:55
def ProcessNewDataType : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.ProcessNewDataType",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "processNewDataType"})
      }
   """
)
                        

// @LINE:59
def ProcessNewTopSummary : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.ProcessNewTopSummary",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "ProcessNewTopSummary"})
      }
   """
)
                        

// @LINE:63
def EditPsychometric : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.EditPsychometric",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "editPsychometric"})
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
                        

// @LINE:51
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
                        

// @LINE:135
def video : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.video",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "video"})
      }
   """
)
                        

// @LINE:122
def submitRegistration : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.submitRegistration",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
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
                        

// @LINE:96
def signalDataExample : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.signalDataExample",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signalDataExample"})
      }
   """
)
                        

// @LINE:118
def ShowDriveDialog : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.ShowDriveDialog",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ShowDriveDialog"})
      }
   """
)
                        

// @LINE:67
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
                        

// @LINE:109
def test : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.test",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "test"})
      }
   """
)
                        

// @LINE:130
def editSubject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.editSubject",
   """
      function(studyNo,SubjectId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "editSubject" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("SubjectId", SubjectId)])})
      }
   """
)
                        

// @LINE:120
def Register : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.Register",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
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
                        

// @LINE:126
def insertSubject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.insertSubject",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "InsertSubject"})
      }
   """
)
                        

// @LINE:124
def shareMyStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.shareMyStudy",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "InsertStudy"})
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
                        

// @LINE:75
def showAddDataType : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showAddDataType",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showAddDataType"})
      }
   """
)
                        

// @LINE:71
def setUpStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.setUpStudy",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "setUpStudy"})
      }
   """
)
                        

// @LINE:45
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
                        

// @LINE:53
def deleteStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.deleteStudy",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "deleteStudy"})
      }
   """
)
                        

// @LINE:57
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
        


// @LINE:137
// @LINE:135
// @LINE:132
// @LINE:130
// @LINE:128
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:120
// @LINE:118
// @LINE:116
// @LINE:114
// @LINE:109
// @LINE:107
// @LINE:104
// @LINE:102
// @LINE:100
// @LINE:98
// @LINE:96
// @LINE:93
// @LINE:91
// @LINE:89
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


// @LINE:93
class ReverseAssets {


// @LINE:93
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:137
// @LINE:135
// @LINE:132
// @LINE:130
// @LINE:128
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:120
// @LINE:118
// @LINE:116
// @LINE:114
// @LINE:109
// @LINE:107
// @LINE:104
// @LINE:102
// @LINE:100
// @LINE:98
// @LINE:96
// @LINE:91
// @LINE:89
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


// @LINE:79
def showAddTopSummary(studyId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showAddTopSummary(studyId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAddTopSummary", Seq(classOf[Int]), "GET", """""", _prefix + """showAddTopSummary""")
)
                      

// @LINE:23
def GetSignal(task:String, subject:String, studyId:Int, signal_type:Int, signal_sequence:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.GetSignal(task, subject, studyId, signal_type, signal_sequence), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "GetSignal", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int], classOf[Int]), "GET", """""", _prefix + """GetSignal""")
)
                      

// @LINE:104
def videoExample(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.videoExample(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "videoExample", Seq(), "GET", """""", _prefix + """videoExample""")
)
                      

// @LINE:17
def getInfo(task:String, subject:String, studyId:Int, signal_seq:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getInfo(task, subject, studyId, signal_seq), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getInfo", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int]), "GET", """""", _prefix + """getInfo""")
)
                      

// @LINE:65
def EditHideSubject(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.EditHideSubject(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "EditHideSubject", Seq(), "POST", """""", _prefix + """editHideSubject""")
)
                      

// @LINE:114
def authentication(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.authentication(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "authentication", Seq(), "GET", """""", _prefix + """login""")
)
                      

// @LINE:77
def showAddPsychometric(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showAddPsychometric(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAddPsychometric", Seq(), "GET", """""", _prefix + """showAddPsychometric""")
)
                      

// @LINE:73
def howToCreateNewStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.howToCreateNewStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "howToCreateNewStudy", Seq(), "GET", """""", _prefix + """howToCreateNewStudy""")
)
                      

// @LINE:39
def displaySubject(studyNo:Int, SubjectId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.displaySubject(studyNo, SubjectId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displaySubject", Seq(classOf[Int], classOf[String]), "GET", """""", _prefix + """displaySubject""")
)
                      

// @LINE:128
def InsertSession(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.InsertSession(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InsertSession", Seq(), "POST", """""", _prefix + """InsertSession""")
)
                      

// @LINE:132
def uploadFile(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.uploadFile(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "uploadFile", Seq(), "POST", """""", _prefix + """upload""")
)
                      

// @LINE:81
def showAllDataTypes(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showAllDataTypes(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAllDataTypes", Seq(), "GET", """""", _prefix + """showAllDataTypes""")
)
                      

// @LINE:35
def showStudy(studyNo:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showStudy(studyNo), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showStudy", Seq(classOf[Int]), "GET", """""", _prefix + """showStudy/$studyNo<[^/]+>""")
)
                      

// @LINE:102
def tabularExample(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.tabularExample(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "tabularExample", Seq(), "GET", """""", _prefix + """tabularExample""")
)
                      

// @LINE:137
def editStudy(studyId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.editStudy(studyId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "editStudy", Seq(classOf[Int]), "GET", """""", _prefix + """editStudy/$studyId<[^/]+>""")
)
                      

// @LINE:83
def showAllSubjectToHide(studyId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showAllSubjectToHide(studyId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAllSubjectToHide", Seq(classOf[Int]), "GET", """""", _prefix + """showAllSubjectsHide""")
)
                      

// @LINE:61
def EditDataType(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.EditDataType(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "EditDataType", Seq(), "POST", """""", _prefix + """editExistingDataType""")
)
                      

// @LINE:21
def getPsycho(task:String, subject:String, studyId:Int, signal_seq:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getPsycho(task, subject, studyId, signal_seq), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getPsycho", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int]), "GET", """""", _prefix + """getPsycho""")
)
                      

// @LINE:91
def showAllPsychometric(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showAllPsychometric(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAllPsychometric", Seq(), "GET", """""", _prefix + """showAllPsychometric""")
)
                      

// @LINE:9
def Front(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.Front(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Front", Seq(), "GET", """""", _prefix + """""")
)
                      

// @LINE:43
def getRadar(studyNo:Int, SubjectId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getRadar(studyNo, SubjectId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getRadar", Seq(classOf[Int], classOf[String]), "GET", """""", _prefix + """getRadar""")
)
                      

// @LINE:107
def getVideo(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getVideo(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getVideo", Seq(), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """videoB""")
)
                      

// @LINE:47
def RealTime(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.RealTime(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "RealTime", Seq(), "GET", """""", _prefix + """realTime""")
)
                      

// @LINE:100
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
                      

// @LINE:41
def showRadar(studyNo:Int, SubjectId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showRadar(studyNo, SubjectId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showRadar", Seq(classOf[Int], classOf[String]), "GET", """""", _prefix + """showRadar""")
)
                      

// @LINE:98
def InfoExample(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.InfoExample(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InfoExample", Seq(), "GET", """""", _prefix + """InfoExample""")
)
                      

// @LINE:49
def showSignalRealTime(fileId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showSignalRealTime(fileId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showSignalRealTime", Seq(classOf[String]), "GET", """""", _prefix + """showSignalRealTime""")
)
                      

// @LINE:89
def Getdummy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.Getdummy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Getdummy", Seq(), "GET", """""", _prefix + """getDummy""")
)
                      

// @LINE:55
def ProcessNewDataType(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ProcessNewDataType(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ProcessNewDataType", Seq(), "POST", """""", _prefix + """processNewDataType""")
)
                      

// @LINE:59
def ProcessNewTopSummary(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ProcessNewTopSummary(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ProcessNewTopSummary", Seq(), "POST", """""", _prefix + """ProcessNewTopSummary""")
)
                      

// @LINE:63
def EditPsychometric(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.EditPsychometric(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "EditPsychometric", Seq(), "POST", """""", _prefix + """editPsychometric""")
)
                      

// @LINE:29
def zip(studyId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.zip(studyId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "zip", Seq(classOf[Int]), "GET", """""", _prefix + """downloadStudy""")
)
                      

// @LINE:51
def InsertNewStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.InsertNewStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InsertNewStudy", Seq(), "POST", """""", _prefix + """createStudy""")
)
                      

// @LINE:19
def getPRF(task:String, subject:String, studyId:Int, signal_type:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getPRF(task, subject, studyId, signal_type), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getPRF", Seq(classOf[String], classOf[String], classOf[Int], classOf[Int]), "GET", """""", _prefix + """getPRF""")
)
                      

// @LINE:135
def video(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.video(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "video", Seq(), "GET", """to be deleted""", _prefix + """video""")
)
                      

// @LINE:122
def submitRegistration(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.submitRegistration(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "submitRegistration", Seq(), "POST", """""", _prefix + """signup""")
)
                      

// @LINE:13
def ReceiveOauthData(state:String, code:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ReceiveOauthData(state, code), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ReceiveOauthData", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """display""")
)
                      

// @LINE:96
def signalDataExample(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.signalDataExample(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "signalDataExample", Seq(), "GET", """""", _prefix + """signalDataExample""")
)
                      

// @LINE:118
def ShowDriveDialog(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ShowDriveDialog(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ShowDriveDialog", Seq(), "GET", """""", _prefix + """ShowDriveDialog""")
)
                      

// @LINE:67
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
                      

// @LINE:109
def test(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.test(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "test", Seq(), "GET", """""", _prefix + """test""")
)
                      

// @LINE:130
def editSubject(studyNo:Int, SubjectId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.editSubject(studyNo, SubjectId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "editSubject", Seq(classOf[Int], classOf[String]), "GET", """""", _prefix + """editSubject""")
)
                      

// @LINE:120
def Register(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.Register(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Register", Seq(), "GET", """""", _prefix + """signup""")
)
                      

// @LINE:15
def getPortraitInfo(studyId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getPortraitInfo(studyId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getPortraitInfo", Seq(classOf[Int]), "GET", """""", _prefix + """portrait""")
)
                      

// @LINE:126
def insertSubject(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.insertSubject(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "insertSubject", Seq(), "POST", """""", _prefix + """InsertSubject""")
)
                      

// @LINE:124
def shareMyStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.shareMyStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "shareMyStudy", Seq(), "POST", """""", _prefix + """InsertStudy""")
)
                      

// @LINE:37
def showStudySkipTop(studyNo:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showStudySkipTop(studyNo), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showStudySkipTop", Seq(classOf[Int]), "GET", """""", _prefix + """showStudySkipTop/$studyNo<[^/]+>""")
)
                      

// @LINE:75
def showAddDataType(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showAddDataType(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showAddDataType", Seq(), "GET", """""", _prefix + """showAddDataType""")
)
                      

// @LINE:71
def setUpStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.setUpStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "setUpStudy", Seq(), "GET", """""", _prefix + """setUpStudy""")
)
                      

// @LINE:45
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
                      

// @LINE:53
def deleteStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.deleteStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "deleteStudy", Seq(), "POST", """""", _prefix + """deleteStudy""")
)
                      

// @LINE:57
def ProcessNewPsychometric(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.ProcessNewPsychometric(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "ProcessNewPsychometric", Seq(), "POST", """""", _prefix + """processNewPsychometric""")
)
                      

}
                          
}
        
    