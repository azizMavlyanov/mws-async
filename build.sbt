name := "mws-async"

version := "0.1"

scalaVersion := "2.13.5"

val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.4"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

unmanagedJars in Compile += file("lib/*.jar")



