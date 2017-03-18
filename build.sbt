name := """platform-alerts"""

organization := "com.stys"

version := "1.2.2"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-email" % "1.3.3"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)