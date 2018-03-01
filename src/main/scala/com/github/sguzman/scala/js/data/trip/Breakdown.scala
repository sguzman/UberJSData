package com.github.sguzman.scala.js.data.trip

case class Breakdown(
                    category: String,
                    items: Array[Item],
                    total: String,
                    description: String
                    )
