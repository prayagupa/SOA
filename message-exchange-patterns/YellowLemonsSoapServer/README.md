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