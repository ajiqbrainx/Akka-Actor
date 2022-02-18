package MorePractise

import akka.actor.{Actor, ActorSystem, Props}

object CaseBecome extends App {

  case object Inccc

  case object Inttt
  case object Print
  case object SS

  class Aji extends Actor {
    override def receive: Receive = {
      case SS=>println("ssss")
        context.become(change(0))
    }
    def change(x: Int): Receive = {
      case Inttt =>context.become(change(x+1))

      case Print =>println(x)
    }
  }

  val system = ActorSystem("ss")
  val aji =system.actorOf(Props[Aji],"aji")

  aji ! SS
  aji ! Inttt
  aji ! Inttt
  aji ! Print
}
