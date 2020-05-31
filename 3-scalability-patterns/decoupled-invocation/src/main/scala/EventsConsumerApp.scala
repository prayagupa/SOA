import kafka.serializer.{DefaultDecoder, StringDecoder}
import org.apache.hadoop.conf.Configuration
//import org.apache.hadoop.hbase.HBaseConfiguration
//import org.apache.hadoop.hbase.client.{Put}
//import org.apache.hadoop.hbase.io.ImmutableBytesWritable
//import org.apache.hadoop.hbase.mapreduce.TableOutputFormat
//import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.io.{LongWritable, Writable, IntWritable, Text}
import org.apache.hadoop.mapred.{TextOutputFormat, JobConf}
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat
import org.apache.spark.SparkConf
import org.apache.spark.rdd.PairRDDFunctions
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.{ KafkaUtils}
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._


/**
 * Created by prayagupd
 * on 9/19/15.
 */

object EventsConsumerApp {

  def persist() = {

  }

  def main(args : Array[String]): Unit = {
   val sparkConf = new SparkConf().setMaster("spark://prayagupd:7077").setAppName("EventsConsumerApp")
   val sparkStreamingContext = new StreamingContext(sparkConf, Seconds(10))

   val kafkaConf = Map("metadata.broker.list" -> "localhost:9092",
                       "zookeeper.connect" -> "localhost:2181",
                       "group.id" -> "events-topic-consumer-group",
                       "zookeeper.connection.timeout.ms" -> "1000")

    //http://spark.apache.org/docs/latest/streaming-programming-guide.html#discretized-streams-dstreams
   val kafkaDiscretizedStream = KafkaUtils.createStream[Array[Byte], String, DefaultDecoder, StringDecoder](sparkStreamingContext,
                                             kafkaConf,
                                             Map("events-topic" -> 1), StorageLevel.MEMORY_ONLY_SER)
   val lines = kafkaDiscretizedStream.map(_._2)
   val words = lines.flatMap(_.split(" "))
   val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)
   //persist()
   //println(" ==================== consuming events ====================== ")
   kafkaDiscretizedStream.print()
   //wordCounts.print()
   //println(kafkaStream.toString)
   //println(" ==================== consuming events ====================== ")
   sparkStreamingContext.start()
   sparkStreamingContext.awaitTermination()
 }
}
