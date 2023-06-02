package chapter4
import cats.data.{Writer, WriterT}
import cats.implicits._

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits._

object CatsWriter {
  def slowly[A](body: => A): A = try body finally Thread.sleep(100)

  type Logged[A] = Writer[Vector[String], A]

  42.pure[Logged]
  def factorial(n: Int): Logged[Int] = for {
    ans <- if(n == 0) {
      1.pure[Logged]
    } else {
      slowly(factorial(n - 1).map(_ * n))
    }
    _   <- Vector(s"fact $n $ans").tell
  } yield ans

  def main(in: Array[String]): Unit = {
    Await.result(Future.sequence(Vector(
      Future(factorial(5)),
      Future(factorial(5))
    )).map(_.map(_.written)), 5.seconds)
  }
}
