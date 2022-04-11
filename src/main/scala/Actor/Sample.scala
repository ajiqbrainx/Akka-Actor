package Actor


import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

object Sample extends  App  {
  class SampleDemo extends Actor with ActorLogging{

    val child = system.actorOf(Props[SampleDemo1], "child")
    override def receive: Receive = {
      case msg => println(s"hello getting msg $msg")
//        context.stop(actor)

        child ! "Aji"
    }

    override def preStart(): Unit = log.info("Starting !!!!!")

    override def postStop(): Unit = log.info("Stopping !!!!!")

  }

class SampleDemo1 extends  Actor{
  override def receive: Receive ={
    case msg:String=> println(msg)
  }
}

  val system = ActorSystem("ActorSystem")
  val actor = system.actorOf(Props[SampleDemo], "Parent")
  actor ! "Welcome to Garikalan magic show !!!"


//  child ! "sss"
}
