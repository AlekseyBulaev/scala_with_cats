
object PrintableLibrary {

  trait Printable[A] {
    def format(value: A): String
  }

  final case class Cat(name: String, age: Int, color: String)

  def main(in: Array[String]): Unit = {
    import PrintableSyntax._
    import  PrintableInstances._
    Cat("Ammu", 10, "red").print
  }
}

object Printable {
  import PrintableLibrary.Printable

  def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)

  def print[A](value: A)(implicit printable: Printable[A]): Unit = println(format(value))
}

object PrintableSyntax {
  import PrintableLibrary.Printable

  implicit class PrintableOps[A](value: A) {
    def format(implicit print: Printable[A]): String = print.format(value)

    def print(implicit print: Printable[A]): Unit = println(format(print))
  }
}

object PrintableInstances {
  import PrintableLibrary._

  implicit val printableString: Printable[String] = new Printable[String] {
    override def format(in: String): String = in
  }

  implicit val printableInt: Printable[Int] = new Printable[Int] {
    override def format(in: Int): String = in.toString
  }

  implicit val printableCat: Printable[Cat] = new Printable[Cat] {
    override def format(cat: Cat): String =
      s"${Printable.format(cat.name)} is a ${Printable.format(cat.age)} year-old ${Printable.format(cat.color)} cat."
  }
}