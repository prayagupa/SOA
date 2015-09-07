package service

import com.twitter.finagle.Service
import com.twitter.util.Future
import io.finch._
import io.finch.json.Json
import io.finch.response._

/**
 * Created by prayagupd
 * on 8/10/15
 */

class PaymentService(amount : Double) extends Service[HttpRequest, HttpResponse] {

//  def payment(amount: Double) = new Service[HttpRequest, HttpResponse]  {
//    def apply(req: HttpRequest) = for {
//      title <- OptionalParam("amount")(req)
//    } yield Ok(Json.obj("amount" -> s"${title.getOrElse("")} $amount!"))
//  }

  def apply(request: HttpRequest): Future[HttpResponse] = {
    Ok(
      Json.obj(
        "status" -> "SUCCESS"
      )
    ).toFuture
  }
}
