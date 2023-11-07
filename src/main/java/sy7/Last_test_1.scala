package sy7

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Last_test_1 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    //sparkConf.set("spark.default.parallelism", "3")
    val sc = new SparkContext(sparkConf)
    //去除重复数据
    val rdd = sc.makeRDD(List(1,2,3,4,5,6,7,8,9,1,2,3,4,5,6))
    val rdd1: RDD[Int] = rdd.distinct()
    rdd1.collect().foreach(println)
    println("+++++++++++++++++++++++")
    //过滤掉奇数。
    val filterRDD: RDD[Int] = rdd.filter(num=>num%2!=1)
    filterRDD.collect().foreach(println)
    println("+++++++++++++++++++++++")
    //将偶数数据乘以2。
    def mapFunction(num: Int): Int = {num * 2}
    val mapRDD: RDD[Int] = filterRDD.map(mapFunction)
    mapRDD.collect().foreach(println)
    sc.stop()
  }
}
