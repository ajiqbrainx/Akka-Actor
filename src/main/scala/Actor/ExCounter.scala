package Actor

import akka.actor.{Actor, ActorSystem, Props}
import _root_.Actor.ExCounter.Counter.Increase

object ExCounter extends App {
  object Counter {

    case object Increase

    case object Decrease

    case object Print
  }

  class Counter extends Actor {

    import Counter._

    var counter = 0

    override def receive: Receive = {
      case Increase => counter += 1
      case Decrease => counter -= 1
      case Print => println(s"Counter the $counter")
    }
  }

  val system = ActorSystem("System")
  val counter = system.actorOf(Props[Counter], "Counter")

  import Counter._

  (1 to 5).foreach(_ => counter ! Increase)
  (1 to 2).foreach(_ => counter ! Decrease)
  counter ! Print
}
