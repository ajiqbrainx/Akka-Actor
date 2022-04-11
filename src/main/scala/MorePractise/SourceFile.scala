package MorePractise

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

import java.io.File
import scala.io.Source


object SourceFile extends App {
  case object File1

  var data: Source = null

  class AjiFile extends Actor with ActorLogging {
    override def receive: Receive = {
      case File1 => if (data == null) {
        data = Source.fromFile(new File("src/main/resources/testboot/jilll.txt"))
        log.info(s"Read The file ${data.getLines().toList}")
      }
      else new IllegalArgumentException("SSS")

    }
  }

  val system = ActorSystem("aaa")
  val ajiFile = system.actorOf(Props[AjiFile], "ajiFile")

  ajiFile ! File1
}
