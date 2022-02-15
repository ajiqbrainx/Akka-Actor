package Actor

import akka.actor.{Actor, ActorSystem, Props}

object UnBecome extends App {


  class Become extends Actor {
    override def receive: Receive = {
      case msg:String=>println(s"The string is $msg")
        context.become(integer)
    }
    def integer:Receive={
      case msg:Int =>println(s"The Interger is $msg")
        context.unbecome()
    }
  }

  val system=ActorSystem("ss")
  val become=system.actorOf(Props[Become],"Become")

  become ! "Ajith"
  become ! 23
  become ! "aji"
}
