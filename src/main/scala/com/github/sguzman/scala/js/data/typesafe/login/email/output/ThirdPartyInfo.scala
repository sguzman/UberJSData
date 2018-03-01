package com.github.sguzman.scala.js.data.typesafe.login.email.output

case class ThirdPartyInfo(
                         email: String,
                         firstName: String,
                         identityTypes: Option[String],
                         lastName: String
                         )
