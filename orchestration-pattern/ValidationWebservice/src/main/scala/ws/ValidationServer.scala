package ws

import com.twitter.finagle.http.{Response, Request}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.logging.filter.{TraceIdMDCFilter, LoggingMDCFilter}
import com.twitter.finatra.logging.modules.LogbackModule
import ws.controller.{ValidationController, BillingController}

/**
 * Created by prayagupd
 * on 8/10/15.
 */

object ValidationServerMain extends ValidationServer

class ValidationServer extends HttpServer {
  override def modules = Seq(LogbackModule)

  override def configureHttp(router: HttpRouter) {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[BillingController]
      .add[ValidationController]
  }
}
