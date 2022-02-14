package MorePractise

import akka.actor.{Actor, ActorSystem, Props}

object Calc extends App {

  case class Add(x: Int, y: Int)

  case class Sub(x: Int, y: Int)

  case class Mul(x: Int, y: Int)

  case class Div(x: Int, y: Int)

  class Calculator extends Actor {
    override def receive: Receive = {
      case Add(x, y) => println(s"Adding number is = ${x+y}")
      case Sub(x, y) => println(s"Adding number is = ${x-y}")
      case Mul(x, y) => println(s"Adding number is = ${x*y}")
      case Div(x, y) => println(s"Adding number is = ${x/y}")
    }
  }

  val system=ActorSystem("ActorSystem")
  val calculator=system.actorOf(Props[Calculator],"calculator")

  calculator ! Add(1,2)
  calculator ! Sub(1,2)
  calculator ! Mul(1,2)
  calculator ! Div(1,2)

  system.terminate()
}
