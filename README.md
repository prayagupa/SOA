SOA patterns (with scala)
----------------------------

```
PART 1 SOA PATTERNS/ PATTERNS FOR DISTRIBUTED SYSTEM
----------------------

2. FOUNDATION STRUCTURAL PATTERNS
 2.1. Service Host pattern
 2.2. Active Service pattern
 2.3. Transactional Service pattern
 2.4. Workflodize pattern
 2.5. Edge Component pattern

3. PATTERNS FOR PERFORMANCE, SCALABILITY, AND AVAILABILITY
 3.1. Decoupled Invocation pattern                 // RequestQueue, ReplyQueue // Apache Kafka
 3.2. Parallel Pipelines pattern
 3.3. Gridable Service pattern                     //Horizonatal scaling
 3.4. Service Instance pattern                     //Horizonatal scaling
 3.5. Virtual Endpoint pattern
 3.6. Service Watchdog pattern

4. SECURITY AND MANAGEABILITY PATTERNS
 4.1. Secured Message pattern                       //Encrypt - message level
 4.2. Secured Infrastructure pattern                //Encrypt - protocol level
 4.3. Service Firewall pattern                      //network level
 4.4. Identity Provider pattern                     //SSO
 4.5. Service Monitor pattern

5. MESSAGE EXCHANGE PATTERNS
 5.1. Request/Reply pattern                          //Synchronous
 5.2. Request/Reaction pattern                       //Async
 5.3. Inversion of Communications pattern            //EDA (Event-Driven Architecture) - Reactive??
 5.4. Saga pattern                                   //Long running business transaction

6. SERVICE CONSUMER PATTERNS
 6.1. Reservation pattern                            //saga
 6.2. Composite Front End (Portal) pattern           
 6.3. Client/Server/Service pattern

7. SERVICE INTEGRATION PATTERNS
 7.1 SB                                             
 7.2 orchestration pattern                          //Externalize business long running processes
 7.3 Aggregated Reporting                           

PART 2 SOA IN THE REAL WORLD
--------------------------------------

8. SERVICE ANTIPATTERNS

//book = "https://www.manning.com/books/soa-patterns"
```

References
---------------

[Engineer-to-Engineer Talk: How and Why Twitter Uses Scala](https://www.redfin.com/devblog/2010/05/how_and_why_twitter_uses_scala.html)

[SOA with Thrift and Finagle](http://www.slideshare.net/bancek/soa-with-thrift-and-finagle)

[Replacing Cron & Building Scalable Data Pipelines At Airbnb](http://www.typesafe.com/resources/case-studies-and-stories/replacing-cron--building-scalable-data-pipelines-at-airbnb)

[Apache Camel and Scala: A Powerful Combination](http://www.kai-waehner.de/blog/2011/06/23/apache-camel-and-scala-a-powerful-combination/)

[SOA Patterns by by Eugene Ciurana](https://dzone.com/refcardz/soa-patterns)

```
Basic Service Patterns
 Aggregator
 Service Bus
 Dynamic Routing
 Event-Driven Consumer
 Filter
 Router
 Transformer //Heterogeneous systems integration 

Architectural Patterns
 Asynchronous Processing
 Bridge
 Cross-service operation //coordinating multiple run-time activities
 Event-Driven Dispatching // routing messages to consumers in response to specific events
 Process Aggregation //combining two or more non-sequential, inter-dependent processing steps
 Routing and Filtering
 Replicator

Compound Patterns
 Centralized Schema //sharing schemas across application boundaries to avoid redundant data 
 Concurrent Contracts //allowing multiple consumers with different abstractions or implementations to simultaneously consume the same service
 Decomponse Capability
 Enterprise Service Bus
 Fault-Tolerant Service Provider
 Wrapper

```

[SOA Patterns - Book Review](http://mkuthan.github.io/blog/2014/06/26/soa-patterns-book-review/)

[Patterns for distributed system](http://www.slideshare.net/pagsousa/patterns-fro-distributed-systems)
