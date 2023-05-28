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
}
