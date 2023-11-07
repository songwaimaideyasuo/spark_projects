package _04

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @Author wangf
 * @Description TODO
 * @Date 2023/4/21 8:34
 */
object Three {
  def main(args: Array[String]): Unit = {

    //创建sparkSQL运行环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val ss = SparkSession.builder().config(sparkConf).getOrCreate()
    import ss.implicits._
    //执行逻辑操作
    val df = ss.read.json("datas/user.json")
    //4.3.1查询user.json全部信息
    df.show()

    //4.3.2查看"name"列数据以及"age+2"数据
    df.select("name", "age" + 2).show()

    //4.3.3查看"age"大于"21"的数据
    df.filter($"age" > 21).show()

    // 关闭环境
    ss.close()
  }

}
