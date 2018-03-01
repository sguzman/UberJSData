package com.github.sguzman.scala.js.data.typesafe.verify

case class ProfileNav(
                     documents: Documents,
                     profile: ProfileProfile,
                     vehicles: Vehicles,
                     primarySideNav: Option[Int],
                     url: Option[String]
                     )
