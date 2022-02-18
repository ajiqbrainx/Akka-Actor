package Actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object BecomeActor extends App {
  case class Name(x: String)

  class Aji extends Actor {
    override def receive: Receive = {
      case s: String => abi ! Name(s)
      case s:Int => println(s"Your girlfriend name is $s")
    }
    val abi = context.actorOf(Props[Abi], "abi")
  }

  class Abi extends Actor {
    override def receive: Receive = {
      case Name(s) => println(s"Your Name is $s")
        sender() ! 1
    }
  }

  val system = ActorSystem("aa")
  val aji = system.actorOf(Props[Aji], "aji")


  aji ! "Aji"
  aji ! "abi"
}
