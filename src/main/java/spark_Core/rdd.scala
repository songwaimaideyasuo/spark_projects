package spark_Core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object rdd {

  def main(args:Array[String]):Unit = {
  val sparkConf = new SparkConf().setMaster("local").setAppName("rdd")
  val sc = new SparkContext(sparkConf)
  val rdd1:RDD[String] = sc.makeRDD(List("谢富兴","2020402139"),3)
  val rdd2:RDD[String] = sc.textFile("spark/datas/1.txt",3)
//  val rdd3:RDD[String] = sc.textFile("spark/datas/1*.txt")
//  val rdd4:RDD[String] = sc.textFile("spark/datas")

//   val rdd:RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6,7,8),2)
         //    3.3
//  val rdd_v:RDD[Int] = rdd.map(Num=>Num*2+1)
//  val groupRDD:RDD[(Int,Iterable[Int])] = rdd.groupBy(num=>num % 2)
//    val filterPDD:RDD[Int] = rdd.filter(num=>num%2==0)
  rdd2.collect().foreach(println)
  sc.stop()
  }
}
