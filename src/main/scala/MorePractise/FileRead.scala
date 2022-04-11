package MorePractise

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

import java.io.File
import scala.io.Source

object FileRead extends App {
  case object FileReadAndWrite

  class Read1 extends Actor with ActorLogging {

    var x: Source = null

    override def receive: Receive = {
      case FileReadAndWrite => if (x == null) {
        x = Source.fromFile(new File("src/main/resources/testboot/aji.txt").toString)
        log.info(s"Your getting File is == ${x.getLines().toList}")
      }
    }
  }

  val system = ActorSystem("aa")
  val read = system.actorOf(Props[Read1], "read")

  read ! FileReadAndWrite
}
