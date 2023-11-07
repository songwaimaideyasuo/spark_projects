package operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object rdd_5 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(("a", 1), ("a", 2), ("c", 3)))
    val rdd2 = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)))
    val rdd3 = sc.makeRDD(List(("a", 5), ("b", 6), ("c", 4)))

    //对rdd1进行reduceByKey聚合value的值，进行groupByKey操作
    val reduceRDD: RDD[(String, Int)] = rdd1.reduceByKey( (x:Int, y:Int) => { x + y} )
    val groupRDD: RDD[(String, Iterable[Int])] = reduceRDD.groupByKey()
    groupRDD.foreach(println)

    //对rdd2和rdd3进行join操作
    val joinRDD1: RDD[(String, (Int, Int))] = rdd2.join(rdd3)
    joinRDD1.foreach(println)

    //对rdd1和rdd2进行join操作
    val joinRDD2: RDD[(String, (Int, Int))] = rdd1.join(rdd2)
    joinRDD2.foreach(println)


    sc.stop()
  }

}
