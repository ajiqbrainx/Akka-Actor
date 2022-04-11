package Practise

import akka.actor.AbstractActor.Receive
import akka.actor.{Actor, ActorSystem, Props}

object BecomAndUnbecome extends App {

  case object Method

  class BecomDemo extends Actor {
    override def receive: Receive = {

      case true => context.become(isStateTrue)
      case false => context.become(isStateFalse)
      case Method =>

    }


    def isStateTrue: Receive = {
      case msg: String => println(s" String $msg")
//      case num:Int=>println(s"Number is $num")
      case false => context.become(isStateFalse)

    }

    def isStateFalse: Receive = {
      case msg: Int => println(s" Int $msg")
      case true => context.become(isStateTrue)
    }
  }


  val system = ActorSystem("HelloActor")
  val actor = system.actorOf(Props[BecomDemo])
//  actor ! true
////  actor ! " hello welcome all to garigalan magic show"
//  actor ! 34

  actor ! false
  actor ! 322
  actor ! "kljb"






}