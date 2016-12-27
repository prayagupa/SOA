package webservice.service

import akka.actor.Actor
import akka.event.Logging
import spray.routing.RequestContext

object TimezoneActor {
  case class TimezoneObject(long: Double, lat: Double, timestamp: String)
}

class TimezoneActor(requestContext: RequestContext) extends Actor {

  import TimezoneActor._

  implicit val system = context.system
  val log = Logging(system, getClass)

  def receive = {
    case TimezoneObject(long,lat,timestamp) =>
      process(long,lat,timestamp)
      context.stop(self)
  }

  def process(long: Double, lat: Double, timestamp: String) = { 
      requestContext.complete((lat+long+timestamp).toString)
//    log.info("Requesting timezone long: {}, lat: {}, timestamp: {}", long, lat, timestamp)
//    val pipeline = sendReceive ~> unmarshal[GoogleTimezoneApiResult[Timezone]]
//
//    val responseFuture = pipeline {
//      Get(s"https://maps.googleapis.com/maps/api/timezone/json?location=$long,$lat&timestamp=$timestamp&sensor=false")
//    }
//    responseFuture onComplete {
//      case Success(GoogleTimezoneApiResult(_, _, timeZoneName)) =>
//        log.info("The timezone is: {} m", timeZoneName)
//        requestContext.complete(timeZoneName)
//
//      case Failure(error) =>
//        requestContext.complete(error)
//    }
  }
}
