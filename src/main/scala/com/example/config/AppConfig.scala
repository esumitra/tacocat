/**
  * Configuration utils
  */

package com.example.config

import com.typesafe.config.{ConfigFactory, Config}
import io.circe.generic.auto._
import io.circe.config.syntax._

// config classes
case class CookieSettings(domain: String, path: String, ttl: Int)
case class SignatureSettings(pkfile: String, keyPairId: String)
case class AppSettings(cookie: CookieSettings, signature: SignatureSettings)

object ConfigUtils {
  lazy val config = initConfig(ConfigFactory.load())

  def initConfig(conf: Config): AppSettings = {
    conf.as[AppSettings]("com.example") match {
      case Right(c) => c
      case _ => throw new Exception("invalid configuration")
    }
  }
}
