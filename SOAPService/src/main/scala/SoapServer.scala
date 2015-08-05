/**
 * Created by prayagupd on 8/5/15.
 */

import javax.jws.WebService
import javax.jws.soap.SOAPBinding
import javax.jws.soap.SOAPBinding.Style
import javax.xml.ws.Endpoint

//SOAPBinding Style RPC for method test conflicts with global SOAPBinding Style DOCUMENT

//http://www.mkyong.com/webservices/jax-ws/jax-ws-hello-world-example-document-style/

@WebService(targetNamespace="org.scalabound.minecraft",
            name="org.scalabound.minecraft",
            portName="minecraft",
            serviceName="WSMinecraft")
private class SoapServer {

  @SOAPBinding(style = Style.RPC)
  def minecraft(value : String) = "Welcome " + value

}

object SoapServer {                                          // defined Companion Object for our class

  def main(args: Array[String]) {                                   // main method to make this a runnable application
  val endpoint = Endpoint.publish("http://localhost:8080/wsminecraft", new SoapServer())
    System.out.println("Waiting for requests...")
  }
}

//@WebService(targetNamespace="org.scalabound.test", name="org.scalabound.test", portName="test", serviceName="WSTest")
//private class MinimalSoapServer {
//
//  @SOAPBinding(style = Style.DOCUMENT)
//  def test(value : String) = "Hi " + value
//
//}
//object MinimalSoapServer {                                          // defined Companion Object for our class
//
//  def main(args: Array[String]) {                                   // main method to make this a runnable application
//  val endpoint = Endpoint.publish("http://localhost:8080/wstest", new MinimalSoapServer())
//    System.out.println("Waiting for requests...")
//  }
//}
