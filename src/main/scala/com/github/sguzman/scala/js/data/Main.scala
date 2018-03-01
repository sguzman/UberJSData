package com.github.sguzman.scala.js.data

import lol.http._

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object Main {
  def main(args: Array[String]): Unit = {
    val port = util.Try(System.getenv("PORT").toInt) match {
      case Success(v) => v
      case Failure(e) => 8888
    }

    Server.listen(port)(handle)
  }

  def handle(e: Request) = e.path match {
    case "/hello" => Ok("Hello :-)")
    case _ => NotFound("404 :-(")
  }
}