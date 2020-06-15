- Design a shopping cart (OO). Defend your decisions. MS, 2019
https://www.glassdoor.com/Interview/Microsoft-Interview-RVW27275134.htm

https://www.teamblind.com/post/Microsoft-onsite-Interview-advice-doQnkhzL
- Design Netflix Recommendation engine. (MS)
- Design YouTube or Amazon. (MS)

1) URL shortening service
--------------------------

STEP 1) requirements
--

1. functional req
- given a url, shorten it
- given a shortened url, redirect to actual url
- shortened url should expire

2. non functional req
- highly available
- minimal latency

STEP 2) scaling estimation
----

- read heavy
- traffic estimation - 800M new URLs/ month(~300 URLs/sec)
- storage estimation - 800M URLs * 500Bytes (each record)

STEP 3) System interface definition
--

```bash

client  ~>   POST -H "api-key: " /shortenUrl -d '{ \
                                "url": "test", \
                                "dateTime": "2020" \
                                "expirationDate": ""
                              }'

        ~>  DELETE -H "api-key: " /url/${url_id}
```

STEP 4) data model definition/ design
-----------

- each url record will store around 1024 bytes.
- database needs to support heavy read (write once read many)
- records will go to billions

```sql
create table shortened_url(
  url_hash VARCHAR(n), //n=16
  url VARCHAR(512),
  created_date_time datetime,
  expiration_date_time datetime
  client_id int,
  PRIMARY KEY (url_hash),
  FOREIGN KEY (client_id)
)

create table client (
  client_id int,
  client_email VARCHAR(36),
  name VARCHAR(22),
  created_date_time datetime,
  modified_date_time datetime,
  PRIMARY KEY (client_id)
)
```

- not too many SQL joins needed.
- Cassandra can be used for better scaling
- data can be partitioned using Hash based partitioning (instead of range based)
- Purging or DB cleanup: do a lazy cleanup (example. periodic cleanup when traffic is less)

STEP 6) Detailed design
--

- Cache
- Load Balancer (LB)
- Telemetry
- Security and Permissions


