package Actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object Teacher1 extends App{
  case class Call(call:ActorRef)
  case class Name(name:String)
  case object Present
  case object Absent

  class Teacher extends Actor {
    override def receive: Receive = {
      case Call(cc)=>
        cc ! Name("Aji")
        cc ! Name("Abi")
       case Present =>println("Aji is Present")
      case Absent =>println("Abi is Absent")
    }
  }
  class Student extends Actor {
    override def receive: Receive = {
      case Name("Aji") => sender ! Present
      case Name("Abi") => sender ! Absent
    }
  }


  val system=ActorSystem("ActorSystem")
  val teacher=system.actorOf(Props[Teacher],"Teacher")
  val student=system.actorOf(Props[Student],"Student")

  teacher ! Call(student)
}
