package sy7

import org.apache.spark.api.java.JavaRDD.fromRDD
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}


object Last_test_3 {

    case class Person(thing:String, month:String, year:Int)
    def main(args: Array[String]): Unit = {

      val sparConf = new SparkConf().setMaster("local").setAppName("WordCount")
      val sc = new SparkContext(sparConf)

      val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
      val ss = SparkSession.builder().config(sparkConf).getOrCreate()

      //读取文件，获取一行一行的数据（textFile）
      val lines: RDD[String] = sc.textFile("src/main/java/sy7/qm2.txt")
      //    lines.collect().foreach(println)

      //通过对每一行数据进行 split 操作，将其转换为一个包含三个属性的 Person 对象，最后将 RDD 转换为 DataFrame 对象。
      import ss.implicits._
      val RDDPerson = lines.map(_.split(" ")).map(p => Person(p(2),p(1),p(0).trim.toInt))
      val DFPerson = RDDPerson.toDF()

      //从 DataFrame 中选取 year、month 和 thing 三列并保存到 df 中。
      val df: DataFrame = DFPerson.select($"year",$"month",$"thing")

      //将 df 注册为一个临时表 user，并使用 SparkSession 的 sql 方法执行 SQL 语句，查询年份为 2015、月份小于 7 的数据并返回 DataFrame 对象。
      df.createOrReplaceTempView("user")
      val frame: DataFrame = ss.sql("SELECT * FROM user WHERE year='2015' AND month < 7;")
      //    frame.show

      //将 DataFrame 转换成 RDD[Row] 对象，再将每个 Row 对象转换成字符串类型，最终得到一个 RDD[String] 对象。
      val line: RDD[Row] = frame.rdd
      val line1 = line.rdd.map(_.mkString(" "))
      //    line1.collect().foreach(println)

      //对 RDD[String] 对象进行 map 操作，将其转换成一个包含两个值的元组对象，元组的第一个值为物品名称（即该行数据的第三个字段），第二个值为 1
      val mapRDD= line1.map
        line => {
          val datas = line.split(" ")
          ((  datas(2) ), 1)    //索引，只取第三列‘买的东西’
        })
      //将转换结构后的数据，进行分组聚合
      val wordToSum: RDD[(String, Int)] = mapRDD.reduceByKey((x, y) => {
        x + y
      })
      //按照点击次数进行降序排序（sortBy）
      val group: RDD[(String, Int)] = wordToSum.sortBy(t => t._2,false)
      //取前一（take）
      val tup: Array[(String, Int)] = group.take(1)
      //采集打印在控制台
      print("2015年上半年 购买最多的东西： ")
      tup.foreach(println)
    }
}
