package chapter3

object Imap {
  trait Codec[A] { self =>
    def encode(value: A): String
    def decode(value: String): A
    def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {
      override def encode(value: B): String = self.encode(enc(value))
      override def decode(value: String): B = dec(self.decode(value))
    }
  }
  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): A =
    c.decode(value)

  implicit val stringCodec: Codec[String] =
    new Codec[String] {
      def encode(value: String): String = value

      def decode(value: String): String = value
    }

  final case class Box[A](value: A)

  def main(in: Array[String]): Unit = {
    implicit val doubleCodec: Codec[Double] = stringCodec.imap(_.toDouble, _.toString)

    implicit def boxCodec[A](implicit p: Codec[A]): Codec[Box[A]] = p.imap(Box(_), _.value)

    println(encode(123.4))
    // res11: String = "123.4"
    println(decode[Double]("123.4"))
    // res12: Double = 123.4
    println(encode(Box(123.4)))
    // res13: String = "123.4"
    println(decode[Box[Double]]("123.4"))
    // res14: Box[Double] = Box(123.4)
  }
}
