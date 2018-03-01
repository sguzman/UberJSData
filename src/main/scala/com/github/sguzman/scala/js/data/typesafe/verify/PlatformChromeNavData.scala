package com.github.sguzman.scala.js.data.typesafe.verify

case class PlatformChromeNavData(
                                avatars: Avatars,
                                languages: Array[Languages],
                                locale: Locale,
                                nav: Nav,
                                rating: Rating,
                                translations: Map[String,String],
                                user: User
                                )
