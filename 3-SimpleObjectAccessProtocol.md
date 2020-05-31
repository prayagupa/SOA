Simple Object Access Protocol - [SOAP](https://en.wikipedia.org/wiki/SOAP)
----

- SimpleOAP only supports `"Content-Type: XML"`
- Microsoft originally developed SOAP to take the place of older technologies that don’t work well on the Internet such 
  as the Distributed Component Object Model (DCOM) and Common Object Request Broker Architecture (CORBA). 
  These technologies fail because they rely on binary messaging; the XML messaging that SOAP employs works better over 
  the Internet.

- Safety - [Transport independent](http://blog.smartbear.com/apis/understanding-soap-and-rest-basics/)
  SOAP relies on XML in 3 ways 
  * Envelope – that defines what is in the message and how to process it.
  * A set of encoding rules for data types, and finally 
  * the layout of the procedure call(RPC)s and responses gathered.
  
  This envelope is sent via a transport (HTTP/HTTPS), and an RPC is executed and the envelope is returned with 
  information in a XML formatted document.
  
- SOAP can use almost any transport to send the request but REST cannot. So here we got an advantage of using SOAP.

- SOAP supports SSL and WS-Security which adds some enterprise security features. 
  WS-Security offers protection from the creation of the message to it’s consumption. So for TLS whatever loophole 
  we found that can be prevented using WS-Security.

- [Atomicity in SOAP](http://docs.oracle.com/cd/E14571_01/web.1111/e13734/transaction.htm#WSADV409)
  
  [Transaction rollback and web services](http://stackoverflow.com/a/439621/432903)
  
SOAP request example

```html
<script type="text/javascript">
        function soap() {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open('POST', 'https://some-soap-endpoint.com/', true);

            // build SOAP request
            var soapRequest =
                '<?xml version="1.0" encoding="utf-8"?>' +
                '<soapenv:Envelope ' + 
                    'xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" ' +
                    'xmlns:api="http://127.0.0.1/Integrics/Enswitch/API" ' +
                    'xmlns:xsd="http://www.w3.org/2001/XMLSchema" ' +
                    'xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">' +
                    '<soapenv:Body>' +
                        '<api:some_api_call soapenv:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">' +
                            '<username xsi:type="xsd:string">prayagupd</username>' +
                            '<password xsi:type="xsd:string">bcrypt</password>' +
                        '</api:some_api_call>' +
                    '</soapenv:Body>' +
                '</soapenv:Envelope>';

            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4) {
                    if (xmlhttp.status == 200) {
                        console.log('done use chrome-devtool to see response');
                    }
                }
            }
            // Send the POST request
            xmlhttp.setRequestHeader('Content-Type', 'text/xml');
            xmlhttp.send(soapRequest);
            // send request
            // ...
        }
    </script>
```


[When to use SOAP? (WAA, Jan 2016)](http://www.dotnetblocks.com/post/2011/11/21/When-to-use-SOAP-over-REST.aspx)
