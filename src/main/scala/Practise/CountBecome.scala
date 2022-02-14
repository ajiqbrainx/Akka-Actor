package Practise

import akka.actor.{Actor, ActorSystem, Props}


object CountBecome extends App {

    case object Increment

    case object Decrement

    case object Print

  class Count extends Actor {

    override def receive: Receive = count(0)

    def count(number: Int): Receive = {
      case Increment => context.become(count(number + 1))
      println(s"Your are increment = $number")
      case Decrement => context.become(count(number - 1))
        println(s"Your are decrement = $number")
      case Print => println(s"your count number is $number ")
    }
  }


  val system=ActorSystem("Aa")
  val count=system.actorOf(Props[Count],"aa")
  count ! Increment
//  count ! Increment
//  count ! Increment
  count ! Decrement
  count ! Print
  system.terminate()
}