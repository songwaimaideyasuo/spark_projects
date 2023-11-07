package buider

import org.apache.spark.{SparkConf, SparkContext}

object rdd_2 {

  def main(args: Array[String]): Unit = {
    // spark在默认情况下，从配置对象中获取配置参数：spark.default.parallelism
    // 如果获取不到，那么使用totalCores属性，这个属性取值为当前运行环境的最大可用核数
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    sparkConf.set("spark.default.parallelism", "2")
    val sc = new SparkContext(sparkConf)
    one(sc)
    tow(sc)
    three(sc)
    //关闭资源
    sc.stop()
  }

  //（1）读取1.txt文件,并指定最小分区数量为3
  private def one(sc: SparkContext): Unit = {
    //textFile方法可以通过第二个参数指定最小分区数量.
    val rdd = sc.textFile("datas/1.txt", 3)
    // 将处理的数据保存成分区文件
    rdd.collect().foreach(println)
  }
  //（2）读取1开头的文件
  private def tow(sc: SparkContext): Unit = {
    //textFile方法 path路径还可以使用通配符 *
    val rdd = sc.textFile("datas/*1.txt")
    rdd.collect().foreach(println)
  }
    //（3）读取datas文件夹下的所有文件
  private def three(sc: SparkContext): Unit = {
    //textFile方法 path路径可以是文件的具体路径，也可以目录名称
    val rdd = sc.textFile("datas/")
    rdd.collect().foreach(println)
  }
}
