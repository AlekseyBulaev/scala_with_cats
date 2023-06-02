package chapter4

import cats.data.Reader

object CatsReader {
  final case class Db(usernames: Map[Int, String], passwords: Map[String, String])

  type DbReader[A] = Reader[Db, A]
}
