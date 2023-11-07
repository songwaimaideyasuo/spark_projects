package spark_Core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object GJ {
  def main(args:Array[String]):Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("gj")
    val sc = new SparkContext(sparkConf)
//    val rdd5 : RDD[Int] = sc.makeRDD(List(1,2,3,4))
//    val rdd6 : RDD[Int] = sc.makeRDD(List(3,4,5,6))
////        交
//    val rdd7 : RDD[Int] = rdd5.intersection(rdd6)
////        并
//    val rdd8 : RDD[Int] = rdd5.union(rdd6)
////        差
//    val rdd9 : RDD[Int] = rdd5.subtract(rdd6)
////        拉链
//    val rdd10 : RDD[(Int,Int)] = rdd5.zip(rdd6)
    val rdd = sc.makeRDD(List(1,4,2,6,3,5))
    val rdd1 = sc.makeRDD(List( ("a", 1), ("a", 2), ("c", 3)))
    val rdd2 = sc.makeRDD(List( ("a", 1), ("b", 2), ("c", 3)))
    val rdd3 = sc.makeRDD(List( ("a", 5), ("b", 6),("c", 4)))

    val reduceBykeyRDD:RDD[(String,Int)] = rdd1.reduceByKey((x:Int,y:Int)=> {x + y})

    val groupBykeyRDD:RDD[(String,Iterable[Int])] = rdd1.groupByKey()

    val joinRDD1:RDD[(String,(Int,Int))] = rdd1.join(rdd2)
    val joinRDD2:RDD[(String,(Int,Int))] = rdd2.join(rdd3)

    val reduceResult: Int = rdd.reduce((x,y)=>(x+y))
    val cnt = rdd.count()
    val firstResult: Int = rdd.first()
    val takeResult: Array[Int] = rdd.take(3)
    println()
//    joinRDD1.collect().foreach(println)
    sc.stop()
  }
}
