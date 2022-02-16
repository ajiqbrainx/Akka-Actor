package Practise

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object Sender extends App {

  case class Call(re: ActorRef)

  class Sender1 extends Actor {
    override def receive: Receive = {
      case Call(re) => re ! "Ajith"
      case "Ajith"=> println("get")
        sender() ! "Aji"
      case msg:String=>println(s"It is getting from $msg")
    }
  }

  val system = ActorSystem("Aa")
  val send = system.actorOf(Props[Sender1], "send")
  val aji = system.actorOf(Props[Sender1], "aji")

  send ! Call(aji)
}
