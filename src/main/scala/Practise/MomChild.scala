package Practise

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object MomChild extends App {
  val Veg = "Veg"
  val NonVeg = "Non veg"

  case class Momcall(food: ActorRef)
  case class Food(food:String)
  case object Happy
  case object Sad

  class Mom extends Actor {
    override def receive: Receive = {
      case Momcall(ref) =>
        ref ! Food(Veg)
        ref ! Food(NonVeg)
      case Happy => println("Mom Happy")
      case Sad => println("Mom sad")

    }
  }

  class Child extends Actor {
    override def receive: Receive = {
      case Food(Veg)=> sender() ! Happy
      case Food(NonVeg) =>sender() ! Sad
    }
  }

  val system=ActorSystem("ss")
  val mom=system.actorOf(Props[Mom],"mom")
  val child=system.actorOf(Props[Child],"chil")

  mom ! Momcall(child)

}
