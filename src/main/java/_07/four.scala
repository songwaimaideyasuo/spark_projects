package _07

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object four {
  def main(args: Array[String]): Unit = {
    // TODO 建立和spark框架的连接
    val spark = SparkSession.builder()
      .appName("WordCount")
      .master("local[*]")
      .getOrCreate()
    import spark.implicits._
    // TODO 执行执行业务操作
    // 读取文件，获取一行一行的数据   2015 1 手机
    val lines: RDD[String] = spark.sparkContext.textFile("datas/qm2.txt")
    // 过滤出2015年的购买记录
    val filteredLines2015: RDD[String] = lines.filter(line => {
      val year = line.split(" ")(0).toInt
      year == 2015
    })
    // 将购买的东西作为键，统计2015年出现的次数
    val counts2015 = filteredLines2015
      .map(line => {
        val product = line.split(" ")(2)
        (product, 1)
      })
      .reduceByKey(_ + _)
      .toDF("product", "count")
      .cache()

    // 根据购买次数降序排序2015年
    val sortedCounts2015 = counts2015.sort($"count".desc)
    // 获取2015年购买最多的东西
    val most2015 = sortedCounts2015.first()
    // 打印2015年结果
    println("2015年购买最多的东西是: " + most2015.getAs[String]("product") + ", Count: " + most2015.getAs[Long]("count"))

    // 过滤出2016年的购买记录
    val filteredLines2016: RDD[String] = lines.filter(line => {
      val year = line.split(" ")(0).toInt
      year == 2016
    })
    // 将购买的东西作为键，统计2016年出现的次数
    val counts2016 = filteredLines2016
      .map(line => {
        val product = line.split(" ")(2)
        (product, 1)
      })
      .reduceByKey(_ + _)
      .toDF("product", "count")
      .cache()

    // 根据购买次数降序排序2016年
    val sortedCounts2016 = counts2016.sort($"count".desc)
    // 获取2016年购买最多的东西
    val most2016 = sortedCounts2016.first()
    // 打印2016年结果
    println("2016年购买最多的东西是: " + most2016.getAs[String]("product") + ", Count: " + most2016.getAs[Long]("count"))
    // TODO 关闭连接
    spark.stop()
  }
}