name := "moip-challenge"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

logBuffered in Test := false

mainClass := Some("br.com.moip.challenge.Main")

assemblyJarName in assembly := "moip-challenge.jar"