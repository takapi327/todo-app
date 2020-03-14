name := """TODO-app"""
organization := "com.tuyano.play"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.12"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
// libraryDependencies ++= Seq(
//   "mysql" % "mysql-connector-java" % "8.0.12",
//   "com.typesafe.play" %% "play-slick" % "5.0.0",
//   "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
// )
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.tuyano.play.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.tuyano.play.binders._"
