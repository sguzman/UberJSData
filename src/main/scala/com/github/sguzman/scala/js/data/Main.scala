package com.github.sguzman.scala.js.data

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.Future
import scala.io.StdIn
import scala.util.{Failure, Success}

object Main {
  var js = "{}"

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    Future {
      println("Downloading")
      Items.js
    }.onComplete {t =>
      js = t match {
        case Success(v) => v
        case Failure(e) => throw e
      }
      println("Done")
    }

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return

    Http().bindAndHandle(path("js") {
        get {
          complete(HttpEntity(ContentTypes.`application/json`, js))
        }
      }, "localhost",
      util.Try(System.getenv("PORT").toInt) match {
        case Success(v) => v
        case Failure(_) => 8080
      })
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}