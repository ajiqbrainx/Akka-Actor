package Actor

import akka.actor.SupervisorStrategy.{Decider, Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorLogging, ActorSystem, AllForOneStrategy, OneForOneStrategy, PoisonPill, Props, SupervisorStrategy}

import scala.language.postfixOps



//import scala.language.postfixOps
import scala.concurrent._
import scala.concurrent.duration.DurationInt


object ExceptionHandling extends App {

  class Handling extends Actor with ActorLogging {
    override def receive: Receive = {
      case msg: Int =>println( 20 / msg)
    }

    override val supervisorStrategy: SupervisorStrategy = OneForOneStrategy(){
      case _:ArithmeticException => Stop
//      case _:NullPointerException => Restart
    }

    override def preStart(): Unit = log.info("Prestart is Starting ")

    override def postStop(): Unit = log.info("Poststop is Stopping")

    override def preRestart(reason: Throwable, message: Option[Any]): Unit = log.info(s"preRestarting is Running $reason ")

    override def postRestart(reason: Throwable): Unit = log.info(s"PostRestarting is Running $reason")
  }
val system=ActorSystem("aa")
  val hand=system.actorOf(Props[Handling],"hand")

  hand ! 0
  hand ! 2
}
