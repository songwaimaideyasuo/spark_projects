package buider

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object rdd_1 {
  def main(args: Array[String]): Unit = {
    // spark在默认情况下，从配置对象中获取配置参数：spark.default.parallelism
    // 如果获取不到，那么使用totalCores属性，这个属性取值为当前运行环境的最大可用核数
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    sparkConf.set("spark.default.parallelism", "2")
    val sc = new SparkContext(sparkConf)
    // makeRDD方法可以传递第二个参数，这个参数表示分区的数量
    val rdd = sc.makeRDD(Seq[String]("王家骏", "2020402116"),3)
    // 将处理的数据保存成分区文件
    //rdd.saveAsTextFile("datas/03/out/rdd_Memory_output")
    rdd.foreach(println)
  }

}
