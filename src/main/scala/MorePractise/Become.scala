package MorePractise

import akka.actor.{Actor, ActorSystem, Props, scala2ActorRef}

object Become extends App {

  case object Inc

  case object Dec

  case object Print

  class MainActor extends Actor {
    override def receive: Receive = count(0)

    def count(x: Int): Receive = {
      case Inc => context.become(count(x + 1))
      case Dec => context.become(count(x - 1))
      case Print => println(s"You are count is = $x")
    }
  }
val system=ActorSystem("ActorSystem")
  val mainActor=system.actorOf(Props[MainActor],"mainActor")
  mainActor ! Print
  mainActor ! Inc
  mainActor ! Inc
  mainActor ! Dec
  mainActor ! Print

}
