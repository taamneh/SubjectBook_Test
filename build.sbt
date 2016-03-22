import play.PlayImport._
import play.PlayJava

name := """first_play"""

version := "1.0-SNAPSHOT"

val appName = "first_play"

val appVersion = "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

javaOptions ++= Seq("-Xms1024M", "-Xmx2048M")

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  javaCore,  // The core Java API
  "org.nuiton.thirdparty" % "REngine" % "1.7-3",
  "org.nuiton.thirdparty" % "Rserve" % "1.7-3",
  "org.sorm-framework" % "sorm" % "0.3.8",
  "com.monitorjbl"  % "xlsx-streamer" % "0.2.6",
  //"com.google.http-client" %  "google-http-client-jackson2"  % "1.18.0-rc",
  "com.google.http-client" %  "google-http-client-jackson2"  % "1.15.0-rc",
  //"com.google.apis" % "google-api-services-oauth2" % "v2-rev78-1.18.0-rc",
  "com.google.apis" % "google-api-services-oauth2" % "v2-rev41-1.15.0-rc",
  "com.google.apis" % "google-api-services-drive" % "v2-rev145-1.18.0-rc",
  "com.google.api-client" % "google-api-client" % "1.18.0-rc",
  "com.h2database" % "h2" % "1.3.168",
  "org.webjars" % "webjars-play" % "2.1.0",
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.webjars" % "bootstrap" % "2.3.1",
  "org.apache.poi"  % "poi" % "3.9",
  "org.apache.xmlbeans" % "xmlbeans"  % "2.4.0" exclude("javax.jms", "jms") exclude("com.sun.jdmk", "jmxtools") exclude("com.sun.jmx", "jmxri"),
  "org.apache.poi"  % "poi-ooxml"  % "3.9",
  "org.slf4j" % "slf4j-api"       % "1.7.7" exclude("javax.jms", "jms") exclude("com.sun.jdmk", "jmxtools") exclude("com.sun.jmx", "jmxri"),
  "org.slf4j" % "jcl-over-slf4j"  % "1.7.7" exclude("javax.jms", "jms") exclude("com.sun.jdmk", "jmxtools") exclude("com.sun.jmx", "jmxri"),
  "net.sourceforge.jexcelapi" % "jxl" % "2.6.3" exclude("javax.jms", "jms") exclude("com.sun.jdmk", "jmxtools") exclude("com.sun.jmx", "jmxri"),
  "com.googlecode.json-simple" % "json-simple" % "1.1.1",
  "com.google.gdata" % "core" % "1.47.1"
)

//libraryDependencies ~= { _.map(_.exclude("ch.qos.logback", "logback-classic")) }
