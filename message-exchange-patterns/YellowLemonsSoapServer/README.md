Java Web service deployment to tomcat7
--------------------------------------

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
