package Practise

import akka.actor.Actor

object Sender extends App {

  object Teacher {
    case class Attedance(name: String)
  }

  class Teacher extends Actor {
    import Student._
    import Teacher._
    override def receive: Receive ={
      case Present(msg) =>println(s"$msg Present Miss ")
      case Absent(msg)=>println(s"$msg Absent Miss")
    }
  }

  object Student {
    case class Present(msg: String)

    case class Absent(msg: String)
  }

  class Student extends Actor {

    import Student._
    import Teacher._

    override def receive: Receive = {
      case Present(msg) =>
    }
  }


}
