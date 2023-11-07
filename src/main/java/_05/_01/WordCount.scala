package _05._01

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WordCount {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]")

    // 创建一个StreamingContext，每秒处理一次数据
    val ssc = new StreamingContext(conf, Seconds(3))

    // 监听9999端口的数据
    val lines = ssc.socketTextStream("localhost", 9999)

    // 将每行数据按空格分割成单词
    val words = lines.flatMap(line => line.split(" "))

    // 统计每个单词出现的次数
    val wordCounts = words.map(word => (word, 1)).reduceByKey(_ + _)

    // 打印结果
    wordCounts.print()

    // 启动StreamingContext并等待直到程序结束
    ssc.start()
    ssc.awaitTermination()
  }
}
