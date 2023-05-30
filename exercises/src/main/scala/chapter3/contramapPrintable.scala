package chapter3

import chapter2.Printable

object contramapPrintable {
  trait Printable[A] { self =>
    def format(value: A): String
    def contramap[B](func: B => A): Printable[B] =
      new Printable[B] {
        def format(value: B): String =
          self.format(func(value))
      }
  }
  def format[A](value: A)(implicit printable: Printable[A]): String = {
    printable.format(value)
  }

  final case class Box[A](value: A)

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        s"'${value}'"
    }
  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }
  implicit def boxStringPrintable: Printable[Box[String]] = stringPrintable.contramap(x => x.value)
  implicit def boxBooleanPrintable: Printable[Box[Boolean]] = booleanPrintable.contramap(x => x.value)

  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] = p.contramap[Box[A]](_.value)
  def main(input: Array[String]): Unit = {
    format(Box("hello world"))

    format(Box(true))
//      format(Box(123))
  }
}
