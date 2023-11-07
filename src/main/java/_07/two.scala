package _07

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf}
import org.apache.spark.sql.functions.{col, udf}
/**
 * @Author wangf
 * @Description TODO
 * @Date 2023/6/9 8:32
 */
object two {
  def main(args: Array[String]): Unit = {
    // TODO 准备环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val ss = SparkSession.builder().config(sparkConf).getOrCreate()

    // TODO 读取 json 文件创建DataFrame
    val df = ss.read.json("datas/data.json")
    //对 DataFrame 创建一个临时表
    df.createOrReplaceTempView("user")
    // TODO 执行业务逻辑
    //只查询名字这一列的数据。
    ss.sql("select name from user").show
    println("=====================================")
    //筛选出age大于21并且mark大于80的人。
    ss.sql("select * from user where age > 21 and mark > 80").show
    println("=====================================")
    //通过自定义函数将mark这一列的数据全部加5，并将结果保存为json文件格式，保存过程中，如果文件存在则覆盖。
    //使用自定义函数处理DataFrame中的mark列
    ss.udf.register("mark",(mark:Int)=>{mark+5})
    val result = ss.sql("select name,mark(mark) from user")
    result.show()
    result.write.mode("overwrite").json("datas/result")
    // TODO 关闭环境
    ss.stop()
  }
}
