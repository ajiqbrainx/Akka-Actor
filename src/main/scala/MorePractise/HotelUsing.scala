package MorePractise

import akka.actor.{Actor, ActorSystem, Props}

object HotelUsing extends App{

  case class Customer(order:String)
  case class Order(food:String)
  val Pizza="pizza"
  val Burger="burger"

class Hotel extends Actor {
  override def receive: Receive = {
    case Customer(order) => println(s"Customer Ordering is $order")
      sender() ! context.become(server(order))
  }
  def server(food:String):Receive={
    case _ =>food match {
      case x if food == Pizza => println(s"customer ordering is the $x")
        context.become(kitchen(Pizza))
      case x if food == Burger =>println(s"customer ordering is $x")
        context.become(kitchen(Burger))
    }
      def kitchen(food1:String):Receive={
        case food => food1 match {
          case x if food1 == Pizza => println("Its avialable")
          case x if food1 == Burger =>println("Its not Available")
        }
      }
  }
}


  val system=ActorSystem("system")
  val hotel=system.actorOf(Props[Hotel],"hotel")

  hotel ! Customer(Pizza)
//  hotel ! Order(Pizza)
//  hotel !
}
