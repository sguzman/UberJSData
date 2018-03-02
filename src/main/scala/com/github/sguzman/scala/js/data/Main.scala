package com.github.sguzman.scala.js.data

import lol.http._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Main {
  var js = "..."

  def dl = {
    def download(idx: Int = 0): Unit = {
      println(s"Downloading $idx")
      js = Items.js
      println(s"Finished $idx")
      Thread.sleep(86400000)
      download(idx + 1)
    }

    download()
  }

  def server(port: Int) = Server.listen(port) {
    case GET at "/js" =>
      Ok(js)
    case _ =>
      NotFound
  }

  def start = server(util.Try(System.getenv("PORT").toInt) match {
    case Success(v) => v
    case Failure(_) => 8080
  })

  def main(args: Array[String]): Unit =
    List[() => Unit](dl _, start _)
      .par
      .foreach(t => t.apply)
}