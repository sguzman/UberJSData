package com.github.sguzman.scala.js.data.typesafe.login.sms.output

import java.util.UUID

case class SMSOutput(
                    nextURL: String,
                    stage: SMSOutputStage,
                    userUUID: UUID,
                    isSignup: Option[Boolean]
                    )
