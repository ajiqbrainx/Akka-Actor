package MorePractise

import akka.actor.{Actor, ActorSystem, Props}

object Vishnu extends App {

  case class Add(a:Int,b:Int)

  case class Sub(a:Int,b:Int)

  case class Mul(a:Int,b:Int)

  case class Div(a:Int,b:Int)

  class Calc extends Actor {
    override def receive: Receive ={
      case Add(x,y) =>println(s"Adding value is ${x+y}")
      case Sub(x,y) =>println(s"Sub value is ${x-y}")
      case Mul(x,y) =>println(s"Mul value is ${x*y}")
      case Div(x,y) =>println(s"Div value is ${x/y}")
      case msg:String=>println(s"$msg")
    }
  }

  val system=ActorSystem("Actors")
  val calc=system.actorOf(Props[Calc],"Calc")

  calc ! Add(1,2)
  calc ! Sub(1,2)
  calc ! Mul(1,2)
  calc ! Div(1,2)
  calc ! "Div"

}
