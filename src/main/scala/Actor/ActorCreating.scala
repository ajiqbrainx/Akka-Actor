package Actor

import akka.actor.{Actor, ActorSystem, Props}

object ActorCreating extends App{

  //actor System
  val actorSystem=ActorSystem("Name")
//  println(actorSystem.name)

  // create actor and Its behaviour
  class Actor1 extends Actor{
    override def receive: Receive = {
      case message:String=>println(s"I have received a message : $message")
      case msg:Int=>println(s"I don't know you : $msg   ")
    }
  }
  // create initiated or like creating instance for Actor
  val actor=actorSystem.actorOf(Props[Actor1],"Actor11")

  //communicate to actor
  actor ! "Ajith"
actor ! 20.2

}
