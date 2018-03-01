package com.github.sguzman.scala.js.data.typesafe.verify

case class EarningsNav(
                      banking: Banking,
                      instantPay: InstantPay,
                      paymentStatements: PaymentStatements,
                      taxes: Taxes,
                      primarySideNav: Option[Int]
                      )
