package com.github.sguzman.scala.js.data

import java.net.SocketTimeoutException
import java.util.UUID

import com.github.sguzman.scala.js.data.typesafe.data.all_data.AllDataStatement
import com.github.sguzman.scala.js.data.typesafe.data.statement.Statement
import com.github.sguzman.scala.js.data.typesafe.data.trip.Trip
import com.github.sguzman.scala.js.data.typesafe.verify.PlatformChromeNavData
import io.circe.generic.auto._
import io.circe.parser.decode

import scala.util.{Failure, Success}
import scalaj.http.{Http, HttpResponse}

object Main {
  val cookie: String = System.getenv("COOKIE")

  implicit final class StrWrap(str: String) {
    def req = Http(str).header("Cookie", cookie)
    def exe: HttpResponse[String] = util.Try(req.asString) match {
      case Success(v) => v
      case Failure(e) =>
        println(e)
        e match {
          case _: SocketTimeoutException => exe
        }
    }
    def body = exe.body
    def verify(): Unit = util.Try(decode[PlatformChromeNavData](body).right.get) match {
      case Success(_) =>
      case Failure(e) => throw e
    }

    def statements = {
      "https://partners.uber.com/p3/platform_chrome_nav_data".verify()
      decode[List[AllDataStatement]](str.body)
        .right
        .get
    }
  }

  implicit final class UUIDWrap(uuid: UUID) {
    def statement = decode[Statement](s"https://partners.uber.com/p3/money/statements/view/${uuid.toString}".body) match {
      case Right(v) => v
      case Left(e) => throw e
    }

    def trip = decode[Trip](s"https://partners.uber.com/p3/money/trips/trip_data/${uuid.toString}") match {
      case Right(v) => v
      case Left(e) => throw e
    }
  }

  def main(args: Array[String]): Unit = {
    "https://partners.uber.com/p3/money/statements/all_data/"
      .statements
      .par
      .map(_.uuid)
      .map(_.statement)
      .flatMap(_.body.driver.trip_earnings.trips.keys)
      .map(_.trip) foreach println
  }

}