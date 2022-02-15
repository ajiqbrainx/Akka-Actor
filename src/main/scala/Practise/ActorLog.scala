package Practise

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

object ActorLog extends App{

  class Macha extends Actor with ActorLogging{
    override def receive: Receive = {
      case msg =>log.info(msg.toString)
    }
  }

  val system=ActorSystem("System")

  val log=system.actorOf(Props[Macha],"log")

  log ! 12

}
