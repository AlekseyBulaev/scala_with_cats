import cats.Monoid
import cats.instances.int._
import cats.syntax.semigroup._
object SuperAdder {
  def add(items: List[Int]): Int = {
    items.foldLeft(Monoid[Int].empty)(_ |+| _)
  }

  def add[A: Monoid](items: List[A]): A = {
    items.foldLeft(Monoid[A].empty)(_ |+| _ )
  }

  case class Order(totalCost: Double, quantity: Double)

  implicit val monoidOrder: Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(0, 0)

    override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
  }
  def main(in: Array[String]): Unit = {
    add(List(Order(1, 1), Order(2, 2)))
  }
}
