name := """platform-alerts"""

organization := "com.stys"

version := "1.1.2"

scalaVersion := "2.11.2"

crossScalaVersions := Seq("2.10.4", "2.11.2")

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-email" % "1.3.3"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)