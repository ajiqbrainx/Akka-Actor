package Actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object SimpleActor extends App {

  class Simple extends Actor {
    override def receive: Receive = {
      case "Hi"=> sender() ! "Hello macha"  //Replying to Hii message
      case msg: String => println(s"$self I have Received a msg $msg")
      case num: Int => println(s"I have a Received a num $num")
      case SpecialActor(msg) => println(s"I have Received a Msg $msg")
      case Some(msg) => self ! msg
      case Hi(ref)=>ref ! "Hi"
    }
  }

  val system = ActorSystem("ActorSystem1")
  val simpleactor = system.actorOf(Props[Simple], "simple1")
  simpleactor ! "Hi"
  simpleactor ! 23

  case class SpecialActor(msg: String)

  simpleactor ! SpecialActor("Jingley")

  case class Some(msg: Any)

  simpleactor ! Some("Hello")
  simpleactor ! Some(23)
//  simpleactor ! Some(23.3)

  // Actor reply message
  val aji=system.actorOf(Props[Simple],"aji")
  val abi=system.actorOf(Props[Simple],"abi")

  case class Hi(ref:ActorRef)
  aji ! Hi(abi)

  // Dead letter
  aji ! "Hi"
}
