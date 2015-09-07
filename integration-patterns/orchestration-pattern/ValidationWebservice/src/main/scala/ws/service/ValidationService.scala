package ws.service

import com.google.inject.Singleton
import ws.domain.Transaction

/**
 * Created by prayagupd
 * on 8/10/15.
 */

@Singleton
class ValidationService {
  def validate (customer : Transaction) : Map[String, Any] = {
    Map("status" -> "SUCCESS",
        "name"   -> customer.name,
        "amount" -> customer.amount)
  }
}
