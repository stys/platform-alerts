name := """platform-alerts"""

version := "1.1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.2"

crossScalaVersions := Seq("2.10.4", "2.11.2")

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-email" % "1.3.3"
)
