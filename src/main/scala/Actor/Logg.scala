package Actor

import akka.actor.TypedActor.context
import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

object Logg extends App{

  class Log1 extends Actor with ActorLogging{
    override def receive: Receive ={
      case msg => log.info(msg.toString)
    }
  }


  val system=ActorSystem("ActorSystem")
  val log1=system.actorOf(Props[Log1],"log1")
  val actorSelection=context.actorSelection("/user/log1")

  log1 ! 12
  log1 ! "Ajith"
//  actorSelection !

}
