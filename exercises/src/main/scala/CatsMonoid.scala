import cats.Monoid

object CatsMonoid {
import cats.Monoid._
  implicit val booleanAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val booleanOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  implicit val booleanEitherMonoid: Monoid[Boolean] = new Monoid[Boolean] {
      override def empty = false

      override def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
  }

  implicit val booleanNorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = (!x || y) && (x || !y)
  }
}
