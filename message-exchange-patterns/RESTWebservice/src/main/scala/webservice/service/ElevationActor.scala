package webservice.service

import akka.actor.Actor
import akka.event.Logging
import spray.json.DefaultJsonProtocol._
import spray.routing.RequestContext
import spray.json._

object ElevationActor {
  case class ElevationObject(long: Double, lat: Double)
}

class ElevationActor(requestContext: RequestContext) extends Actor {

  import ElevationActor._

  implicit val system = context.system
  val log = Logging(system, getClass)

  def receive = {
    case ElevationObject(long,lat) =>
      process(long,lat)
      context.stop(self)
  }

  def process(long: Double, lat: Double) = {

    log.info("Requesting elevation long: {}, lat: {}", long, lat)
    val result = long + lat
    //val response = JResponse(long, lat, result)
    //val r = response.toJson
    requestContext.complete(result+"")
//    import ElevationJsonProtocol._
//    import SprayJsonSupport._
//    val pipeline = sendReceive ~> unmarshal[GoogleElevationApiResult[Elevation]]
//
//    val responseFuture = pipeline{
//      Get(s"http://maps.googleapis.com/maps/api/elevation/json?locations=$long,$lat&sensor=false")
//    }
//    responseFuture onComplete {
//      case Success(GoogleElevationApiResult(_, Elevation(_, elevation) :: _)) =>
//        log.info("The elevation is: {} m", elevation)
//        requestContext.complete(elevation.toString)
//
//      case Failure(error) =>
//        requestContext.complete(error)
//    }
  }
}
