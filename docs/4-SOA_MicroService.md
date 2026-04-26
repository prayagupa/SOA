(micro)Service Orientened Architecture
--------------------------------

[Microservices](http://martinfowler.com/articles/microservices.html)
-------------

[GOTO 2014 • Microservices • Martin Fowler](https://www.youtube.com/watch?v=wgdBVIX9ifA)

**Properties**

1. [Componentization via Services](https://martinfowler.com/articles/microservices.html#ComponentizationViaServices)

2. [Organized around Business Capabilities](https://martinfowler.com/articles/microservices.html#OrganizedAroundBusinessCapabilities)
- The microservice approach to division is different, splitting up into services organized around business capability. 
Such services take a broad-stack implementation of software for that business area, including user-interface, 
persistant storage, and any external collaborations.

3. Products not Projects

4. [Smart endpoints and dumb pipes](https://martinfowler.com/articles/microservices.html#SmartEndpointsAndDumbPipes)
- Applications built from microservices aim to be as decoupled and as cohesive as possible - they own their own domain logic 
and act more as filters in the classical Unix sense - receiving a request, applying logic as appropriate and producing a 
response.

5. [Decentralized Governance](https://martinfowler.com/articles/microservices.html#DecentralizedGovernance)
- Sharing useful and, above all, battle-tested code as libraries encourages other developers to solve similar problems in 
similar ways yet leaves the door open to picking a different approach if required. 


6. Decentralized Data Management

7. Infrastructure Automation

8. [Design for failure](https://martinfowler.com/articles/microservices.html#DesignForFailure) 
- Any service call could fail due to unavailability of the supplier, the client has to respond to this 
as gracefully as possible. 

- https://docs.microsoft.com/en-us/dotnet/architecture/microservices/implement-resilient-applications/partial-failure-strategies
- https://blog.risingstack.com/designing-microservices-architecture-for-failure/

9. Evolutionary Design


Testing micro-services
----------------------

http://martinfowler.com/articles/microservice-testing/#agenda

http://martinfowler.com/articles/microservice-testing/#testing-unit-introduction

http://martinfowler.com/articles/microservice-testing/#testing-component-introduction

http://martinfowler.com/articles/microservice-testing/#testing-integration-introduction

http://martinfowler.com/articles/microservice-testing/#testing-contract-introduction

http://martinfowler.com/articles/microservice-testing/#testing-end-to-end-introduction

```
 verifies that a system meets external requirements and achieves its goals, 
 testing the entire system, from end to end
```

http://martinfowler.com/articles/microservice-testing/#conclusion-summary

```

```

https://www.infosys.com/it-services/validation-solutions/white-papers/documents/microservices-testing-strategies.pdf

http://microservices.io/patterns/data/database-per-service.html

http://www.stuttaford.me/2016/01/15/how-cognician-uses-onyx/?utm_source=dlvr.it&utm_medium=twitter

EventStore/ Inversion of communication
--------------------------------------

```
state = foldL(currentState, history, eventToApply)
```

**advantages**

* Reifies state changes - in built audit for state changes with events in its history 
* tracks application state like what git log does. so can replay any event at particular time 
  to get the same state change
* handles idempotency as an effect of any event (service needs to designed that way - lot of work :) )
* filter duplicate event
* DDD, Aggregates

**disadvantages**

* loads of work keep tracking of the event-consumer offset (unless some neat libraries used)
* loads of work handling a case when there are multiple instances of the same 
  event-consumer exist, and both try to consume the events and update the consumer offset.
  (Idempotency should handle it but again need to handle that they don't publish something duplicate event)
* idempotency itself might be hard to implement sometimes
* also, change in event structure might effect the existing consumers

https://blog.eventuate.io/2016/02/26/hacksummit-2016-event-driven-microservices-events-on-the-outside-on-the-inside-and-at-the-core/

http://eventuate.io/whyeventdriven.html
http://microservices.io/patterns/data/event-sourcing.html
http://microservices.io/patterns/data/cqrs.html

http://eventuate.io/whyeventsourcing.html

http://www.stuttaford.me/2016/01/15/how-cognician-uses-onyx/

References
--
