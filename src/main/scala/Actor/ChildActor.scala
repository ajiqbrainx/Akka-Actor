package Actor

import akka.actor.{Actor, ActorSystem, Props}

object ChildActor extends App{

  class Parent extends Actor {


    override def receive: Receive = {
      case msg:String=>println(s"Your message is $msg ${self.path.name}")

    }
  }
class Child extends Actor {
  val createactor=context.actorOf(Props[Parent],"Ajithparent1")
  createactor ! "hi"
  override def receive: Receive = {
    case msg:String =>println(s"Your message is $msg ${self.path.name}")
  }
}

  val system=ActorSystem("ActorSystem")
  val parent=system.actorOf(Props[Parent],"Ajithparent")
  parent ! "hi"
  val child=system.actorOf(Props[Child],"Ajithout")
  child ! "helo"

  system.terminate()
}
