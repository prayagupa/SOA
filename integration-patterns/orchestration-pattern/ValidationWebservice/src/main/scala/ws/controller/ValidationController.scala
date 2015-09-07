package ws.controller

import com.google.inject.{Inject, Singleton}
import com.twitter.finatra.http.Controller
import ws.domain.Transaction
import ws.service.ValidationService

/**
 * Created by prayagupd
 * on 8/10/15.
 */

@Singleton
class ValidationController @Inject()(validationService : ValidationService) extends Controller {
  post("/validate") { customer : Transaction =>
    info(s"validatiing ${customer.name}")
    validationService.validate(customer)
  }
}
