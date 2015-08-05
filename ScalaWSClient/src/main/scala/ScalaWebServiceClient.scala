/**
 * Created by prayagupd on 8/4/15.
 */
object ScalaWebServiceClient {

    def main(args : Array[String]) = {
        val lat = args(0).toInt
        val long_ = args(1).toInt

        //consumeCdyne()
        consumeLocalWS(lat, long_)
    }

    def consumeLocalWS(lat : Int, long_ : Int)  : Unit = {
        println(s"calling http://localhost:8080/api/elevation/${lat}/${long_}")
        val url = s"http://localhost:8080/api/elevation/${lat}/${long_}"
        val result = scala.io.Source.fromURL(url).mkString
        println(result)
    }


    def consumeCdyne() : Unit = {
        val restUrl = "http://wsf.cdyne.com/WeatherWS/Weather.asmx/GetCityForecastByZIP?ZIP=52557"
        val response = scala.io.Source.fromURL(restUrl).mkString
        println(response)
    }

}
