package webservice.controller

import akka.actor.{Actor, Props}
import spray.routing._
import webservice.service.{ElevationService, TimezoneService}

class MinecraftControllerActor extends Actor with MinecraftController {
  
  def actorRefFactory = context

  def receive = runRoute(sprayApiDemoRoute)
}

trait MinecraftController extends HttpService {
  val sprayApiDemoRoute =
    pathPrefix("api") {
      path("elevation" / DoubleNumber / DoubleNumber) { (long, lat) =>
        requestContext =>
          val elevationService = actorRefFactory.actorOf(Props(new ElevationService(requestContext)))
          elevationService ! ElevationService.Process(long, lat)
      } ~
      path("timezone" / DoubleNumber / DoubleNumber / Segment) { (long, lat, timestamp) =>
        requestContext =>  
          val timezoneService = actorRefFactory.actorOf(Props(new TimezoneService(requestContext)))
          timezoneService ! TimezoneService.Process(long, lat, timestamp)
      }
    }
}
