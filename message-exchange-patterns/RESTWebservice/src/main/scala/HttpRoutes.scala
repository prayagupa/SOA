

import java.util.concurrent.ConcurrentLinkedDeque

import Models.PackageShipment
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
    path("shipment") {
      post {
        entity(as[PackageShipment]) {
          shipment => complete {
            shipments.add(shipment)
            s"got package with package id ${shipment.name}"
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
