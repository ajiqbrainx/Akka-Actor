package Practise

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object TeachStudent extends App {

  case class Call(re: ActorRef)

  case class Name(name: String)

  case object Present

  case object Absent

  val Aji = "Aji"
  val Abi = "Abi"

  class Teacher extends Actor {
    override def receive: Receive = {
      case Call(res) =>
        res ! Name(Aji)
        res ! Name(Abi)
      case Present => println("He is present")
      case Absent => println("He is absent")
    }
  }

  class Student extends Actor {
    override def receive: Receive = {
      case Name(Aji) => sender() ! Present
      case Name(Abi) => sender() ! Absent
    }
  }

  val system = ActorSystem("ss")
  val teacher=system.actorOf(Props[Teacher],"teacher")
  val student=system.actorOf(Props[Student],"student")

  teacher ! Call(student)


}
