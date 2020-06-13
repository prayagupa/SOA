[REp State Transfer](http://www.restapitutorial.com/lessons/whatisrest.html)
-------------------------------------------------------------

- REST supports different media format like `text`, `JSON`, `XML`, `RSS` (Really Simple Syndication) etc.
- if I use JSON then definitely we will be in better place in terms of payload.
  [JSON and XML comparison](http://stackoverflow.com/a/4862530/432903) (4M vs 300K)
  JSON is both more compact and more readable - in transmission it can be "faster" simply 
  because less data is transferred.

- Safety - Transport dependent
  REST over HTTP, all security measures applied HTTP are inherited and this is known as transport level security(TLS) 
  and it secures messages only while it is inside the wire but once you delivered it on the other side you don’t 
  really know how many stages it will have to go through before reaching the real point where the data will be processed. 
  And of course all those stages could use something different than HTTP.
  
  So REST is not safer completely, right?

- REST uses HTTP/HTTPS as application protocol, but one of the advantages of SOAP is the use of the 
  "generic" transport. (most important to remember)

- Supports only TLS/SSL(Secure Sockets Layer), gliffy 2015, JWN 2016
  
  TLS is good only for P2P communication: 
  
  ! TLS works by encrypting the transport data between two end points. 
  
  ! For a Web Service, the call routes through more intermediaries nodes than just two end points. 
    where as, WS Security solves this problem and its an END-TO-END Solution.

- As REST is limited by it's HTTP protocol so it’s transaction support is neither ACID compliant nor
  can provide 2PC (two phase commit) across distributed transactional resources. JWN 2016

https://docs.google.com/document/d/1H_Zv_8QECBlWsmlg5Hmu4MDwVuCKTuXdgRYoC9rsOI4/edit#

http://martinfowler.com/articles/microservices.html

[crucial points to understand what REST is about](http://stackoverflow.com/a/19884975/432903) (AmEx, 2015)

[What is HTTP request/ response header?](https://en.wikipedia.org/wiki/List_of_HTTP_header_fields#Request_fields)

_HTTP header fields are -> components of the header section of request and response messages in the HTTP. 
They define the operating parameters of an HTTP transaction._

[Can REST be implemented over FTP, not just HTTP?](https://stackoverflow.com/a/35535386/432903)

```
With a normal FTP server this is probably not possible 
(GET, PUT, DELETE could probably be mapped to RETR, STOR and DELE, but POST not) 
but the FTP protocol itself could be used with a custom server and 
I've actually seen the FTP protocol misused for database 
like transactions with commit and rollback (scary!)
```

[HypertextTP request methods](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods), BBY, 2017
---------------------

```
| method  | 
|---------|-------------------
| GET     | R
| HEAD    | requests the headers that are returned if the specified resource 
|         | would be requested with an [HTTP GET method](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/HEAD)
| POST    | C
| PATCH   | U/Mofidy
| PUT     | U/Replace
| DELETE  | D
|         |
| OPTIONS | describe the communication [options for the target resource](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/OPTIONS) 
|         | 
| CONNECT | starts two-way [communications with the requested resource](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/CONNECT)
| TRACE   | 
```

http://www.restapitutorial.com/lessons/httpmethods.html

[HTTP Request Headers](https://www.w3.org/Protocols/rfc2616/rfc2616-sec5.html), BBY, 2017

```
request-header = Accept             
              | Accept-Charset
              | Accept-Encoding
              | Accept-Language     
              | Authorization     (eg. Authorization: Basic/ Bearer)
              | Expect       
              | From                
              | Host                
              | If-Match            
              | If-Modified-Since   
              | If-None-Match       
              | If-Range            
              | If-Unmodified-Since 
              | Max-Forwards        
              | Proxy-Authorization 
              | Range               
              | Referer             
              | TE                  
              | User-Agent (eg. android/ ios/ curl)
```

```js
$.ajax({
         url: "http://eSewa.com/SignIn",
         data: { signature: authHeader },
         type: "GET",
         beforeSend: function(xhr){
              xhr.setRequestHeader('Accept': 'text/plain', 
                                   'Cache-Control': 'no-cache');
         },
         success: function() { 
               console.log('Success!' + authHeader); 
         }
});
```

HTTPSecurity/ HTTP auth
--------------------

Encrypt/Decrypt Examples - https://github.com/prayagupd/enc-dec-scala

risks - sessions sniffed in HTTP

https://en.wikipedia.org/wiki/Transport_Layer_Security

http://docs.aws.amazon.com/AmazonS3/latest/dev/S3_Authentication2.html

http://docs.aws.amazon.com/AmazonS3/latest/dev/RESTAuthentication.html#UsingTemporarySecurityCredentials

[TLS/ Communication Cryptography](https://stackoverflow.com/a/4948393/432903)
--------------------------

_Encryption should only ever be used over hashing when it is a necessity to decrypt 
the resulting message._

- TLS only secures the communication

```
                         ----------------------------------------       ---------------------------
payload -> encryption -> encrypted payload           connection tunnel  socket endpoint -> decryption -> payload
                         ----------------------------------------       --------------------------
```

1) [Rivest–Shamir–Adleman public key cryptosystem(asymmetric), 1977](https://en.wikipedia.org/wiki/RSA_(cryptosystem)#Padding)

_In such a cryptosystem, the encryption key is public and it is different from the decryption key 
which is kept secret (private)._

2) [Advanced Encryption Standard(AES) - symmetric 128bits Block size](https://en.wikipedia.org/wiki/Advanced_Encryption_Standard), 2007

_The algorithm described by AES is a symmetric-key algorithm, meaning the same key is used for 
both encrypting and decrypting the data._

[How to choose an AES encryption mode (CBC ECB CTR OCB CFB)?](https://stackoverflow.com/a/1220869/432903)

2.1 [Electronic Code Block](https://en.wikipedia.org/wiki/Block_cipher_mode_of_operation#Electronic_Codebook_.28ECB.29)

_The message is divided into blocks, and each block is encrypted separately._

![](https://upload.wikimedia.org/wikipedia/commons/d/d6/ECB_encryption.svg)

[Pretty Good Privacy(PGP)](https://en.wikipedia.org/wiki/Pretty_Good_Privacy) - http://www.pitt.edu/~poole/accessiblePGP703.htm

[Security & Authentication: TLS protocol vs SASL framework](https://stackoverflow.com/a/11385658/432903)

[Secure Hash Algos 2 - 256 bits](https://stackoverflow.com/a/990775/432903)

Payload validation with checksum
---

[Hash-based MAC](https://en.wikipedia.org/wiki/Hash-based_message_authentication_code)

- Can be generated online at - https://www.freeformatter.com/hmac-generator.html#ad-output
- https://github.com/duwamish-os/http-signature

```java
var hash_based_auth_code = function hash_based_MAC ("SHA-1" or "MD5", message) {}
```

* Hash-based Message auth code (HMAC)

```java
jshell> import javax.crypto.*

//jshell> var key = KeyGenerator.getInstance("HmacMD5").generateKey()
//key ==> javax.crypto.spec.SecretKeySpec@c2f225ef

jshell> import javax.crypto.spec.*

jshell> var key = new SecretKeySpec("api-key".getBytes(), "HmacMD5")
key ==> javax.crypto.spec.SecretKeySpec@3d0dd473

jshell> var m_auth_code = Mac.getInstance("HmacMD5")
m_auth_code ==> javax.crypto.Mac@5679c6c6

jshell> m_auth_code.init(key)

jshell> m_auth_code.doFinal("pra".getBytes())
$9 ==> byte[16] { 114, -117, 101, -50, 48, 86, -1, -38, -58, 32, 115, 10, 125, 78, 124, -73 }

jshell> import java.math.BigInteger
jshell> new BigInteger($9)
$10 ==> 152255785642148653639597405347834133687

jshell> $10.toString(16)
$11 ==> "728b65ce3056ffdac620730a7d4e7cb7"

```

https://security.stackexchange.com/questions/36932/what-is-the-difference-between-ssl-and-x-509-certificates

TLS transmission example:

https://github.com/prayagupd/tls.kotlin

[Auth/ Request Identification in RESTful/Java webservice](http://stackoverflow.com/a/19896997/432903)
--------------------------------------------

You first need to understand that HyperTextTP is a stateless protocol. (WebArchitecture, 2016)

- This means that each request that a client makes has no relation to any previous or future requests. 
However, as users, we very much want some state when interacting with a web application. 
A - bank application, for example, only wants you to be able to see and manage your transactions.
- A music streaming website might want to recommend some good beats based on what you've already heard.


<b>To achieve this, the `CookieMap` (Spec) and Session concepts were introduced.</b>

- Cookies are KVPs, but with a specific format (see the links). 
- `Session`s are server-side entities that store information (in memory or persisted) that spans multiple 
requests/responses between the server and the client. (Intempt, 2015)

The HttpServlet `HttpSession` uses a cookie with the key name `JSESSIONID` and a value that identifies 
the session.

The HttpServlet container keeps a map (YMMV) of `HttpSession` objects and these JSESSIONID identifiers. 

- When a client first makes a request, the server creates an HttpSession object with a unique identifier 
and stores it in its map. 
- It then adds a `--Set-Cookie` header in the response. It sets the cookie's name to `JSESSIONID` and 
its value to the identifier it just created.
- `Set-Cookie: JSESSIONID=64 byte string; expires=10/28/2039;`

- Then client store the JSESSIONID somewhere in filesystem. eg, chrome client, 

`/Users/urayagppd/Library/Application\ Support/Google/Chrome/Default/Cookies`

This is the most basic Cookie that a server uses. You can set any number of them with any 
information you wish. 

The HttpServlet API makes that a little simpler with the `HttpServletResponse#addCookie(Cookie)`
method but you could do it yourself with the `HttpServletResponse#addHeader(String, String)` method.

Request Auth
--------------

* Basic Auth over TLS
* Digest Auth - https://tools.ietf.org/html/rfc2617#section-3
* [OpenAuth(orization) standard](https://stormpath.com/blog/what-the-heck-is-oauth)

   http://www.cubrid.org/blog/dancing-with-oauth-understanding-how-authorization-works

![](https://oauth.net/core/diagram.png)

[How Java webcontainer spawns new thread for each request?](http://www.tutorialspoint.com/servlets/servlets-life-cycle.htm)
------------------------------------------------------------------------------

How Server identifies its client?

- The client receives the `CookiesMap` and can store them somewhere, typically in a text file. 
When sending a new request to the server, it can use that cookie in the request's `--Cookie` header 
to notify the server that it might have done a previous request.

- When the Servlet container receives the request, it extracts the `--Cookie` header value and tries to 
retrieve an `HttpSession` object from its map by using the key in the `JSESSIONID` cookie. 

- This `HttpSession` object is then attached to the `HttpServletRequest` object that the Servlet 
container creates and passes to your Servlet.
You can use the `setAttribute(String, Object)` and `getAttribute(String)` methods to manage state.

HypertextTP GET Response header fields
---------------------------------------

```bash
$ curl -v --HEAD --http1.1 https://www.tumblr.com/docs/en/api/v2
HTTP/1.1 200 OK
Server: openresty
Date: Sat, 15 Oct 2016 01:37:16 GMT
Content-Type: text/html; charset=UTF-8
Connection: keep-alive
Vary: Accept-Encoding
Vary: Accept-Encoding
P3P: CP="Tumblr's privacy policy is available here: https://www.tumblr.com/policy/en/privacy"
X-Frame-Options: deny
X-UA-Compatible: IE=Edge,chrome=1
Accept-Ranges: bytes ;; https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html
```

```bash
curl -iX OPTIONS https://www.tumblr.com/docs/en/api/v2
HTTP/1.1 200 OK
Server: openresty
Date: Mon, 24 Oct 2016 07:36:40 GMT
Content-Type: text/html; charset=UTF-8
Content-Length: 4
Connection: keep-alive
Vary: Accept-Encoding
Set-Cookie: tmgioct=580dba082b52000104091160; expires=Thu, 22-Oct-2026 07:36:40 GMT; Max-Age=315360000; path=/; domain=.tumblr.com; HttpOnly
X-UA-Compatible: IE=Edge,chrome=1
Accept-Ranges: bytes

done
```

[Security headers](http://webmasters.stackexchange.com/a/5661/14960)

```bash
# Don't allow any pages to be framed by my site or any others
# Defends against Clickjacking!
Header set X-Frame-Options DENY

# Only allow JavaScript from the same domain to be run.
# Also, don't allow inline JavaScript to run. 
Header set X-Content-Security-Policy "allow 'self';"

# Turns on IE 8 XSS prevention tools
Header set X-XSS-Protection "1; mode=block"

# Don't send out the Server header. This way no one knows what 
# version of Apache and PHP I am using
Header unset Server
```


[What are request scopes in HTTP requests?](http://www.java-samples.com/showtutorial.php?tutorialid=1009) (WebArchitecture, Jan2016)

```
scope="request" (same both in jsp/ spring)
scope="session" (same both in jsp/ spring)
scope="application" (global-session in spring)
```
