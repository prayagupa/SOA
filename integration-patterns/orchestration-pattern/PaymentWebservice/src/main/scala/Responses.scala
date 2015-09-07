import com.fasterxml.jackson.databind.ObjectMapper
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._
import org.jboss.netty.handler.codec.http.HttpResponseStatus._
import org.jboss.netty.handler.codec.http.HttpVersion._
import org.jboss.netty.handler.codec.http.{HttpRequest, DefaultHttpResponse, HttpResponse, HttpResponseStatus}
import org.jboss.netty.util.CharsetUtil._

/**
 * Created by prayagupd
 * on 8/10/15.
 */

// Objects to produce some standard http responses.
object Responses {

  // Used to convert objects into json
  val mapper = new ObjectMapper

  // Create an HttpResponse from a status and some content.
  private def respond(status: HttpResponseStatus, content: ChannelBuffer): HttpResponse = {
    val response = new DefaultHttpResponse(HTTP_1_1, status)
    //    response.setHeader("Content-Type", "application/json")
    //    response.setHeader("Cache-Control", "no-cache")
    response.setContent(content)
    response
  }

  object OK {
    def apply(req: HttpRequest, service: (HttpRequest) => Object): HttpResponse =
      respond(HttpResponseStatus.OK,
        copiedBuffer(mapper.writeValueAsBytes(service(req))))
  }

  object NotFound {
    def apply(): HttpResponse  =
      respond(NOT_FOUND,
        copiedBuffer("{\"status\":\"NOT_FOUND\"}", UTF_8))
  }

  object InternalServerError {
    def apply(message: String): HttpResponse =
      respond(INTERNAL_SERVER_ERROR,
        copiedBuffer("{\"status\":\"INTERNAL_SERVER_ERROR\", " +
          "\"message\":\"" + message + "\"}", UTF_8))
  }
}
