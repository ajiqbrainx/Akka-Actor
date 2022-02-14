package Practise

import MorePractise.ActorRef.Aji
import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import java.util.Spliterators.AbstractIntSpliterator

object Arun extends App {


  case class Call(call: ActorRef)

  case object Aji

  case object Abi

  case object Present

  case object Absent
  case object Akil

  class Teacher extends Actor {
    override def receive: Receive = {
      case Call(re) =>
        re ! Aji
        re ! Abi
      case Present => println("Present is ")
      case Absent => println("Absent is ")
    }
  }

  class Student extends Actor {
    override def receive: Receive = {
      case Aji => sender() ! Present
      case Abi => sender() ! Absent
      case Akil => sender() ! Present
    }
  }

  val system=ActorSystem("Actors")
  val teacher=system.actorOf(Props[Teacher],"teacher")
  val student=system.actorOf(Props[Student],"Student")

  teacher ! Call(student)

}
