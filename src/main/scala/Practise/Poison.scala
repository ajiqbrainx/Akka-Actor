package Practise

import akka.actor.{Actor, ActorSystem, Props}

object Poison extends App {

  case class Increment(x: Int)

  class Arun extends Actor {
    override def receive: Receive = value(5)
    def value(y:Int):Receive ={
      case Increment(x) => x match {
        case e if x > y => context.become(value(x - 1))
        println(y)
      }
    }
  }
  val system = ActorSystem("aa")
  val count1 = system.actorOf(Props[Arun], "arun")

  count1 !Increment(0)

}
