name := "SOAPService"

version := "1.0"

scalaVersion := "2.11.7"

scalaSource in Compile <<= baseDirectory(_ / "src")

scalaSource in Test <<= baseDirectory(_ / "test")

libraryDependencies += "javax" % "javaee-api" % "6.0" % "provided"

libraryDependencies += "log4j" % "log4j" % "1.2.16" % "provided"

libraryDependencies += "junit" % "junit" % "4.8.2" % "test"

mainClass in (Compile, packageBin) := Some("SoapServer")