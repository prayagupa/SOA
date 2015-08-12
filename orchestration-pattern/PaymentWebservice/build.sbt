name := "PaymentWebservice"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

//libraryDependencies += "com.twitter" %% "finagle-httpx" % "6.27.0"
libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala" % "2.0.2"
libraryDependencies += "com.github.finagle" %% "finch-json" % "0.4.0"

mainClass in Compile := Some("PaymentServer")