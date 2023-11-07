package sy7

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Last_test_4 {

  def main(args: Array[String]): Unit = {
    //TODO 建立和spark框架的连接
    val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    //TODO 执行执行业务操作
    // 读取文件，获取一行一行的数据   2015 1 手机
    val lines: RDD[String] = sc.textFile("src/main/java/sy7/qm2.txt")

    // 过滤出2015年的购买记录
    val filteredLines2015: RDD[String] = lines.filter(line => {
      val year = line.split(" ")(0).toInt
      year == 2015
    })

    // 将购买的东西作为键，统计2015年出现的次数
    val counts2015: RDD[(String, Int)] = filteredLines2015.map(line => {
      val product = line.split(" ")(2)
      (product, 1)
    }).reduceByKey(_ + _)

    // 根据购买次数降序排序2015年
    val sortedCounts2015: RDD[(String, Int)] = counts2015.sortBy(_._2, ascending = false)

    // 获取2015年购买最多的东西
    val most2015: (String, Int) = sortedCounts2015.first()

    // 打印2015年结果
    println(f"2015年购买最多的东西是: ${most2015._1}")

    // 过滤出2016年的购买记录
    val filteredLines2016: RDD[String] = lines.filter(line => {
      val year = line.split(" ")(0).toInt
      year == 2016
    })

    // 将购买的东西作为键，统计2016年出现的次数
    val counts2016: RDD[(String, Int)] = filteredLines2016.map(line => {
      val product = line.split(" ")(2)
      (product, 1)
    }).reduceByKey(_ + _)

    // 根据购买次数降序排序2016年
    val sortedCounts2016: RDD[(String, Int)] = counts2016.sortBy(_._2, ascending = false)

    // 获取2016年购买最多的东西
    val most2016: (String, Int) = sortedCounts2016.first()

    // 打印2016年结果
    println(f"2016年购买最多的东西是: ${most2016._1}")


    // TODO 关闭连接
    sc.stop()
  }
}