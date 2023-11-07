package _04


import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

case class User(name: String, age: Int)

object Four {
  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder()
      .appName("Four")
      .master("local[*]")
      .getOrCreate()
    import ss.implicits._

    val rdd1 = ss.sparkContext.makeRDD(List(1, 2, 3))

    //将RDD转换得到DataFrame
    val frame1: DataFrame = rdd1.toDF("id")
    val rdd2: RDD[(String, Int)] = ss.sparkContext.makeRDD(List(("zhangsan", 30), ("lisi", 40)))
    val frame2: DataFrame = rdd2.toDF("name", "age")
    //输出frame2
    frame2.show()

    //DataFrame 转换为 RDD
    val rdd11: RDD[Row] = frame1.rdd.map(row => row)
    rdd11.foreach(println)

    //DataFrame 转换为 DataSet
    val ds: Dataset[User] = Seq(User("zhangsan", 20), User("lisi", 21)).toDS()
    //输出ds
    ds.show()

    //关闭环境
    ss.close()
  }
}

