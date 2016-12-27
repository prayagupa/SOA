name := "RESTWebService"

version := "1.0"

scalaVersion := "2.11.5"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" %% "akka-http-experimental" % "1.0",
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "1.0",
    "com.typesafe.akka" %%"akka-http-testkit-experimental" % "1.0",
    "org.scalatest" %% "scalatest" % "2.2.5" % "test"
  )
}
