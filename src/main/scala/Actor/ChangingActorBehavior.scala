package Actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import _root_.Actor.ChangingActorBehavior.Puppy.Happy

object ChangingActorBehavior extends App {

  object Puppy {
    case object Accept

    case object Reject

    val Happy = "happy"
    val Sad = "sad"
  }

  class Puppy extends Actor {

    import Puppy._
    import Mom._

    var state = Happy

    override def receive: Receive = {
      case Food(VEG) => state = Sad
      case Food(CHOCO) => state = Happy
      case Ask(msg) => {
        if (state == Happy) sender() ! Accept
        else sender() ! Reject
      }
    }
  }

  object Mom {
    case class MomStart(kidref: ActorRef)

    case class Food(food: String)

    case class Ask(msg: String)

    val VEG = "veg"
    val CHOCO = "choco"
  }

  class Mom extends Actor {

    import Mom._
    import Puppy._

    override def receive: Receive = {
      case MomStart(kidref) =>
//        kidref ! Food(VEG)
//        kidref ! Food(CHOCO)
        kidref ! Ask("Do you want")
      case Accept => println("mom happy")
      case Reject => println("mom sad")

    }
  }

  class RR extends Actor {

    import Mom._
    import Puppy._

    override def receive: Receive = happyRecieve

    def happyRecieve: Receive = {
      case Food(VEG) => context.become(sadRecieve)
      case Food(CHOCO) =>
      case Ask(_) => sender() ! Accept
    }

    def sadRecieve: Receive = {
      case Food(VEG) =>
      case Food(CHOCO) => context.become(happyRecieve)
      case Ask(_) => sender() ! Reject
    }


  }

  import Mom._

  val system = ActorSystem("ActorSystem")
  val puppy = system.actorOf(Props[Puppy], "puppy")
  val mom = system.actorOf(Props[Mom], "mom")
//  val rr=system.actorOf(Props[RR],"RR")



  mom ! MomStart(puppy)
//  mom ! MomStart(rr)

  system.terminate()


}
