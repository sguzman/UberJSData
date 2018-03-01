package com.github.sguzman.scala.js.data.typesafe.login.email.output

case class Question(
                   identifiedUserInfo: IdentifiedUserInfo,
                   signinToken: String,
                   thirdPartyInfo: ThirdPartyInfo,
                   tripChallenges: Array[String],
                   `type`: String
                   )
