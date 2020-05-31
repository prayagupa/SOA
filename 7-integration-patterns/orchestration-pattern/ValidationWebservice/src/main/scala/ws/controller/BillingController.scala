package ws.controller

import com.google.inject.Singleton
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import ws.domain.BillingRequest

/**
 * Created by prayagupd
 * on 8/10/15.
 * https://github.com/twitter/finatra-examples/tree/master/hello-world
 */

@Singleton
class BillingController extends Controller {
  get("/bill") { request: Request =>
    info("/bill")
    Map( "name" -> request.params.getOrElse("name", "pseudononymous"))
    //render.json(Map( "name" -> request.params.getOrElse("name", "pseudononymous")))
  }

  post("/bill") { billingRequest: BillingRequest =>
    val str = s"Hello ${billingRequest.name} with id ${billingRequest.id}"
    info(str)
    Map("name" -> billingRequest.name, "id" -> billingRequest.id)
  }
}

