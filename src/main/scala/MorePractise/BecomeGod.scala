package MorePractise

import akka.actor.{Actor, ActorSystem, Props}

object BecomeGod extends App{


  class God extends Actor {
    override def receive: Receive = {
      case msg:String => println(msg)
        context.become(integer)

    }
    def integer:Receive = {
      case msg :Int => println(msg)
        context.unbecome()
    }
  }


  val system=ActorSystem("aa")
  val god=system.actorOf(Props[God],"god")

  god ! "aji"
  god ! 23
  god ! 23

}
