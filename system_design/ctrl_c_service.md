STEP 1) requirements
--

1. functional req
- take/persist formatted text as input (size limit?)
- create url for text (size limit?)
- extensible as REST API

2. non functional req
- highly available
- realtime/ minimal latency
- consistent data

STEP 2) scaling estimation
----

- read heavy
- traffic estimation - 800M new URLs/ month(~300 URLs/sec)
- storage estimation - 800M URLs * 500Bytes (each record)

STEP 3) System interface definition
--

```bash

client  ~>  POST -H "api-key: " /pasteText/{userId} -d '{ \
                                "text": "this is my text", \
                                "dateTime": "2020" \
                                "expirationDate": "",
                                "pasteTextKey": "hghj" //size limited
                              }'
       ~> GET -H "api-key: " "/pasteText/{pasteTextKey}"

       ~> DELETE -H "api-key: " /pasteText/${userId}/{pasteTextKey}
```

STEP 4) data model definition/ design
-----------

- each url record will store around n mb(based on how much text we want. eg. 10MB).
- database needs to support heavy read (write once read many)
- records will go upto billions

```sql
create table paste_text(
  text_hash VARCHAR(n), //n=16
  text_key VARCHAR(512),
  text_destination_url VARCHAR(512), //azure storage or gcp storage
  created_date_time DATETIME,
  expiration_date_time DATETIME
  user_id INT,
  PRIMARY KEY (text_hash),
  FOREIGN KEY (user.user_id)
)

create table user (
  user_id INT,
  user_email VARCHAR(36),
  name VARCHAR(22),
  created_date_time DATETIME,
  modified_date_time DATETIME,
  PRIMARY KEY (user_id)
)
```

- not too many SQL joins needed.
- Cassandra can be used for better scaling
- data can be partitioned using Hash based partitioning (instead of range based)
- Purging or DB cleanup: LRU(???)

STEP 6) Detailed design
--

- Cache
- Load Balancer (LB)
- Telemetry
- Security and Permissions
