name := "scaladesignpatterns"

version := "0.1"

scalaVersion := "2.12.2"

libraryDependencies += "com.twitter" %% "finagle-http" % "18.3.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"


libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.10"

libraryDependencies += "ch.qos.logback" % "logback-core" % "1.2.3"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.11.0"
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.8.0-beta2"