package dd

import scala.beans.BeanProperty

class Cat {
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var name: String = _
}


object CatTest {
  def main(args: Array[String]): Unit = {
    //java style
    val cat = new Cat()
    cat.setId(1)
    cat.setName("小黑")
    println(cat.getId + "\t" + cat.getName)

    //scala style
    val cat2 = new Cat()
    cat2.id = 2
    cat2.name = "小白"
    println(cat2.id + "\t" + cat2.name)
  }
}



