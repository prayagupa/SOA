
[How two computers can communicate?](https://www.quora.com/What-is-the-difference-between-HTTP-protocol-and-TCP-protocol/answer/Daniel-Miller-7)
--------------------------------------------

Essentially, there are different protocols that let a computer talk at different distances and 
different layers of abstraction.

- **At the very bottom of the network stack is the physical layer.** This is where electrical signals 
or light pulses or radio waves actually transmit information from place to place.

You can transmit information directly this way, but you need a lot of power or a dedicated line, 
and without higher layers you won't be able to share bandwidth.

- **The next layer up is the link layer.**
This layer covers communication with devices that share a physical communications medium. 

Here, protocols like Ethernet, 802.11a/b/g/n, and Token Ring specify how to handle multiple 
concurrent accesses to the physical medium and how to direct traffic to one device instead of another.

- **The third layer is the network layer.** 
In the majority of cases, this is dominated by Internet Protocol (IP). 
This is where the magic of the Internet happens, and you get to talk to a computer halfway around 
the world, without needing to know where it is. 

[Comparing TCP/IP applications vs HyperTextTP applications](https://softwareengineering.stackexchange.com/a/214251/31060)

```
TCP is a protocol in the transport layer and HTTP is a protocol in the application layer.

HTTP is over TCP/IP

You want to add a application layer protocol over TCP (like HTTP) and then a content-type (like json, xml, html). 
Netty let you use HTTP and content-type as protobuff which is equivalent to json, xml, html.
```

[How web works?](http://www.garshol.priv.no/download/text/http-tut.html) / What happens when I hit a http endpoint/ follow a link?
----------------------------------------------------

**Step 1: Parsing the URL**
 - Most URLs have this basic form: `protocol://server/request-URI(Uniform Resource Identifier)`

[UniformResource I vs L vs N](https://stackoverflow.com/a/28865728/432903)

|                         |                        |                         |
|-------------------------|------------------------|-------------------------|
|`UniformResource I`      | Identifies             | eg. name                 |
| `UniformResource L`     | Identifies and Locates | eg. physical address, [https://hostname/api/resource]()    |
| `UniformResource I`/`Name` | `urn:isbn:0-486-27557-4` |                       |
| `UniformResource I`/`Locator` | `file://hostname/sharename/RomeoAndJuliet.pdf`   | 

**Step 2: Sending the request by browser with header infos** 
 - the browser transmits the following request to the server: 

```bash
"GET /request-URI HTTP/version"
```

[Step 3: CGI/ The server response](http://www.garshol.priv.no/download/text/http-tut.html)

```bash
HTTP/1.0 200 OK
Server: Netscape-Communications/1.1
Date: Tuesday, 25-Nov-97 01:22:04 GMT
Last-modified: Thursday, 20-Nov-97 10:44:53 GMT
Content-length: 6372
Content-type: text/html

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<HTML>
...followed by document content...
```

What is [CommonGatewayInterface](https://stackoverflow.com/a/5058873/432903)?

```
a standard protocol for web servers to execute programs that execute like CLI programs 
running on a server that generates web pages dynamically.
```

[HyperText Transfer Protocol -- HTTP/1.1 Draft](http://greenbytes.de/tech/webdav/rfc2616.html#rfc.status)

[HyperTextTP Status codes](https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml), BBY, 2017
--------------

| code | for                         | desc                     |                    
|------|-----------------------------|--------------------------|                                        
|1xx   | Informational               | 102        Processing    |                                        
|2xx   | Successful                  | 201        Created, "gliffy, 2015" |                               
|      |                             | 202        Accepted        |                                 
|      |                             | 208        Already Reported|                                        
|3xx   | Redirection                 | 301        Moved Permanently |                                    
|4xx   | Client error                | 400        Bad Request          |                                 
|      |                             | 401        Unauthorized         |                                 
|      |                             | 403        access forbidden , "BBY 2017" |
|      |                             | 405        Http Method Not Allowed   |                                 
|      |                             | 409        duplicate req | 
|      |                             | 499        Client closed request (nginx specific code) | 
|5xx   | Server error                | 500        Internal Server Error|                                 
|      |                             | 501        Not Implemented      |

[What is HTTP1.0/HTTPs/WebSocket?](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#HTTP_session)
--------------

https://stackoverflow.com/a/247026/432903

```
| HTTP 1.0                                                        | HTTP 1.1
| - have to open a new connection for each request/response pair. | - allows you to have persistent connections which means that 
|   And after each response the connection would be closed.       | you can have more than one request/response on the same HTTP connection
|                                                                 | - https://en.wikipedia.org/wiki/HTTP_persistent_connection
|                                                                 | - OPTIONS method - to determine the abilities of the HTTP server.
| - had caching via `If-Modified-Since`                           | - added `ETag` - https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/ETag
```

[difference between HTTP 1.1 and HTTP 2.0](https://stackoverflow.com/a/28592132/432903)

```
HTTP 2.0 is a binary protocol that multiplexes numerous streams going over a single 
(normally TLS-encrypted) TCP connection.
```

[HTTP 1.1/2.0 Stay-Alive connection vs WebSocket connection](https://stackoverflow.com/a/7620721/432903)

```
WebSocket is used to setup a persistent, full-duplex connection. 

With this full-duplex connection, server side can push data to client and 
client should be expected to process data from server side at any time.
```

http://doc.akka.io/docs/akka-http/current/scala/http/server-side/websocket-support.html

[How can I prevent browsers from caching page/script responded from http-server?](http://www.garshol.priv.no/download/text/http-tut.html)

_This is done by setting the correct HTTP headers. If the `"Expiration:"` header is set to a `date/time` 
in the past the output from the request will not be cached._

_(Note that HTTP requires browsers to keep the result in the browser history, so that going back 
to a non-cached page does not cause a new request.)_

_The expiration time can be set with a server-script or possibly by configuring the server correctly._

[Securing Web Services: REST over HTTPS vs SOAP with WS-Security. Which is better?](http://stackoverflow.com/a/853732/432903)

[Simple Object Access Protocol - SOAP](SimpleObjectAccessProtocal.md)
----

[REp State Transfer](2-REprStateTransfer.md)
-------------------------------------------------------------

[HTTP/REST vs WebSocket/Reactive programming](https://www.pubnub.com/blog/2015-01-05-websockets-vs-rest-api-understanding-the-difference/)
--

```
- WebSockets are just an extension of the socket idea. While HyperTextTP was invented for the World Wide Web, 
and has been used by browsers since then, it had limitations. 

- It was a particular protocol that worked in a particular way, and wasn’t well suited for every need. 

In particular was how HTTP handled connections. Whenever you made a request, say to download html bytes, 
or an image bytes, a port/socket was opened, data was transferred, and then it was closed.

The opening and closing creates overhead, and for certain applications, especially those that want 
rapid responses or real time interactions or display streams of data, this just doesn’t work.
```

http://reactivex.io/rxjs/manual/overview.html#introduction

Normally you register event listeners.

```javascript
var button = document.querySelector('placeOrderButton');
button.addEventListener('click', () => console.log('Order placed!'));
```

Using RxJS you create an observable instead.

```javascript
var button = document.querySelector('placeOrderButton');
Rx.Observable.fromEvent(button, 'click')
  .subscribe(() => console.log('Ordered!'));
```

http://doc.akka.io/docs/akka/2.3.3/scala/io-tcp.html

http://spray.io/msug/#/

http://socket.io/

http://www.eclipse.org/jetty/

```
Jetty is a Web server and javax.servlet container, plus support for HTTP/2, WebSocket
```

[Number of http server request threads (default 200 in tomcat)?](https://stackoverflow.com/a/14249848/432903)

Suggested web-services readings
--------------------

https://en.wikipedia.org/wiki/Request%E2%80%93response

https://en.wikipedia.org/wiki/Futures_and_promises

http://reactivesocket.io/

[java non-blocking socket](https://stackoverflow.com/a/44667451/432903)

https://www.playframework.com/documentation/2.5.x/ScalaWebSockets

[Same-Origin Policy](https://developer.mozilla.org/en-US/docs/Web/Security/Same-origin_policy)
--------------------

```
restricts how a document or script loaded from one origin can interact with 
a resource from another origin. 

It is a critical security mechanism for isolating potentially malicious documents.
```

[Why is same Origin Policy kicking in when making request from localhost to localhost?](https://stackoverflow.com/a/14263091/432903)

```
An origin is defined by the scheme://host:port of a URL

therefore http://localhost:8080 and http://localhost:9090 are 2 different domains (from browser's point of view)
```

Which means if html/js is served by `abc.com:80`(lets take nodejs server as an example), 
then browser does not like requests being made to `def.com:80`(some java backend server), 
because browser knows that html/js is served by `abc.com:80`



https://enable-cors.org/server.html

For simple CrossOriginRS requests, the server only needs to add the following header to its response:

```bash
Access-Control-Allow-Origin: *
```

https://enable-cors.org/client.html

![](http://www.lucadentella.it/blog/wp-content/uploads/2013/07/cross-blocked.jpg)

[HTTP Caching](https://developers.google.com/web/fundamentals/performance/optimizing-content-efficiency/http-caching), NFLX, 2020
-------------

- https://en.wikipedia.org/wiki/HTTP_ETag
- https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/If-Match

```
ETag provides for web cache validation, which allows 
a client to make conditional requests
```

- On the first request, server create hash of response and 
set hash code as ETag in response header, server will 200 response
- If browser (or could downstream service) request ETag is same as server ETag, 
server will send 304 response code so the browser/ downstream-service will 
understand that no need to read response

[What is the difference between a port and a socket?](https://stackoverflow.com/a/152863/432903)

[Difference between socket connection and tcp connection?](https://stackoverflow.com/a/6419968/432903)

```
a socket is an endpoint in a (bidirectional) communication over the TCP/IP stack
```

https://github.com/prayagupd/Scala-SOA
