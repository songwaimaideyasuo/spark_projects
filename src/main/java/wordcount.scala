import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object wordcount {
  def main(args: Array[String]): Unit = {
    //建立spark链接
    val sparConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(sparConf)

    //执行业务逻辑
    //读取文件，获取一行一行的数据
    val lines: RDD[String] = sc.textFile("datas/word.txt")
    //将一行数据切分成一个一个的单词
    val word: RDD[String] = lines.flatMap(_.split(" "))
    //将数据根据单词进行转换，便于统计,word=>(word,1)
    val wordToOne: RDD[(String, Int)] = word.map(word => (word, 1))
    //将转换的数据进行分组聚合，相同的key的value进行累加,(word,1)=>(word,sum)
    val wordToSum: RDD[(String, Int)] = wordToOne.reduceByKey((x, y) => {
      x + y
    })

    //将统计的结果采集到控制台打印出来
    val result: Array[(String, Int)] = wordToSum.collect()
    result.foreach(println)
    //关闭资源
    sc.stop()
  }
}
