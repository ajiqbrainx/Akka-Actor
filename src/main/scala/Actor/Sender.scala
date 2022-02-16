package Actor

import akka.actor.{Actor, ActorSystem, Props}

object Sender extends App {

  class Simple extends Actor {
    override def receive: Receive = {
      case msg: String => println(s"I got message is $msg")
      case num: Int => println(s"I got number is $num")
      case _:Double => sender() ! "HI"
    }
  }

  val system = ActorSystem("Ac")
  val simple = system.actorOf(Props[Simple], "simple")

  simple ! "Aji"
  simple ! 23
  simple ! 23.2

}
