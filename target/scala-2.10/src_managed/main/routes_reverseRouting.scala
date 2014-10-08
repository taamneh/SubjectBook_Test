// @SOURCE:C:/Users/staamneh/Desktop/CPL-Lab/System Desgin/first_play/conf/routes
// @HASH:29075a6f12bd2e729ef21d2b681e55bbdfffdce3
// @DATE:Fri Oct 03 01:13:20 CDT 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString


// @LINE:59
// @LINE:54
// @LINE:52
// @LINE:49
// @LINE:47
// @LINE:44
// @LINE:42
// @LINE:40
// @LINE:38
// @LINE:36
// @LINE:34
// @LINE:32
// @LINE:30
// @LINE:28
// @LINE:26
// @LINE:24
// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:14
// @LINE:11
// @LINE:6
package controllers {

// @LINE:54
class ReverseAssets {


// @LINE:54
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:59
// @LINE:52
// @LINE:49
// @LINE:47
// @LINE:44
// @LINE:42
// @LINE:40
// @LINE:38
// @LINE:36
// @LINE:34
// @LINE:32
// @LINE:30
// @LINE:28
// @LINE:26
// @LINE:24
// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:14
// @LINE:11
// @LINE:6
class ReverseApplication {


// @LINE:18
def authentication(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "login")
}
                        

// @LINE:38
def displaySubject(studyNo:Int, SubjectId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "displaySubject" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)), Some(implicitly[QueryStringBindable[String]].unbind("SubjectId", SubjectId)))))
}
                        

// @LINE:32
def InsertSession(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "InsertSession")
}
                        

// @LINE:44
def uploadFile(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "upload")
}
                        

// @LINE:16
def Login(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

// @LINE:59
def showStudy(studyNo:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "showStudy/" + implicitly[PathBindable[Int]].unbind("studyNo", studyNo))
}
                        

// @LINE:49
def editStudy(studyId:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "editStudy/" + implicitly[PathBindable[Int]].unbind("studyId", studyId))
}
                        

// @LINE:47
def video(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "video")
}
                        

// @LINE:24
def submitRegistration(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "signup")
}
                        

// @LINE:52
def findImage(img_no:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "test/" + implicitly[PathBindable[Int]].unbind("img_no", img_no))
}
                        

// @LINE:20
def logout(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "logout")
}
                        

// @LINE:40
def editSubject(studyNo:Int, SubjectId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "editSubject" + queryString(List(Some(implicitly[QueryStringBindable[Int]].unbind("studyNo", studyNo)), Some(implicitly[QueryStringBindable[String]].unbind("SubjectId", SubjectId)))))
}
                        

// @LINE:22
def Register(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "signup")
}
                        

// @LINE:14
def file(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "file")
}
                        

// @LINE:30
def insertStudy(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "Create")
}
                        

// @LINE:28
def insertSubject(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "InsertSubject")
}
                        

// @LINE:26
def shareMyStudy(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "InsertStudy")
}
                        

// @LINE:11
def test(task:String, subject:String, studyId:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "test" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("task", task)), Some(implicitly[QueryStringBindable[String]].unbind("subject", subject)), Some(implicitly[QueryStringBindable[Int]].unbind("studyId", studyId)))))
}
                        

// @LINE:6
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "index")
}
                        

// @LINE:36
// @LINE:34
def displayStudies(): Call = {
   () match {
// @LINE:34
case ()  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "allStudies")
                                         
   }
}
                                                

// @LINE:42
def displayFortest(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "display")
}
                        

}
                          
}
                  


// @LINE:59
// @LINE:54
// @LINE:52
// @LINE:49
// @LINE:47
// @LINE:44
// @LINE:42
// @LINE:40
// @LINE:38
// @LINE:36
// @LINE:34
// @LINE:32
// @LINE:30
// @LINE:28
// @LINE:26
// @LINE:24
// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:14
// @LINE:11
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:54
class ReverseAssets {


// @LINE:54
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:59
// @LINE:52
// @LINE:49
// @LINE:47
// @LINE:44
// @LINE:42
// @LINE:40
// @LINE:38
// @LINE:36
// @LINE:34
// @LINE:32
// @LINE:30
// @LINE:28
// @LINE:26
// @LINE:24
// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:14
// @LINE:11
// @LINE:6
class ReverseApplication {


// @LINE:18
def authentication : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.authentication",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        

// @LINE:38
def displaySubject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.displaySubject",
   """
      function(studyNo,SubjectId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "displaySubject" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("SubjectId", SubjectId)])})
      }
   """
)
                        

// @LINE:32
def InsertSession : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.InsertSession",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "InsertSession"})
      }
   """
)
                        

// @LINE:44
def uploadFile : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.uploadFile",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "upload"})
      }
   """
)
                        

// @LINE:16
def Login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.Login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:59
def showStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.showStudy",
   """
      function(studyNo) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showStudy/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("studyNo", studyNo)})
      }
   """
)
                        

// @LINE:49
def editStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.editStudy",
   """
      function(studyId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "editStudy/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("studyId", studyId)})
      }
   """
)
                        

// @LINE:47
def video : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.video",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "video"})
      }
   """
)
                        

// @LINE:24
def submitRegistration : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.submitRegistration",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
      }
   """
)
                        

// @LINE:52
def findImage : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.findImage",
   """
      function(img_no) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "test/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("img_no", img_no)})
      }
   """
)
                        

// @LINE:20
def logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.logout",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
      }
   """
)
                        

// @LINE:40
def editSubject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.editSubject",
   """
      function(studyNo,SubjectId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "editSubject" + _qS([(""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyNo", studyNo), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("SubjectId", SubjectId)])})
      }
   """
)
                        

// @LINE:22
def Register : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.Register",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
      }
   """
)
                        

// @LINE:14
def file : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.file",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "file"})
      }
   """
)
                        

// @LINE:30
def insertStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.insertStudy",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "Create"})
      }
   """
)
                        

// @LINE:28
def insertSubject : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.insertSubject",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "InsertSubject"})
      }
   """
)
                        

// @LINE:26
def shareMyStudy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.shareMyStudy",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "InsertStudy"})
      }
   """
)
                        

// @LINE:11
def test : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.test",
   """
      function(task,subject,studyId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "test" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("task", task), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("subject", subject), (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("studyId", studyId)])})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "index"})
      }
   """
)
                        

// @LINE:36
// @LINE:34
def displayStudies : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.displayStudies",
   """
      function() {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "allStudies"})
      }
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "allStudies"})
      }
      }
   """
)
                        

// @LINE:42
def displayFortest : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.displayFortest",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "display"})
      }
   """
)
                        

}
              
}
        


// @LINE:59
// @LINE:54
// @LINE:52
// @LINE:49
// @LINE:47
// @LINE:44
// @LINE:42
// @LINE:40
// @LINE:38
// @LINE:36
// @LINE:34
// @LINE:32
// @LINE:30
// @LINE:28
// @LINE:26
// @LINE:24
// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:14
// @LINE:11
// @LINE:6
package controllers.ref {


// @LINE:54
class ReverseAssets {


// @LINE:54
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:59
// @LINE:52
// @LINE:49
// @LINE:47
// @LINE:44
// @LINE:42
// @LINE:40
// @LINE:38
// @LINE:36
// @LINE:34
// @LINE:32
// @LINE:30
// @LINE:28
// @LINE:26
// @LINE:24
// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:14
// @LINE:11
// @LINE:6
class ReverseApplication {


// @LINE:18
def authentication(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.authentication(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "authentication", Seq(), "POST", """""", _prefix + """login""")
)
                      

// @LINE:38
def displaySubject(studyNo:Int, SubjectId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.displaySubject(studyNo, SubjectId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displaySubject", Seq(classOf[Int], classOf[String]), "GET", """""", _prefix + """displaySubject""")
)
                      

// @LINE:32
def InsertSession(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.InsertSession(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "InsertSession", Seq(), "POST", """""", _prefix + """InsertSession""")
)
                      

// @LINE:44
def uploadFile(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.uploadFile(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "uploadFile", Seq(), "POST", """""", _prefix + """upload""")
)
                      

// @LINE:16
def Login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.Login(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Login", Seq(), "GET", """""", _prefix + """""")
)
                      

// @LINE:59
def showStudy(studyNo:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.showStudy(studyNo), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "showStudy", Seq(classOf[Int]), "GET", """""", _prefix + """showStudy/$studyNo<[^/]+>""")
)
                      

// @LINE:49
def editStudy(studyId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.editStudy(studyId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "editStudy", Seq(classOf[Int]), "GET", """""", _prefix + """editStudy/$studyId<[^/]+>""")
)
                      

// @LINE:47
def video(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.video(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "video", Seq(), "GET", """to be deleted""", _prefix + """video""")
)
                      

// @LINE:24
def submitRegistration(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.submitRegistration(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "submitRegistration", Seq(), "POST", """""", _prefix + """signup""")
)
                      

// @LINE:52
def findImage(img_no:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.findImage(img_no), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "findImage", Seq(classOf[Int]), "GET", """to be deleted""", _prefix + """test/$img_no<[^/]+>""")
)
                      

// @LINE:20
def logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.logout(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "logout", Seq(), "POST", """""", _prefix + """logout""")
)
                      

// @LINE:40
def editSubject(studyNo:Int, SubjectId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.editSubject(studyNo, SubjectId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "editSubject", Seq(classOf[Int], classOf[String]), "GET", """""", _prefix + """editSubject""")
)
                      

// @LINE:22
def Register(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.Register(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "Register", Seq(), "GET", """""", _prefix + """signup""")
)
                      

// @LINE:14
def file(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.file(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "file", Seq(), "GET", """to be deleted""", _prefix + """file""")
)
                      

// @LINE:30
def insertStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.insertStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "insertStudy", Seq(), "POST", """""", _prefix + """Create""")
)
                      

// @LINE:28
def insertSubject(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.insertSubject(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "insertSubject", Seq(), "POST", """""", _prefix + """InsertSubject""")
)
                      

// @LINE:26
def shareMyStudy(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.shareMyStudy(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "shareMyStudy", Seq(), "POST", """""", _prefix + """InsertStudy""")
)
                      

// @LINE:11
def test(task:String, subject:String, studyId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.test(task, subject, studyId), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "test", Seq(classOf[String], classOf[String], classOf[Int]), "GET", """change the name""", _prefix + """test""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """index""")
)
                      

// @LINE:34
def displayStudies(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.displayStudies(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displayStudies", Seq(), "GET", """""", _prefix + """allStudies""")
)
                      

// @LINE:42
def displayFortest(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.displayFortest(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "displayFortest", Seq(), "GET", """""", _prefix + """display""")
)
                      

}
                          
}
        
    