name := "decoupled-invocation"

version := "1.0"

scalaVersion := "2.10.4"

val sparkVersion = "1.2.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming-kafka" % sparkVersion
)

mainClass in (Compile, run) := Some("EventsConsumerApp")