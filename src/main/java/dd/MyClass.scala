package dd

/**
 * class 和 object 都可以用来定义类型和方法。它们之间的区别在于：
class 定义一个类，可以创建该类的多个实例。而 object 定义一个单例对象，整个应用程序中只有一个该对象的实例。
class 可以被继承，而 object 不能被继承，因为它只有一个实例。
class 可以包含成员变量，而 object 不行
 */


class MyClass(val name: String) {
    def sayHello(): Unit = {
      println(s"Hello, $name!")
    }
  }


object scalaO {
  def main(args: Array[String]): Unit = {
    val obj1 = new MyClass("Alice")
    val obj2 = new MyClass("Bob")

    obj1.sayHello() // 输出 "Hello, Alice!"
    obj2.sayHello() // 输出 "Hello, Bob!"
  }
}


object MyObject {
  def sayHello(): Unit = {
    println("Hello from MyObject!")
  }

  def main(args: Array[String]): Unit = {
    MyObjects.sayHello() // 输出 "Hello from MyObject!"
  }
}






