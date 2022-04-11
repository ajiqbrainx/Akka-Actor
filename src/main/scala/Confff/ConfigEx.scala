//package Confff
//
////import akka.actor.ActorSystem
//
//import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
//import com.typesafe.config.ConfigFactory
//
//object ConfigEx extends App {
//
//  class Aji extends Actor with ActorLogging {
//    override def receive: Receive = {
//      case msg => log.info(msg.toString)
//    }
//  }
//
//  val configString =
//    """
//      |akka{
//      |  loglevel="DEBUG"      //In case (Debug or Info or Error) it works on Debugging
//      |
//      |}
//      |""".stripMargin
//
//  val config = ConfigFactory.parseString(configString) // First Step
//  val system = ActorSystem("ConfigSetup", config)
//  val aji =system.actorOf(Props[Aji],"aji")
//
//  aji ! "Hi macha"
//
//  val default=ActorSystem("Default")
//  val actor1=default.actorOf(Props[Aji],"actor1")
//  actor1 ! "HI macha"
//
//
//  val specila
//}
