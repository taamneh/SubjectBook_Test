import play.PlayJava

name := """first_play"""

version := "1.0-SNAPSHOT"

val appName         = "first_play"

val appVersion      = "1.0-SNAPSHOT"


lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  javaCore,  // The core Java API
  "org.sorm-framework" % "sorm" % "0.3.8",
  "com.h2database" % "h2" % "1.3.168",
  "org.webjars" % "webjars-play" % "2.1.0",
  "org.webjars" % "bootstrap" % "2.3.1",
  "org.apache.poi"  % "poi" % "3.9",
  "org.apache.xmlbeans" % "xmlbeans"  % "2.4.0",
  "org.apache.poi"  % "poi-ooxml"  % "3.9",
  "org.apache.poi"  % "poi-ooxml"  % "3.5-beta5"
)

