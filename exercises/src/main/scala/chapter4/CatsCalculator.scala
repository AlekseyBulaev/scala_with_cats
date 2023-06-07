package chapter4

object CatsCalculator {

  import cats.data.State._
  import cats.data.State

  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => operator(_ + _)
    case "-" => operator(_ - _)
    case "*" => operator(_ * _)
    case "/" => operator(_ / _)
    case num => operand(num.toInt)
  }

  def operator(f: (Int, Int) => Int): CalcState[Int] = State[List[Int], Int] {
    case b :: a :: tail =>
      val ans = f(a, b)
      (ans :: tail, ans)
    case _ => sys.error("Fail!")
  }

  def operand(num: Int): CalcState[Int] = State[List[Int], Int] { stack =>
      (num :: stack, num)
    }
}
