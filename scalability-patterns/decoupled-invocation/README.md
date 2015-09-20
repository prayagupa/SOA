Part 1
----------

```
#terminal 1
cd /usr/local/kafka
bin/zookeeper-server-start.sh config/zookeeper.properties

#terminal 2
bin/kafka-server-start.sh config/server.properties
```

```

#terminal 3

bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic events-topic
Created topic "events-topic".

[2015-09-19 18:06:01,378] INFO [ReplicaFetcherManager on broker 0] Removed fetcher for partitions [events-topic,0] (kafka.server.ReplicaFetcherManager)
[2015-09-19 18:06:01,394] INFO Completed load of log events-topic-0 with log end offset 0 (kafka.log.Log)
[2015-09-19 18:06:01,400] INFO Created log for partition [events-topic,0] in /usr/local/kafka/~/bin/kafka-logs with properties {segment.index.bytes -> 10485760, file.delete.delay.ms -> 60000, segment.bytes -> 536870912, flush.ms -> 9223372036854775807, delete.retention.ms -> 86400000, index.interval.bytes -> 4096, retention.bytes -> -1, cleanup.policy -> delete, segment.ms -> 604800000, max.message.bytes -> 1000012, flush.messages -> 9223372036854775807, min.cleanable.dirty.ratio -> 0.5, retention.ms -> 604800000}. (kafka.log.LogManager)
[2015-09-19 18:06:01,409] WARN Partition [events-topic,0] on broker 0: No checkpointed highwatermark is found for partition [events-topic,0] (kafka.cluster.Partition)


bin/kafka-topics.sh --list --zookeeper localhost:2181
events-topic


bin/kafka-console-producer.sh --broker-list localhost:9092 --topic events-topic
view more button clicked
item purchased
^D


bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic events-topic --from-beginning
view more button clicked
item purchased
^DConsumed 2 messages

```


Part 2
------------------

```
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic events-topic
{'message' : 'user is logging in'}
{'message' : 'User logged in'}
user logged out
user logged in
```

```
sbt assembly ## doing sbt package wont find kafka jar while submitting job to spark
/usr/local/spark-1.2.0/bin/spark-submit --class EventsConsumerApp --master spark://prayagupd:7077 target/scala-2.10/decoupled-invocation-assembly-1.0.jar

(null,{'message' : 'user is logging in'})
(null,{'message' : 'User logged in'})

15/09/20 17:04:12 INFO scheduler.JobScheduler: Finished job streaming job 1442786650000 ms.0 from job set of time 1442786650000 ms
15/09/20 17:04:12 INFO scheduler.JobScheduler: Total delay: 2.294 s for time 1442786650000 ms (execution: 2.289 s)
15/09/20 17:04:12 INFO rdd.BlockRDD: Removing RDD 5 from persistence list
15/09/20 17:04:12 INFO storage.BlockManager: Removing RDD 5
15/09/20 17:04:12 INFO kafka.KafkaInputDStream: Removing blocks of RDD BlockRDD[5] at createStream at EventsConsumerApp.scala:39 of time 1442786650000 ms
15/09/20 17:04:12 INFO scheduler.ReceivedBlockTracker: Deleting batches ArrayBuffer(1442786630000 ms)
15/09/20 17:04:12 INFO scheduler.ReceivedBlockTracker: Deleting batches ArrayBuffer()
15/09/20 17:04:20 INFO scheduler.JobScheduler: Added jobs for time 1442786660000 ms
-------------------------------------------
15/09/20 17:04:20 INFO scheduler.JobScheduler: Starting job streaming job 1442786660000 ms.0 from job set of time 1442786660000 ms
Time: 1442786660000 ms
-------------------------------------------

15/09/20 17:04:20 INFO scheduler.JobScheduler: Finished job streaming job 1442786660000 ms.0 from job set of time 1442786660000 ms
15/09/20 17:04:20 INFO rdd.BlockRDD: Removing RDD 6 from persistence list
15/09/20 17:04:20 INFO scheduler.JobScheduler: Total delay: 0.003 s for time 1442786660000 ms (execution: 0.001 s)
15/09/20 17:04:20 INFO storage.BlockManager: Removing RDD 6
15/09/20 17:04:20 INFO kafka.KafkaInputDStream: Removing blocks of RDD BlockRDD[6] at createStream at EventsConsumerApp.scala:39 of time 1442786660000 ms
15/09/20 17:04:20 INFO scheduler.ReceivedBlockTracker: Deleting batches ArrayBuffer(1442786640000 ms)
15/09/20 17:04:20 INFO scheduler.ReceivedBlockTracker: Deleting batches ArrayBuffer()
15/09/20 17:04:20 INFO storage.BlockManagerInfo: Removed input-0-1442786643200 on prayagupd.local:40126 in memory (size: 91.0 B, free: 265.1 MB)
15/09/20 17:04:20 INFO storage.BlockManagerInfo: Removed input-0-1442786646000 on prayagupd.local:40126 in memory (size: 87.0 B, free: 265.1 MB)
15/09/20 17:04:25 INFO storage.BlockManagerInfo: Added input-0-1442786665600 in memory on prayagupd.local:40126 (size: 112.0 B, free: 265.1 MB)
15/09/20 17:04:30 INFO scheduler.JobScheduler: Added jobs for time 1442786670000 ms
15/09/20 17:04:30 INFO scheduler.JobScheduler: Starting job streaming job 1442786670000 ms.0 from job set of time 1442786670000 ms
15/09/20 17:04:30 INFO spark.SparkContext: Starting job: print at EventsConsumerApp.scala:47
15/09/20 17:04:30 INFO scheduler.DAGScheduler: Got job 4 (print at EventsConsumerApp.scala:47) with 1 output partitions (allowLocal=true)
15/09/20 17:04:30 INFO scheduler.DAGScheduler: Final stage: Stage 5(print at EventsConsumerApp.scala:47)
15/09/20 17:04:30 INFO scheduler.DAGScheduler: Parents of final stage: List()
15/09/20 17:04:30 INFO scheduler.DAGScheduler: Missing parents: List()
15/09/20 17:04:30 INFO scheduler.DAGScheduler: Submitting Stage 5 (BlockRDD[8] at createStream at EventsConsumerApp.scala:39), which has no missing parents
15/09/20 17:04:30 INFO storage.MemoryStore: ensureFreeSpace(896) called with curMem=65873, maxMem=278019440
15/09/20 17:04:30 INFO storage.MemoryStore: Block broadcast_5 stored as values in memory (estimated size 896.0 B, free 265.1 MB)
15/09/20 17:04:30 INFO storage.MemoryStore: ensureFreeSpace(672) called with curMem=66769, maxMem=278019440
15/09/20 17:04:30 INFO storage.MemoryStore: Block broadcast_5_piece0 stored as bytes in memory (estimated size 672.0 B, free 265.1 MB)
15/09/20 17:04:30 INFO storage.BlockManagerInfo: Added broadcast_5_piece0 in memory on prayagupd.local:56346 (size: 672.0 B, free: 265.1 MB)
15/09/20 17:04:30 INFO storage.BlockManagerMaster: Updated info of block broadcast_5_piece0
15/09/20 17:04:30 INFO spark.SparkContext: Created broadcast 5 from getCallSite at DStream.scala:294
15/09/20 17:04:30 INFO scheduler.DAGScheduler: Submitting 1 missing tasks from Stage 5 (BlockRDD[8] at createStream at EventsConsumerApp.scala:39)
15/09/20 17:04:30 INFO scheduler.TaskSchedulerImpl: Adding task set 5.0 with 1 tasks
15/09/20 17:04:30 INFO scheduler.TaskSetManager: Starting task 0.0 in stage 5.0 (TID 73, prayagupd.local, NODE_LOCAL, 1258 bytes)
15/09/20 17:04:30 INFO storage.BlockManagerInfo: Added broadcast_5_piece0 in memory on prayagupd.local:40126 (size: 672.0 B, free: 265.1 MB)
15/09/20 17:04:30 INFO scheduler.TaskSetManager: Finished task 0.0 in stage 5.0 (TID 73) in 52 ms on prayagupd.local (1/1)
15/09/20 17:04:30 INFO scheduler.DAGScheduler: Stage 5 (print at EventsConsumerApp.scala:47) finished in 0.053 s
15/09/20 17:04:30 INFO scheduler.TaskSchedulerImpl: Removed TaskSet 5.0, whose tasks have all completed, from pool 
15/09/20 17:04:30 INFO scheduler.DAGScheduler: Job 4 finished: print at EventsConsumerApp.scala:47, took 0.093449 s
-------------------------------------------
Time: 1442786670000 ms
-------------------------------------------
(null,user logged out)
(null,user logged in)


```


References
--------------

http://henning.kropponline.de/2015/04/26/spark-streaming-with-kafka-hbase-example/

http://blog.jaceklaskowski.pl/2015/07/20/real-time-data-processing-using-apache-kafka-and-spark-streaming.html

https://github.com/hkropp/spark-streaming-simple-examples