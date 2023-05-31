package chapter4
import cats.MonadError
import cats.syntax.applicative._

import scala.util.Try

object CatsError {

  def validateAdult[F[_]](age: Int)(implicit me: MonadError[F, Throwable]): F[Int] =
    me.ensure(age.pure[F])(new IllegalArgumentException("Age must be greater than or\nequal to 18"))(_ >= 18)

  def main(in: Array[String]): Unit = {
    println(validateAdult[Try](18))
    println(validateAdult[Try](10))
  }
}
