import cats.Show
import cats.syntax.show._
import cats.Show._
object PrintableShow {
  final case class Cat(name: String, age: Int, color: String)
  implicit val catsShow: Show[Cat] = new Show[Cat] {
    override def show(t: Cat): String =
      s"${t.name.show} is a ${t.age.show} year-old ${t.color.show} cat."
  }
  def main(in: Array[String]): Unit = {
    print(Cat("Lollypop", 10, "black").show)
  }
}
