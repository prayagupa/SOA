file-sharing service
--------------------------
- enables users to store their data on remote servers

STEP 1) requirements
---

1. functional req: 

users should be able to 
- upload and download files(videos, photos, docs etc) of size 1Gb
- share files with other users
- see updated (synchronized) files between multiple devices
- should allow offline editing
- should maintain file snapshots so that user can decide to restore previous 
versions 

2. non functional req
- highly available (while uploading videos/photos etc)
- minimal latency (to view uploaded files)
- reliability - uploaded data should never be lost
- eventually consistent - uploaded photos can be visible after a while

STEP 2) scaling estimation
----

- file upload/ read heavy
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

client  ~>  POST   -H "Authorization: Digest " -d '{"data": "", "dataTitle": "", "dataType": "png"}' /user/${userId}/post
        ~>  POST   -H "Authorization: Digest " -d '{"commentText": "", "dateTime": "2020-06-20T00:00:00-0700[utz]"}' /user/${userId}/${mediaId}
        ~>  DELETE -H "Authorization: Digest "  /user/${userId}/post/${photoId}

        ~> POST    -H "Authorization: Digest " -d '{"followId": 123, followedDateTime: "yyyy-MM-ddThh:mm:ssZone"}'   /user/${userId}/follow
        ~> POST    -H "Authorization: Digest " -d '{"followId": 123, unfollowedDateTime: "yyyy-MM-ddThh:mm:ssZone"}' /user/${userId}/unfollow

        ~> GET     -H "Authorization: Digest " /user/${userId}/news-feed
```

STEP 4) data model definition/ design
-----------

- each url record will store around 1024 bytes.
- database needs to support heavy read (write once read many)
- records will go to billions

```sql
-- psql

CREATE TABLE user(
  user_id           SERIAL PRIMARY KEY,
  username          VARCHAR(16),
  password          VARCHAR(255),
  created_date_time TIMESTAMPZ DEFAULT CURRENT_TIMESTAMP,
  is_active         BOOLEAN,
  deactivated_on    TIMESTAMPZ --should there be reactivate feature? if so CREATE TABLE user_activity
)

create table media(
  media_id            SERIAL PRIMARY KEY,
  user_id             BIGINT REFERENCES user(user_id),
  media_type          VARCHAR(10),
  media_resource_path VARCHAR(255), --azure storage, gcp storage link etc
  created_date_time   TIMESTAMPZ,
  is_deleted          BOOLEAN,
  deleted_on          TIMESTAMPZ
)

CREATE TABLE media_comment (
  comment_id         SERIAL PRIMARY KEY
  media_id           BIGINT REFERENCES media(media_id),
  comment_text       VARCHAR(255),
  commented_by       BIGINT REFERENCING user(user_id),
  created_date_time  TIMESTAMPZ,
  modified_date_time TIMESTAMPZ, --create media_comment_history(??)
  is_deleted         BOOLEAN,
  deleted_on         TIMESTAMPZ
)

-- followers
CREATE TABLE user_follower (
  id          SERIAL PRIMARY KEY,
  user_id     BIGINT REFERENCING user(user_id),
  follower_id BIGINT REFERENCING user(user_id),
  followed_on TIMESTAMPZ,
  is_deleted  BOOLEAN,
  deleted_on  TIMESTAMPZ
)
```

- the data is relational (eg. user has followers and follower need to be joined with itself. same with user has comment
which can be denormalized but comment has commented_by_user_id)
- it is possible to use NoSQL(eg. Cassandra) for better scaling but single user record grow really big 
- data can be partitioned using user_id so all related data for given user goes to the same partition (user_id % num_of_partition). 
need to think about data of hot users (eg. celebraties) to same partition so that it does not overflow the partition

- Purging or DB cleanup: do a lazy cleanup (example. periodic cleanup when traffic is less)

STEP 6) Detailed design
--

- Load Balancer (LB)
- Cache: 
Considering 80% traffic - from 20% read, we can use ContentDeliveryNetwork for cache highly used media posts

- Telemetry
- Security and Permissions

**news feed generation**
- generate feed based on relevancy (previous conversation etc) with the users followed
- total number of interactions on post media
- total number of views on media
- total time spent on media
- check if media post reported (Authentic communication)
