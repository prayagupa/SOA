

import java.util.concurrent.ConcurrentLinkedDeque

import Models.{Acknowledge, PackageShipment}
import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.collection.JavaConverters._

/**
  * Created by prayagupd
  * on 12/27/16.
  */

trait HttpRoutes {
  implicit val system:ActorSystem
  implicit val materializer:ActorMaterializer

  val shipments = new ConcurrentLinkedDeque[Models.PackageShipment]()
  shipments.add(new PackageShipment(name = "some existing package"))

  import Models.ServiceJsonProtoocol._

  val route =
    //TODO authenticate + authorization
    // http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0/scala/http/routing-dsl/directives/security-directives/index.html
    path("shipment") {
      post {
        entity(as[PackageShipment]) {
          shipment => complete {
            shipments.add(shipment)
            //TODO write to stream
            Acknowledge(status = "Ok")
          }
        }
      } ~
        get {
          complete {
            shipments.asScala
          }
        }
    }
}
