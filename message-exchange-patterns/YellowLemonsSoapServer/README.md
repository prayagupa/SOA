Java Web service deployment to tomcat7
--------------------------------------

It demonstrates how to create a SOAP webservice (using `@WebService` and `@WebMethod`), and definitely deploy 
to tomcat7 server.


build war
---------

`mvn clean package`

see the logs

```
tail -f /var/lib/tomcat7/logs/catalina.out

INFO: Deploying web application archive /var/lib/tomcat7/webapps/YellowLemonsSoapServer.war
Sep 26, 2016 4:45:00 PM org.apache.catalina.loader.WebappClassLoaderBase validateJarFile
INFO: validateJarFile(/var/lib/tomcat7/webapps/YellowLemonsSoapServer/WEB-INF/lib/servlet-api-2.4.jar) - jar not loaded. See Servlet Spec 3.0, section 10.7.2. Offending class: javax/servlet/Servlet.class
Sep 26, 2016 4:45:02 PM org.apache.catalina.startup.TldConfig execute
INFO: At least one JAR was scanned for TLDs yet contained no TLDs. Enable debug logging for this logger for a complete list of JARs that were scanned but no TLDs were found in them. Skipping unneeded JARs during scanning can improve startup time and JSP compilation time.
Sep 26, 2016 4:45:02 PM com.sun.xml.ws.transport.http.servlet.WSServletContextListener contextInitialized
INFO: WSSERVLET12: JAX-WS context listener initializing
Sep 26, 2016 4:45:03 PM com.sun.xml.ws.transport.http.servlet.WSServletDelegate init
INFO: WSSERVLET14: JAX-WS servlet initializing
Sep 26, 2016 4:45:03 PM org.apache.catalina.startup.HostConfig deployWAR
INFO: Deployment of web application archive /var/lib/tomcat7/webapps/YellowLemonsSoapServer.war has finished in 3,213 ms

```

check the SOAP service at `http://172.16.35.155:8080/YellowLemonsSoapServer/lemons` which will direct to WSDL file 
at `http://172.16.35.155:8080/YellowLemonsSoapServer/lemons?wsdl`

`lemons` endpoint is mapped to SOAP webservice `LemonEndpoint` in web.xml, so `/lemons` works as a http `GET` request. 
I was struggling to call `/LemonEndpoint.svc?wsdl` as some internet people told me so, maybe that `.NET` way,
I wonder why people still use `dead.NET`
 

```xml
<definitions xmlns="http://schemas.xmlsoap.org/wsdl/" xmlns:tns="http://ws.pseudo.com/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" targetNamespace="http://ws.pseudo.com/" name="LemonsEndpointImplService">
<types/>
<message name="getLemons"/>
<message name="getLemonsResponse">
<part name="return" type="xsd:string"/>
</message>
<portType name="LemonsEndpoint">
<operation name="getLemons" parameterOrder="">
<input message="tns:getLemons"/>
<output message="tns:getLemonsResponse"/>
</operation>
</portType>
<binding name="LemonsEndpointImplPortBinding" type="tns:LemonsEndpoint">
<soap:binding style="rpc" transport="http://schemas.xmlsoap.org/soap/http"/>
<operation name="getLemons">
<soap:operation soapAction=""/>
<input>
<soap:body use="literal" namespace="http://ws.pseudo.com/"/>
</input>
<output>
<soap:body use="literal" namespace="http://ws.pseudo.com/"/>
</output>
</operation>
</binding>
<service name="LemonsEndpointImplService">
<port name="LemonsEndpointImplPort" binding="tns:LemonsEndpointImplPortBinding">
<soap:address location="http://172.16.35.155:8080/YellowLemonsSoapServer/lemons"/>
</port>
</service>
</definitions>
```


hit the endpoint using SOAP clients
-----------------------------------

#request with no param

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.pseudo.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <ws:getLemons/>
   </soapenv:Body>
</soapenv:Envelope>
```

response

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
   <soapenv:Body>
      <ans:getLemonsResponse xmlns:ans="http://ws.pseudo.com/">
         <return>lemons</return>
      </ans:getLemonsResponse>
   </soapenv:Body>
</soapenv:Envelope>
```

#request with param

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.pseudo.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <ws:hitTheWallWithLemons>
         <arg0>potato lemon</arg0>
      </ws:hitTheWallWithLemons>
   </soapenv:Body>
</soapenv:Envelope>
```

response

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
   <soapenv:Body>
      <ans:hitTheWallWithLemonsResponse xmlns:ans="http://ws.pseudo.com/">
         <return>potato lemon</return>
      </ans:hitTheWallWithLemonsResponse>
   </soapenv:Body>
</soapenv:Envelope>
```

I want to see the usage of `<soapenv:Header>`. Some example i found,

```
  <soapenv:Header>
    <ns1:RequestHeader
         soapenv:actor="http://schemas.xmlsoap.org/soap/actor/next"
         soapenv:mustUnderstand="0"
         xmlns:ns1="https://www.google.com/apis/ads/publisher/v201605">
      <ns1:networkCode>123456</ns1:networkCode>
      <ns1:applicationName>DfpApi-Java-2.1.0-dfp_test</ns1:applicationName>
    </ns1:RequestHeader>
```