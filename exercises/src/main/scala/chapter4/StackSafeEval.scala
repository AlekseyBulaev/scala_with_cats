package chapter4

import cats.Eval

object StackSafeEval {
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): Eval[B] = as match {
      case head :: tail => Eval.defer(foldRight(tail, acc)(fn).map(b => fn(head, b)))
      case Nil => Eval.now(acc)
    }
  def main(in: Array[String]): Unit = {
    print(foldRight((1 to 100000).toList, 0L)(_ + _).value)
  }
}
