package Actor

import akka.actor.{Actor, ActorSystem, Props}

object ActorConstructor extends App {


  class Constructor(name: String) extends Actor {
    override def receive: Receive = {
      case _: String => println(s"Hello Your name is $name ")
      case _ => println("no value")
    }
  }

  object Constructor {
    def props(name: String) = Props(new Constructor(name))
  }

  val actorSystem = ActorSystem("Ajith")

    val cons = actorSystem.actorOf(Props(new Constructor("Ajith")))
    cons ! "Hlo"
    cons ! 9

  val cons1=actorSystem.actorOf(Constructor.props("Ajith"))
  cons1 ! "HLo"
  cons1 ! 23

  actorSystem.terminate()
}