package webservice.controller

import akka.actor.{Actor, Props}
import spray.routing._
import webservice.service.{ElevationActor, TimezoneActor}

class ElevationHttpActor extends Actor with ElevationHttpController {

  def actorRefFactory = context

  def receive = runRoute(akkaHttpApiRoutes)
}

trait ElevationHttpController extends HttpService {
  val akkaHttpApiRoutes =
    pathPrefix("api") {
      path("elevation" / DoubleNumber / DoubleNumber) { (long, lat) =>
        requestContext =>
          val elevationService = actorRefFactory.actorOf(Props(new ElevationActor(requestContext)))
          elevationService ! ElevationActor.ElevationObject(long, lat)
      } ~
      path("timezone" / DoubleNumber / DoubleNumber / Segment) { (long, lat, timestamp) =>
        requestContext =>  
          val timezoneService = actorRefFactory.actorOf(Props(new TimezoneActor(requestContext)))
          timezoneService ! TimezoneActor.TimezoneObject(long, lat, timestamp)
      }
    }
}
