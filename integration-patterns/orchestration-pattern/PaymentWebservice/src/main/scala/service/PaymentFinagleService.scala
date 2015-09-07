package service

import java.util.UUID

/**
 * Created by prayagupd
 * on 8/10/15.
 */

// A very simple service that returns a model object.
class PaymentFinagleService(val waitFor: Option[Int] = None) {

  def apply(amount: Double) = {
    // If a time to wait is supplied then wait. This
    // is used to timeout the service.
    waitFor.foreach(t => this.synchronized { wait(t) })
    // Return the model object.
    new Payment(UUID.randomUUID().toString, amount, "COMPLETED")
  }
}

case class Payment(id: String, amount : Double, status : String)
