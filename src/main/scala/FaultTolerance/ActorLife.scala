package FaultTolerance

import akka.actor.SupervisorStrategy.stop
import akka.actor.{Actor, ActorLogging, ActorSystem, PoisonPill, Props}

object ActorLife extends App {


  case class ChildCreate(name:String)

  class Life extends Actor with ActorLogging{

    override def preStart(): Unit = log.info("I am Staring")

    override def postStop(): Unit = log.info("I am stopping")

    override def receive: Receive = {
      case ChildCreate(name) => context.actorOf(Props[Life], name)
//        context.stop(name)


       }

  }

  val system = ActorSystem("Aa")
  val life =system.actorOf(Props[Life],"life")

  life ! ChildCreate("Aji")



}
