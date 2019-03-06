name := "scalapostgres"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "3.3.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0",
  "org.slf4j" % "slf4j-nop" % "1.7.10",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4"
)