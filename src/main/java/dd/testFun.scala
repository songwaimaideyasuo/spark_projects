package dd

/**
 * 同包调用其他类
 * def functionName ([参数列表]) : [return type]
 *
 */
object testFun{
  def main(args: Array[String]): Unit = {
    val a = 4
    var n= trueOrFllse(a > 2 )
    println(n)
    // 此处为函数体，并在方法体内调用方法
    val f = (x: Int) => trueOrFllse(1==1) + x + 3
    // 调用函数
    println(f(2))


  }
  // 此处为方法体
  def trueOrFllse(scan:Boolean):String ={
    if(scan){
      return "二大爷"
    }
    return "三大爷"
  }

}



