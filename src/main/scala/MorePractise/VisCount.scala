package MorePractise

import akka.actor.{Actor, ActorSystem, Props}

//object VisCount extends App {
//  case object Increment
//
//  case object Decrement
//
//  case object Print
//
//  class Count extends Actor {
//    override def receive: Receive = count(0)
//
//    val child=
//    def count(x: Int): Receive = {
//      case Increment =>
//        context.become(count(x +1))
//      case Decrement => context.become(count(x -1 ))
//      case Print =>println(s"Your count is ${x}")
//    }
//  }
//  class  Child extends Actor{
//    override def receive: Receive = ???
//  }
//
//  val system=ActorSystem("sss")
//  val count=system.actorOf(Props[Count],"count")
//
//  count ! Increment
//  count ! 45
//
//  count ! Increment
//  count ! Decrement
//  count ! Decrement
//  count ! Decrement
//  count ! Decrement
//  count ! Decrement
//
//  count! "viusjc"
//  count ! Print



//}
