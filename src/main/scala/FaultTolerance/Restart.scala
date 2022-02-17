package FaultTolerance

import akka.actor.{Actor, ActorLogging, ActorSystem, PoisonPill, Props}

object Restart extends App {

  case object FailChild

  case object Fail
  case object CheckChild
  case object Check

  class Parent extends Actor with ActorLogging {
    val child = context.actorOf(Props[Child], "child")

    override def receive: Receive = {
      case FailChild => child ! Fail
      case CheckChild => child ! Check
    }
  }

  class Child extends Actor with ActorLogging {

    override def preStart(): Unit = log.info("I am Staring")

    override def postStop(): Unit = log.info("I am stopping")

    override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
      log.info(s"Hey I get $reason So I am out Now I am ok ")
    }

    override def postRestart(reason: Throwable): Unit = log.info("restarting")

    override def receive: Receive = {
      case Fail =>
        log.warning("I am getting warning ")
        throw new RuntimeException("Error")
//        PoisonPill
      case Check => log.info("I am here Macha")
    }
  }

  val system=ActorSystem("aa")
  val parent=system.actorOf(Props[Parent],"parent")

  parent ! FailChild
  parent ! CheckChild

}
