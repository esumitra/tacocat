/**
  * main entry point for application
  */

package com.example
import com.example.config.ConfigUtils

object MainApp {

  def hello(name: String): String = s"Hello ${name}"

  def main(args: Array[String]): Unit = {
    val ttl = ConfigUtils.config.cookie.ttl
    println(s"running application version with ttl: ${ttl}")
    val message = args.length match {
      case 0 => hello("Anonymous")
      case _ => hello(args(0))
    }
    println(message)
  }
}
