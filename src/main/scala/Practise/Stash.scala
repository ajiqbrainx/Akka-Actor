package Practise

import akka.actor.{Actor, ActorSystem, Props, Stash, SupervisorStrategy}

object Stash extends App {

  class Aji extends Actor with Stash {
    override def receive: Receive = {
      case "childCreate" =>
        val childactor=context.actorOf(Props[Aji],"mychild")
        childactor ! "Hi macha"
      case "stashThis" => println("I got a message")
        stash()
      case "change Handler Now" => println("I got it ")
        unstashAll()
      case "change" => context.become(changing)
    }

    def changing: Receive = {
      case mesg =>println(s"I got it $mesg")
    }

    override def supervisorStrategy: SupervisorStrategy = super.supervisorStrategy

  }

  val system=ActorSystem("system")
  val aji=system.actorOf(Props[Aji],"Aji")

  aji ! "Hi macha"
}
