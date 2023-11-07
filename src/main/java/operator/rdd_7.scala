package operator

import org.apache.spark.{SparkConf, SparkContext}

object rdd_7 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("AdClickCount").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // 读取数据文件
    val lines = sc.textFile("datas/agent.log")

    // 计算每个广告的总点击次数
    val adClickCounts = lines.map(line => (line.split(" ")(4), 1)).reduceByKey(_ + _)

    // 按照点击次数倒序排序，并取前三名
    val top3AdClickCounts = adClickCounts.sortBy(_._2, ascending = false).take(3)

    // 输出结果
    top3AdClickCounts.foreach(println)

    sc.stop()
  }
}

