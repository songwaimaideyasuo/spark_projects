package spark_Core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object test {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("t")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.textFile("spark/datas/word.txt", 3)
    rdd.collect().foreach(println)
  }
}
