package Infrastructure

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

import scala.concurrent.duration._
import scala.language.postfixOps

object Shedule extends App {

  class Aji extends Actor with ActorLogging {
    override def receive: Receive = {
      case x => log.info(x.toString + "Dude....")
    }
  }

  val system = ActorSystem("Aji")
  import system.dispatcher
  val aji = system.actorOf(Props[Aji], "aji")

  system.log.info("Hi macha")
import system.dispatcher
  system.scheduler.scheduleOnce(2 second) {
    aji ! "Macha daiii"
  }(system.dispatcher)   // we can import it in system


  val cancel=system.scheduler.schedule(1 second,2 seconds)()


}
