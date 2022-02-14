package MorePractise



import akka.actor.{Actor, ActorSystem, Props}

object Sender extends App {

  case object ReceiveAji

  case object ReceiveAbi

  class Aji extends Actor {
    override def receive: Receive = {
      case msg:String => println(s"$msg Im Aji ")
      self ! (ReceiveAbi)
      case ReceiveAji =>println("Abi is telling")
    }
  }

  //  val Aji="Aji"
  //  val Abi="Abi"

  class Abi extends Actor {
    override def receive: Receive = {
      case msg:String =>println(s"$msg Im Abi ")
      self ! ReceiveAji
      case ReceiveAbi => println("Aji is telling to you Abi")
    }
  }

  val system=ActorSystem("ActorSystem")
  val aji=system.actorOf(Props[Aji],"Aji")
  val abi=system.actorOf(Props[Abi],"Abi")


  aji ! "HI"
}
