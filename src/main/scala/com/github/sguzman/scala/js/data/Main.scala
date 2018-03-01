package com.github.sguzman.scala.js.data

import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.server.blaze.BlazeBuilder

import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {
    val builder = BlazeBuilder[IO]
      .bindHttp(8080, "localhost")
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