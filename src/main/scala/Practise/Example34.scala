package Practise

import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}

object Example34 extends App{
  case object Odd
  case object Even
class Parent extends Actor{
  var count =5
  override def receive: Receive = {
    case Odd=>println(s" $count")
    if(count>0) {
      count -= 1
      sender ! Even
    }else{
        sender ! PoisonPill
      }
    }
  }
  class Two(Parent:ActorRef)extends Actor{
    override def receive: Receive = {
      case Even => println("THE VALUE ARE")
        Parent ! Odd
    }
  }
  val ak=ActorSystem("SD")
  val As=ak.actorOf(Props[Parent],"uji")
  val As1=ak.actorOf(Props(classOf[Two],As),"ak")
  As1 ! Even
}

