package _04

import org.apache.spark.SparkConf
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.{DataFrame, SparkSession}

object Five{
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("UDFExample").setMaster("local[*]")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    // 读取 user.json 文件并创建 DataFrame
    val userDf: DataFrame = spark.read.json("datas/user.json")

    // 注册 UDF，并在 DataFrame 中使用
    val addAgeUdf = udf((age: Int) => age + 2)
    val resultDf = userDf.withColumn("age", addAgeUdf(userDf("age")))

    // 显示结果
    resultDf.show()

    spark.stop()
  }
}
