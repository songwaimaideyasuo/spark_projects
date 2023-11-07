package operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object rdd_4 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    val rdd2: RDD[Int] = sc.makeRDD(List(3, 4, 5, 6))

    // 交集 : 【3，4】
    val rdd3: RDD[Int] = rdd1.intersection(rdd2)
    rdd3.foreach(println)
    // 并集 : 【1，2，3，4，3，4，5，6】
    val rdd4: RDD[Int] = rdd1.union(rdd2)
    rdd4.foreach(println)
    // 差集 : 【1，2】
    val rdd5: RDD[Int] = rdd1.subtract(rdd2)
    rdd5.foreach(println)
    // 拉链 : 【(1,3),(2,4),(3,5),(4,6)】
    val rdd6: RDD[(Int, Int)] = rdd1.zip(rdd2)
    rdd6.foreach(println)

    sc.stop()
  }

}
