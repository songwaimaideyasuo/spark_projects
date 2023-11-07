package dd

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object two {
  
  def main(args: Array[String]): Unit = {
    // 创建SparkSession对象
    val spark = SparkSession.builder()
      .appName("DataFrame Example")
      .master("local")
      .getOrCreate()

    // 读取数据文件并创建DataFrame
    val df = spark.read.format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load("data.csv")

    // 只查询名字这一列的数据
    val nameDF = df.select("Name")
    nameDF.show()

    // 筛选出age大于21并且mark大于80的人
    val filterDF = df.filter(col("Age") > 21 && col("Mark") > 80)
    filterDF.show()

    // 自定义函数：将mark这一列的数据全部加5
    val addFive = udf((value: Int) => value + 5)
    val resultDF = df.withColumn("Mark", addFive(col("Mark")))

    // 将结果保存为json文件格式，如果文件存在则覆盖
    resultDF.write.mode("overwrite").json("result.json")

    // 关闭SparkSession
    spark.stop()

  }
}
