import java.net.URL
import javax.xml.namespace.QName

/**
 * Created by prayagupd on 8/5/15.
 * http://wiki.summercode.com/a_trivial_soap_client_in_scala
 */

object SoapTest {
  def doTest1 {
    //http://10.10.13.29:8000/wsdl?wsdl
    val host = "https://apitest.authorize.net/soap/v1/Service.asmx"
    val req  = <IsAlive xmlns="https://api.authorize.net/soap/v1/"/>
    val cli = new SoapClient
    println("##### request:\n" + cli.wrap(req))
    val resp = cli.sendMessage(host, req)
    if (resp.isDefined) {
      println("##### response:\n" + resp.get.toString)
    }
  }

  def doTest2 {
    val host = "http://ws.cdyne.com/WeatherWS/Weather.asmx"
    val req  = <GetCityForecastByZIP xmlns="http://ws.cdyne.com/WeatherWS/">
      <ZIP>77058</ZIP>
    </GetCityForecastByZIP>
    val cli = new SoapClient
    println("##### request:\n" + cli.wrap(req))
    val resp = cli.sendMessage(host, req)
    if (resp.isDefined) {
      println("##### response:\n")
      (resp.get  \\ "Forecast").foreach(elem => {
        println("#########\n" + elem.toString)
      })
    }
  }

  def doTest3 {
    val host = "http://10.10.13.19:8000/wsdl?wsdl"
    val req  = <sayHello xmlns="http://10.10.13.19:8000/wsdl">abc
    </sayHello>
    val cli = new SoapClient
    println("##### request:\n" + cli.wrap(req))
    val resp = cli.sendMessage(host, req)
    if (resp.isDefined) {
      println("##### response:\n")
      (resp.get  \\ "Forecast").foreach(elem => {
        println("#########\n" + elem.toString)
      })
    }
  }

  def doTest4 {
//    val host = new URL("http://10.10.13.19:8000/wsdl?wsdl")
//    val qname = new QName("http://10.10.13.19:8000/wsdl?wsdl", "sayHello")
    val hostU = "http://10.10.14.201:8082/wsApple"
    val req  = <apple xmlns="org.unified.apple"/>
    val cli = new SoapClient
    println("##### request:\n" + cli.wrap(req))
    val resp = cli.sendMessage(hostU, req)
    if (resp.isDefined) {
      println("##### response:\n")
      println("" + resp.toString)
      (resp.get  \\ "return").foreach(elem => {
        println("#########\n" + elem.toString)
      })
    }

  }

  def main(args: Array[String]) {
    //doTest1
//    doTest3
    doTest4
  }
}
