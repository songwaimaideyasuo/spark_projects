package _07

import org.apache.spark.api.java.JavaRDD.fromRDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.storage.StorageLevel


/**
 * @Author wangf
 * @Description TODO
 * @Date 2023/6/9 8:32
 */
object three {
  case class Commodity(thing:String, month:String, year:Int)
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("WordCount")
      .master("local[*]")
      .getOrCreate()
    //读取文件，获取一行一行的数据（textFile）
    val lines: RDD[String] = spark.sparkContext.textFile("datas/qm2.txt")
    import spark.implicits._
    val RDDPerson = lines.map(_.split(" ")).map(p => Commodity(p(2),p(1),p(0).trim.toInt))
    val DFPerson = RDDPerson.toDF()
    val df: DataFrame = DFPerson.select($"year",$"month",$"thing")
    df.createOrReplaceTempView("user")
    val frame: DataFrame = spark.sql("SELECT * FROM user WHERE year='2015' AND month < 7;")
    //frame.show
    val line: RDD[Row] = frame.rdd
    val line1 = line.rdd.map(_.mkString(" "))
    // line1.collect().foreach(println)
    //将原始数据进行结构的转换。方便统计（map）
    val mapRDD= line1.map(
      line => {
        val datas = line.split(" ")
        ((datas(2)), 1)    //索引，只取第三列‘买的东西’
      })
    //将转换结构后的数据，进行分组聚合
    val wordToSum: RDD[(String, Int)] = mapRDD.reduceByKey((x, y) => {
      x + y
    })
    wordToSum.persist(StorageLevel.DISK_ONLY)   //方法2 持久化数据仅保存在磁盘上
    //按照点击次数进行降序排序（sortBy）
    val group: RDD[(String, Int)] = wordToSum.sortBy(t => t._2,false)
    //取前一（take）
    val tup: Array[(String, Int)] = group.take(1)
    //采集打印在控制台
    print("2015年上半年（1-6月）购买最多的东西： ")
    tup.foreach(println)
  }
}