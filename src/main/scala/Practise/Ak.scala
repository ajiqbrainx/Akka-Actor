package Practise

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

object Ak extends App {
  case object Question

  val ak = ActorSystem("gu")
  val ak1 = ak.actorOf(Props[Ak1], "sdfr")
    ak1 ! Question


  class Ak1 extends Actor with ActorLogging{
    override def receive: Receive = {
      case Question => println("My name is arun")
        aji ! 23
    }
    override def preStart(): Unit = {
      log.info("what is your name")
    }
    val aji = context.actorOf(Props[Aji], "aji")
  }

  }

class Aji extends Actor {
  override def receive: Receive = {
    case ss => println(ss)
  }
}
