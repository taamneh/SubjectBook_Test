// @SOURCE:C:/Users/staamneh/Desktop/CPL-Lab/System Desgin/first_play/conf/routes
// @HASH:0f5a830c28e7cfa09d629aae81a56dfeb501238e
// @DATE:Wed Sep 10 13:23:45 CDT 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString


// @LINE:21
// @LINE:17
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:8
// @LINE:6
package controllers {

// @LINE:21
class ReverseAssets {


// @LINE:21
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:17
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:8
// @LINE:6
class ReverseApplication {


// @LINE:17
def video(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "video")
}
                        

// @LINE:8
def addPerson(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "person")
}
                        

// @LINE:13
def test(task:String, subject:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "test" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("task", task)), Some(implicitly[QueryStringBindable[String]].unbind("subject", subject)))))
}
                        

// @LINE:11
def getPersons(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "persons")
}
                        

// @LINE:15
def file(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "file")
}
                        

// @LINE:6
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

}
                          
}
                  


// @LINE:21
// @LINE:17
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:8
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:21
class ReverseAssets {


// @LINE:21
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:17
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:8
// @LINE:6
class ReverseApplication {


// @LINE:17
def video : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.video",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "video"})
      }
   """
)
                        

// @LINE:8
def addPerson : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addPerson",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "person"})
      }
   """
)
                        

// @LINE:13
def test : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.test",
   """
      function(task,subject) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "test" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("task", task), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("subject", subject)])})
      }
   """
)
                        

// @LINE:11
def getPersons : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getPersons",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "persons"})
      }
   """
)
                        

// @LINE:15
def file : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.file",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "file"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

}
              
}
        


// @LINE:21
// @LINE:17
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:8
// @LINE:6
package controllers.ref {


// @LINE:21
class ReverseAssets {


// @LINE:21
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:17
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:8
// @LINE:6
class ReverseApplication {


// @LINE:17
def video(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.video(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "video", Seq(), "GET", """""", _prefix + """video""")
)
                      

// @LINE:8
def addPerson(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addPerson(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "addPerson", Seq(), "POST", """""", _prefix + """person""")
)
                      

// @LINE:13
def test(task:String, subject:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.test(task, subject), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "test", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """test""")
)
                      

// @LINE:11
def getPersons(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getPersons(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getPersons", Seq(), "GET", """""", _prefix + """persons""")
)
                      

// @LINE:15
def file(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.file(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "file", Seq(), "GET", """""", _prefix + """file""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

}
                          
}
        
    