import com.twitter.finagle.httpx.Method
import com.twitter.finagle.httpx.path._
import com.twitter.finagle.{Httpx, Service}
import com.twitter.util.{Future, Await}

import io.finch._
import service.PaymentService

/**
 * // Invoke the correct underlying service based on the incoming url.
 * https://gist.github.com/vastdevblog/2022320
 */

object PaymentServer extends App {

  val endpoint = new Endpoint[HttpRequest, HttpResponse] {
    def route = {
      // routes requests like '/payment/2000?title=Mr.'
      case Method.Get -> Root / "payment" / amount => new PaymentService(amount.toDouble)
      case Method.Post -> Root / "payment" => new PaymentService(amount = 0)
    }
  }

  val server = Httpx.serve(":9090", endpoint)
  Await.ready(server)

}