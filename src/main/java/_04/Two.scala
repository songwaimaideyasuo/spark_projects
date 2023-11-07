package _04

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @Author wangf
 * @Description TODO
 * @Date 2023/4/21 8:30
 */
object Two {
  def main(args: Array[String]): Unit = {
    // 创建sparkSQL运行环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val ss = SparkSession.builder().config(sparkConf).getOrCreate()
    //执行逻辑操作
    //读取 JSON 文件创建DataFrame
    val df = ss.read.json("datas/user.json")
    //对 DataFrame 创建一个临时表
    df.createOrReplaceTempView("user")
    //通过 SQL 语句实现查询全表
    ss.sql("select * from user").show()

    //4.2.1查询user.json全部信息
    ss.sql("select * from user").show()

    //4.2.2只查询名字
    ss.sql("select name from user").show()

    //4.2.3计算年龄的平均值
    ss.sql("select avg(age) from user").show()

    //关闭环境
    ss.close()
  }
}
