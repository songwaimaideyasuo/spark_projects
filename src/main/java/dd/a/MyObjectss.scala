package dd.a

import dd.{MyClass, MyObjects}

object MyObjectss {
  def sayHello(): Unit = {
    println("Hello from MyObject!")
  }

  def main(args: Array[String]): Unit = {
      val obj1 = new MyClass("Alice")
      val obj2 = new MyClass("Bob")

      obj1.sayHello() // 输出 "Hello, Alice!"
      obj2.sayHello() // 输出 "Hello, Bob!"
      MyObjects.sayHello() // 输出 "Hello from MyObject!"
  }
}
