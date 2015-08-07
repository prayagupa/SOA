/**
 * Created by prayagupd on 8/5/15.
 */

import javax.jws.WebService
import javax.jws.soap.SOAPBinding
import javax.jws.soap.SOAPBinding.Style
import javax.xml.ws.Endpoint

//SOAPBinding Style RPC for method test conflicts with global SOAPBinding Style DOCUMENT

//http://www.mkyong.com/webservices/jax-ws/jax-ws-hello-world-example-document-style/

//@WebService(targetNamespace="org.scalabound.minecraft",
//            name="org.scalabound.minecraft",
//            portName="minecraft",
//            serviceName="WSMinecraft")
//private class SoapServer {
//
//  @SOAPBinding(style = Style.DOCUMENT)
//  def minecraft() = "Welcome to MUM"
//
//}
//
//object SoapServer {                                          // defined Companion Object for our class
//
//  def main(args: Array[String]) {                                   // main method to make this a runnable application
//  val endpoint = Endpoint.publish("http://10.10.14.201:8082/wsminecraft", new SoapServer())
//    System.out.println("Waiting for requests...")
//  }
//}

@WebService(targetNamespace="org.unified.apple",
  name="org.unified.apple",
  portName="apple",
  serviceName="WSApple")
private class SoapServer {

  @SOAPBinding(style = Style.DOCUMENT)
  def apple() : String = {
    println("Invoked from soap client")
    "You got an unified apple."
  }

}
object SoapServer {                                          // defined Companion Object for our class

  def main(args: Array[String]) {                                   // main method to make this a runnable application
    System.out.println("Starting SoapServer...")
    //http://localhost:8082/wsApple/apple?value=1
    val endpoint = Endpoint.publish("http://10.10.14.201:8082/wsApple", new SoapServer())
    //val endpoint = Endpoint.publish("http://10.10.14.201:8082/wstest", new SoapServer())
    System.out.println("Waiting for requests...")
  }
}
