package Practise

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object VishuRevise extends App{

  case object Reply
  case class Ref(x:ActorRef)
  class Tom extends Actor {
    override def receive: Receive ={
      case x:String => println("Hi da Sam")

    }
  }
  class Sam extends Actor {
    override def receive: Receive = {
      case Ref(ref) => ref ! "HiTom"
    }
  }

  val system=ActorSystem("Aaa")
  val tom=system.actorOf(Props[Tom],"tom")
  val sam=system.actorOf(Props[Sam],"sam")

  sam ! Ref(tom)
}
