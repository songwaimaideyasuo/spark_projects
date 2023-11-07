package SY3

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object rdd {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)
    val seq = Seq[String]("张三","2020402106")
    val rdd: RDD[String] = sc.makeRDD(seq)
    rdd.collect().foreach(println)

    val rdd1:RDD[Int] = sc.makeRDD(List())
    val rdd2 : RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6,7,8), 2)

    sc.stop()
  }
}


