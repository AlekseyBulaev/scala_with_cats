package chapter4

import cats.data.Reader
import cats.syntax.applicative._

object CatsReader {
  final case class Db(usernames: Map[Int, String], passwords: Map[String, String])

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] = Reader(_.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(_.passwords.get(username).contains(password))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =  for {
    userName <- findUsername(userId)
    result <- userName.map( name => checkPassword(name, password)).getOrElse(false.pure[DbReader])
  } yield result

  def main(in: Array[String]): Unit = {
    val users = Map(
      1 -> "dade",
      2 -> "kate",
      3 -> "margo"
    )

    val passwords = Map(
      "dade" -> "zerocool",
      "kate" -> "acidburn",
      "margo" -> "secret"
    )
    val db = Db(users, passwords)
    checkLogin(1, "zerocool").run(db)
    // res7: cats.package.Id[Boolean] = true
    checkLogin(4, "davinci").run(db)
    // res8: cats.package.Id[Boolean] = false
  }
}
