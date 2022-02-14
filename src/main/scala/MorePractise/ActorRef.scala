package MorePractise

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ActorRef extends App {

  class Main extends Actor {

    override def receive: Receive = {

//      case Aji(ss) => self ! ss
//      case ss: String => println(s"${self} msg is $ss")
//      case ss: Int => println(s"${self.path}your number is $ss")
      case Refer(ref) => ref ! Receive
      case Receive => sender() ! "I got Received"
      case msg =>println( msg)
//val child=context.actorOf(Props[Child],"child")
    }
  }
  case object Receive

//  class Child extends Actor {
//    override def receive: Receive = {
//      case Receive =>println("I got Received")
//    }
//  }

  val system = ActorSystem("ActorSystem")
  val main = system.actorOf(Props[Main], "main")
  val aji = system.actorOf(Props[Main], "aji")

  case class Aji(s: Any)

  case class Refer(ref: ActorRef)

  //  main ! "macha"
  //  main ! 23
  //  main ! Aji("Hi macha")
  //  main ! Aji(12)
  main ! Refer(aji)
  aji ! Refer(main)


  //  system.terminate()
}
