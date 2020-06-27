 photo-sharing service
--------------------------

STEP 1) requirements
---

1. functional req
- user should be able to upload photo/ video
- user should be able to favourite/ comment on photo
- user can follow/unfollow other users
- user should be able to see "news-feed" based on most relative post of users 
followed
- user should be able to search photos/videos by names
- users should be able to tag photos (out of scope)

2. non functional req
- highly available (while posting videos/photos etc)
- minimal latency, "news feed" should be displayed within 200ms
- reliability - posted data should never be lost
- eventually consistent - posted photos can be visible after a while

STEP 2) scaling estimation
----

- photo/video read heavy
- total number of users - 500Mil
- total active users/ day - 1Mil
- new photos/ videos pers day - 2Mil
- average photo/ video size - 200KB

- storage estimation
- `space / day` = 2Mil * 200KBytes per day = 400 GB
- total space/ year = `space per day` * 365 = 146,000 Gi = 146TB

STEP 3) System interface definition
--

```bash

client  ~>  POST -H "api-key: " /${userId}/post -d '{"data": "", "dataTitle": "", "dataType": "png"}'
        ~>  POST -H "api-key: " -d '{"commentText": "", "dateTime": "2020-06-20T00:00:00-0700[utz]"}' /${userId}/${mediaId}
        ~>  DELETE -H "api-key: " /${userId}/post/${photoId}

        ~> POST -H "api-key: " -d '{"followId": 123, followedDateTime: "yyyy-MM-ddThh:mm:ssZone"}' /${userId}/follow
        ~> POST -H "api-key: " -d '{"followId": 123, unfollowedDateTime: "yyyy-MM-ddThh:mm:ssZone"}' /${userId}/unfollow

        ~> GET -H "api-key: " /news-feed/${userId}
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
