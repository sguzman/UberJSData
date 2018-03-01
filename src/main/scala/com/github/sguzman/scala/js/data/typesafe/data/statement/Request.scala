package com.github.sguzman.scala.js.data.typesafe.data.statement

case class Request(
                  uri: URI,
                  method: String,
                  headers: Map[String,String]
                  )
