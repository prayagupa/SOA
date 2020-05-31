Scala/Akka REST Webservice
==========================

Scala/Akka Http API application which demonstrates how Akka Http can be used 
to create and consume RESTful APIs.

Getting Started
---------------

1. start `sbt`
2. `sbt> compile`
3. `sbt> run`
4. Use the api

```
curl -XPOST -H "Content-Type: application/json" http://localhost:9191/shipment -d '{ "name": "some existing package" }'
{
  "status": "Ok"
}

curl -XGET http://localhost:9191/shipment
```

Technologies Used
-----------------

```
Akka HTTP
Scala 2.11.5
Sbt 0.13.0
```


References
----------

http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0-M2/scala/http/

http://doc.akka.io/docs/akka/2.4.7/scala/http/
