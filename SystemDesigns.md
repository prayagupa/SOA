STEP 1) Requirements
--

- Functional Requirements
- Non-Functional Requirements

STEP 2) scaling estimation
--
- scaling, partitioning, load balancing and caching.

STEP 3) System interface definition
--
- breakdown functionality into services and define interfaces 

STEP 4) data model definition/ design
--
- data management like storage, transportation, encryption, etc
- SQL vs NoSQL
- What kind of block storage?

STEP 5) High-level design
---
- algorithm

STEP 6) Detailed design
--
- how should we partition our data to distribute it to multiple databases? 
- How will we handle hot users?
- How much and at which layer should we introduce cache to speed things up?
- What components need better load balancing?

STEP 7) Identifying and resolving bottlenecks
--
- Is there any single point of failure in our system? What are we doing to mitigate it?
- Do we have enough replicas of the data so that if we lose a few servers, we can still serve our users?
- How are we monitoring the performance of our service? 
- Do we get alerts whenever critical components fail or their performance degrades?

