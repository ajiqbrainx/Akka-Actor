package FaultTolerance

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.pattern.{Backoff, BackoffSupervisor}

import scala.concurrent.duration._
import java.io.File
import scala.io.Source
import scala.language.postfixOps

import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._

object Source1 extends App {
  case object FileRead

  class Aji extends Actor with ActorLogging {

    override def preStart(): Unit = log.info("I m staring Prestart")

    override def postStop(): Unit = log.warning("I m Stopping ")

    override def preRestart(reason: Throwable, message: Option[Any]): Unit = log.info("preRestart is running ")

    var data: Source = null

    override def receive: Receive = {
      case FileRead => if (data == null)
        data = Source.fromFile(new File("src/main/resources/testboot/aji.txt"))
        log.info(s"I getting the : => ${data.getLines().toList}")

    }
  }

  val system = ActorSystem("Aji")
  //  val aji =system.actorOf(Props[Aji],"aji")
  //
  //  aji ! FileRead


  val backoffSupervisor = BackoffSupervisor.props(Backoff.onFailure(
    Props[Aji],
    "aji",
    3 seconds,
    30 seconds,
    0.2
  ))
  //val simple =system.actorOf(backoffSupervisor,"simple")
  //  simple ! FileRead


  val backoffSupervisor1 = BackoffSupervisor.props(Backoff.onStop(
    Props[Aji],
    "aji",
    3 seconds,
    30 seconds,
    0.2
  ).withSupervisorStrategy(
    OneForOneStrategy() {
      case _ => Stop
    })
  )


  val simple1=system.actorOf(backoffSupervisor1)

  simple1 ! FileRead
}
