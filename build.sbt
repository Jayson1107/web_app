name := "App"

version := "1.0"

lazy val `app` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

resolvers ++= Seq(
  "local maven" at ("file://%s/.m2/repository" format Path.userHome.absolutePath),
  "typesafe release" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq( 
  jdbc ,
  anorm ,
  cache ,
  ws,
  "com.typesafe.play" %% "play-slick" % "0.8.0",
  "org.scalaz" %% "scalaz-core" % "7.0.6",
  "com.lambdaworks" % "scrypt" % "1.4.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "redis.clients" % "jedis" % "2.4.2",
  "commons-httpclient" % "commons-httpclient" % "3.1",
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "com.typesafe.slick" %% "slick-codegen" % "2.1.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "com.typesafe" % "config" % "1.2.1",
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "mysql" % "mysql-connector-java" % "5.1.31",
  "joda-time" % "joda-time" % "2.4",
  "org.joda" % "joda-convert" % "1.6",
  "com.github.nscala-time" %% "nscala-time" % "1.2.0",
  "commons-codec" % "commons-codec" % "1.3",
  "com.ning" % "async-http-client" % "1.8.12",
  "org.json4s" %% "json4s-jackson" % "3.2.10"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  
