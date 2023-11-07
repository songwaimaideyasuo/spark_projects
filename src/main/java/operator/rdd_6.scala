package operator

import org.apache.spark.{SparkConf, SparkContext}

object rdd_6 {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,4,2,6,3,5))

    //聚集RDD 中的所有元素
    val reduceResult: Int = rdd.reduce((x, y) => (x + y))
    println(reduceResult)
    //返回RDD 中元素的个数
    println(rdd.count())
    //返回RDD 中的第一个元素
    println(rdd.first())
    //返回RDD 的前 3 个元素
    rdd.take(3).foreach(println)

    sc.stop()
  }
}
