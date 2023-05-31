package chapter4

object CatsId {
  //Implement pure, map, and flatMap for Id! What interesting discoveries do
  //you uncover about the implementation?
  import cats.Id
  def pure[A](value: A): Id[A] = value
  def map[A, B](initial: Id[A])(func: A => B): Id[B] =
    func(initial)
  def flatMap[A, B](initial: Id[A])(func: A => Id[B]): Id[B] =
    func(initial)
}
