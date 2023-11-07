package sy7

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
object Last_test_2 {
  def main(args: Array[String]): Unit = {
    //创建sparkSQL运行环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val ss = SparkSession.builder().config(sparkConf).getOrCreate()
    //执行逻辑操作
    //读取 JSON 文件创建DataFrame
    val df = ss.read.json("src/main/java/sy7/uer.json")
    //对 DataFrame 创建一个临时表
    df.createOrReplaceTempView("user")
    //只查询名字这一列的数据。
    ss.sql("select name from user").show()
    println("++++++++++++++++")
    //筛选出age大于21并且mark大于80的人。
    ss.sql("select * from user where age>20 and mark>80").show()
    println("++++++++++++++++")
    //通过自定义函数将mark这一列的数据全部加5，并将结果保存为json文件格式，保存过程中，如果文件存在则覆盖。
    ss.udf.register("addmark",(mark:Int)=>{mark + 5})
    ss.sql("select addmark(mark) from user").show()
    df.write.mode("Overwrite").json("src/main/java/sy7/jack")
    //关闭环境
    ss.close()
  }
}
