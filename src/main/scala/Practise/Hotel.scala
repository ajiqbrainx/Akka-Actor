package Practise

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object Hotel extends App {

  case class CustomerOrder(ref: ActorRef)

  case object Yes

  case object No

  case class Order(order: String)

  val Pizza = "Pizza"
  val Burger = "Burger"

  case class Kitchen(a: String)

  class Customer extends Actor {
    override def receive: Receive = {
      case CustomerOrder(ref) =>
        ref ! Order(Pizza)
        ref ! Order(Burger)

      case Yes => println("Its Available ")
      case No => println("Its Not Available")
    }
  }

  class Server extends Actor {
    override def receive: Receive = {
      case Order(Pizza) => sender() ! Yes
      case Order(Burger) => sender() ! No
    }
  }

  //  class Kitchen1 extends Actor {
  //    override def receive: Receive = {
  //      case Kitchen(order) => order match {
  //        case x if Kitchen(Pizza)==Pizza => sender() ! Yes
  //        case x if Kitchen(Burger)==Burger => sender() ! No
  //      }
  //    }
  //  }

  val system = ActorSystem("Ac")

  val customer = system.actorOf(Props[Customer], "cus")
  val server = system.actorOf(Props[Server], "se")
  //  val kitchen=system.actorOf(Props[Kitchen1],"kk")


  customer ! CustomerOrder(server)
}
