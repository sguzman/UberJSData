package com.github.sguzman.scala.js.data

import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.server.blaze.BlazeBuilder

import scala.io.StdIn
import scala.util.{Failure, Success}

object Main {
  def main(args: Array[String]): Unit = {
    val port = scala.util.Try(System.getenv("PORT").toInt) match {
      case Success(v) => v
      case Failure(e) => 8080
    }

    val builder = BlazeBuilder[IO]
      .bindHttp(port, "localhost")
      .mountService(
        HttpService[IO] {
          case GET -> Root =>
            Ok("Hello")
        }, "/hello")
      .start

    val server = builder.unsafeRunSync()
    StdIn.readLine
  }
}