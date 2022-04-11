package MorePractise

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

import scala.io.Source

object SourceRead extends App {
  case object FileReadMacha

  class Aji extends Actor with ActorLogging {
    var file: Source = null

    override def receive: Receive = {
      case FileReadMacha => if(file ==null) {
        file = Source.fromFile("src/main/resources/testboot/jill.txt")
        log.info(file.getLines().toList.toString())
      }
    }
  }

  val system=ActorSystem("Aa")
  val aji=system.actorOf(Props[Aji],"aji")

  aji ! FileReadMacha
}
