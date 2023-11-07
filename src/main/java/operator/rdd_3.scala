package operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object rdd_3 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val rdd : RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6,7,8), 2)

    //将rdd中的数据进行（num*2+1）逻辑处理
    one(rdd)
    //对rdd中奇偶数进行分组
    two(rdd, groupFunction _)
    //只保留rdd中的偶数
    three(rdd)

    sc.stop()
  }

  private def one(rdd: RDD[Int]) = {
    val res = rdd.map(mapFunction)
    //println("one")
    res.foreach(println)

  }
  private def two(rdd: RDD[Int], groupFunction: Int => Int): Unit = {
    val groupRDD: RDD[(Int, Iterable[Int])] = rdd.groupBy(groupFunction)
    //println("two")
    groupRDD.foreach(println)
  }
  private def mapFunction(num:Int): Int = { num * 2+1 }

  private def groupFunction(num: Int) = {
    num % 2
  }

  private def three(rdd: RDD[Int]) = {
    val res = rdd.filter(n => {
      n % 2 == 0
    })
    //println("three")
    res.foreach(println)
  }
}
