package dd
// 导入SparkContext

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object one {

  def main(args: Array[String]): Unit = {
    // 创建SparkConf和SparkContext对象
    val conf = new SparkConf().setAppName("RDDExample").setMaster("local")
    val sc = new SparkContext(conf)

    // 创建包含给定数据的RDD
    val rdd = sc.parallelize(Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6))

    // 过滤掉重复的数据
    val uniqueRdd = rdd.distinct()

    // 过滤掉奇数
    val evenRdd = uniqueRdd.filter(x => x % 2 == 0)

    // 将偶数数据乘以2
    val resultRdd = evenRdd.map(x => x * 2)

    // 查看结果
    resultRdd.collect().foreach(println)

    // 关闭SparkContext
    sc.stop()

  }
}
