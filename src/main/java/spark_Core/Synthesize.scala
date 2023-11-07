package spark_Core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
//统计每种广告的点击总次数，并取前3名。
//时间戳，省份，城市，用户，广告
object Synthesize {
  def main(args:Array[String]):Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("st")
    val sc = new SparkContext(sparkConf)
    val lines:RDD[String] = sc.textFile("datas/agent.log")
    val km = lines.filter(line => (line.trim().length > 0) && (line.split(" ").length == 5))
    val i = km.map(_.split(" ")(4))
    val j = i.map(i=>(i,1))
    val sum:RDD[(String,Int)] = j.reduceByKey((x,y)=>{x+y})
    val fl = sum.sortBy(_._2,false).take(3)

    fl.foreach(println)
    sc.stop()
  }
}
