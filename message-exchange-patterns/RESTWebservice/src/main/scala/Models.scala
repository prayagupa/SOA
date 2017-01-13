import spray.json.DefaultJsonProtocol

/**
  * Created by prayagupd
  * on 12/27/16.
  */

object Models {
  case class PackageShipment(name: String)
  case class Acknowledge(status: String)

  object ServiceJsonProtoocol extends DefaultJsonProtocol {
    implicit val shipmentProtocol = jsonFormat1(PackageShipment)
    implicit val ack = jsonFormat1(Acknowledge)
  }
}
