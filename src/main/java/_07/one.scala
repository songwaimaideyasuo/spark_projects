package _07

import org.apache.spark.{SparkConf, SparkContext}
/**
 * @Author wangf
 * @Description TODO
 * @Date 2023/6/9 8:19
 */
object one {
  def main(args: Array[String]): Unit = {
    // TODO 准备环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    // TODO 创建RDD
    val data = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6)
    val rdd = sc.makeRDD(data)
    // TODO 执行业务逻辑
    // 过滤掉重复的数据
    val distinctRdd = rdd.distinct()
    distinctRdd.collect().foreach(println)
    println("=====================================")
    // 过滤掉奇数
    val evenRdd = distinctRdd.filter(x => x % 2 == 0)
    evenRdd.collect().foreach(println)
    println("=====================================")
    // 将偶数数据乘以2
    val resultRdd = evenRdd.map(x => x * 2)
    resultRdd.collect().foreach(println)
    // TODO 关闭环境
    sc.stop()
  }
}
