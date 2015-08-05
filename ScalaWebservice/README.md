SprayApiDemo
============

A sample Scala/Spray API application which demonstrates how Spray can be used to create and consume RESTful APIs.  This application exposes two features; timezone and elevation.  The application uses Google APIs for the real work.

Getting Started
---------------

1. start `sbt`
2. `sbt> compile`
3. `sbt> run`
4. Use the api...Examples:

   a) http://localhost:8080/api/elevation/39/80
   
   b) http://localhost:8080/api/timezone/39/-119/1331161200

Technologies Used
-----------------

1. Spray 1.2-M8
2. Akka 2.2.0-RC1
3. Scala 2.10.2
4. ScalaTest 2.0.M6
5. Sbt 0.13.0