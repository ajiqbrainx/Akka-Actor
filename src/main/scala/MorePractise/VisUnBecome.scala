package MorePractise

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object VisUnBecome extends App{

  case class Call(ref:ActorRef)

  class Aji extends Actor {
    override def receive: Receive = {
      case Call(_) => println(self.path)
    }
  }

  val system=ActorSystem("aa")

  val aji=system.actorOf(Props[Aji],"aji")
  val abi=system.actorOf(Props[Aji],"Abi")

  aji ! Call(abi)
  abi ! Call(aji)

}