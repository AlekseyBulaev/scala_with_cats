package chapter2


object CatsEquality {
  final case class Cat(name: String, age: Int, color: String)

  import cats.Eq
  import cats.instances.int._
  import cats.instances.string._
  import cats.syntax.eq._

  implicit val catEq: Eq[Cat] = Eq.instance[Cat]((cat1, cat2) => {
    cat1.name === cat2.name && cat1.age === cat2.age && cat1.color === cat2.color
  })

  import cats.instances.option._

  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")
  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]
  println(cat1 === cat2)
  println(optionCat1 === optionCat2)
}
