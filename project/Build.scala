import sbt._
import Keys._
import com.github.retronym.SbtOneJar

object AtmosWiring extends Build {
  val Organization = "org.h3nk3"
  val Version      = "1.0-SNAPSHOT"
  val ScalaVersion = "2.10.0"

  lazy val wiring = Project(
    id = "WiredAtmos",
    base = file("."),
    settings = defaultSettings ++ SbtOneJar.oneJarSettings ++ Seq(
      libraryDependencies ++= Dependencies.tracedAkka,
      scalacOptions += "-language:postfixOps",
      mainClass in (Compile, run) := Some("org.h3nk3.WiredAtmosMain"),
      Keys.fork in run := true
    )
  )

  lazy val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := Organization,
    version      := Version,
    scalaVersion := ScalaVersion,
    crossPaths   := false
  )

  lazy val defaultSettings = buildSettings ++ Seq(
    exportJars := true,
    resolvers += "Atmos Repo" at "http://typesafe.artifactoryonline.com/typesafe/atmos-releases",
    resolvers += "JBoss Repo" at "https://repository.jboss.org/nexus/content/repositories/thirdparty-uploads",
    credentials += Credentials(Path.userHome / "atmos-read.credentials")
  )  
}

object Dependencies {

  val akkaActor  = "com.typesafe.akka"  %% "akka-actor"      % "2.1.1"
  val atmosTrace = "com.typesafe.atmos" % "trace-akka-2.1.1" % "1.2.0-M3"
  val logback    = "ch.qos.logback"     % "logback-classic"  % "1.0.7"
  val sigar      = "org.hyperic"        % "sigar"            % "1.6.5.132"

  val tracedAkka = Seq(akkaActor, atmosTrace, logback, sigar)
}
