package com.github.sguzman.scala.js.data.trip

case class Pinpoint(
                 lat: Double,
                 lng: Double
                 ) {
  def latlng = new google.maps.LatLng(lat, lng)
}
