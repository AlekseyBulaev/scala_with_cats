package chapter3

import cats.Functor
import chapter3.FunctorTree.Tree.{branch, leaf}

object FunctorTree {
  sealed trait Tree[+A]
  final case class Branch[A](left: Tree[A], right: Tree[A])
    extends Tree[A]
  final case class Leaf[A](value: A) extends Tree[A]
  object Tree {
    def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
      Branch(left, right)
    def leaf[A](value: A): Tree[A] =
      Leaf(value)
  }
  implicit def functorTree: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case Branch(left, right) => branch(map(left)(f), map(right)(f))
      case Leaf(value) => leaf(f(value))
    }
  }

}
