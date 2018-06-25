import sbt._

object Dependencies {
  lazy val scalaTest = Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    // for law testing cats based typeclasses
    "org.typelevel" %% "cats-laws" % "1.0.1" % Test,
    "org.typelevel" %% "cats-testkit" % "1.0.1"% Test,
    "com.github.alexarchambault" %% "scalacheck-shapeless_1.13" % "1.1.6" % Test
  )

  val circeVersion = "0.9.1"
  lazy val core = Seq(
    "org.typelevel" %% "cats-core" % "1.0.1",
    "org.typelevel" %% "mouse" % "0.16",
    "com.typesafe" % "config" % "1.3.1",
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,
    "io.circe" %% "circe-config" % "0.4.1"
  )
}
