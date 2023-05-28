object PrintableLibrary {

  trait Printable[A] {
    def format(value: A): String
  }

  object PrintableInstances {
    implicit val printableString: Printable[String] = new Printable[String] {
      override def format(in: String): String = in
    }

    implicit val printableInt: Printable[Int] = new Printable[Int] {
      override def format(in: Int): String = in.toString
    }
  }

  object Printable {
    def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)
    def print[A](value: A) (implicit printable: Printable[A]): Unit = println(format(value))
  }

  final case class Cat(name: String, age: Int, color: String)

  implicit val printableCat: Printable[Cat] = new Printable[Cat] {
    import PrintableInstances._
    override def format(cat: Cat): String =
      s"${Printable.format(cat.name)} is a ${Printable.format(cat.age)} year-old ${Printable.format(cat.color)} cat."
  }

  def main(in: Array[String]): Unit = {
    Printable.print(Cat("Ammu", 10, "red"))
  }
}
