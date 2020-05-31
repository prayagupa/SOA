
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

/**
  * Created by prayagupd
  * on 12/27/16.
  */

class Webservice(implicit val system:ActorSystem,
                 implicit  val materializer:ActorMaterializer) extends HttpRoutes {
  def startServer(address:String, port:Int) = {
    Http().bindAndHandle(route, address, port)
  }
}

object Webservice {

  def main(args: Array[String]) {

    implicit val actorSystem = ActorSystem("rest-server")
    implicit val materializer = ActorMaterializer()

    new Webservice().startServer("localhost", 9191)
  }
}
