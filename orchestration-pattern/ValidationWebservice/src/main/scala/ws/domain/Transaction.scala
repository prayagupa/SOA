package ws.domain

import com.twitter.finatra.request.RouteParam

/**
 * Created by prayagupd
 * on 8/10/15.
 */

case class Transaction (@RouteParam name : String, @RouteParam amount : Double)
