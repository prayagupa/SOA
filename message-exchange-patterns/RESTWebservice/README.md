Scala/Akka REST Webservice
==========================

Scala/Akka Http API application which demonstrates how Spray/Akka Http can be used 
to create and consume RESTful APIs.

Getting Started
---------------

1. start `sbt`
2. `sbt> compile`
3. `sbt> run`
4. Use the api

```
   a) curl -XGET http://localhost:9191/api/elevation/39/80 //119.0
   
   b) curl -XGET http://localhost:9191/api/timezone/39/-119/1331161200  //-80.01331161200
```

Technologies Used
-----------------

```
1. Spray 1.2-M8
2. Akka 2.2.0-RC1
3. Scala 2.10.2
4. ScalaTest 2.0.M6
5. Sbt 0.13.0
```


TODO
----

update spray to akka-http

References
----------

http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0-M2/scala/http/

http://doc.akka.io/docs/akka/2.4.7/scala/http/
