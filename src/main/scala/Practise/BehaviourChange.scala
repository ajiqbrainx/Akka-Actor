package Practise

import akka.actor.{Actor, ActorSystem, Props}

object BehaviourChange extends App {
  object Son {
    case object Happy11

    case object Sad11

    val HAPPY = "happy"
    val SAD = "sad"
  }

  class Son extends Actor {

    import Dad._
    import Son._

    var state = HAPPY

    override def receive: Receive = {
      case Food(VEG) => state = SAD
      case Food(NONVEG) => state = HAPPY
      case Ask(msg) =>msg match {
        case x if (state==HAPPY) =>sender() ! Happy11
        case _=>sender() ! Sad11
      }
    }
  }

  object Dad {
    case class Food(food: String)

    case class Ask(msg: String)

    val VEG = "veg"
    val NONVEG = "nonveg"
  }

  class Dad extends Actor {
    import Son._
    import Dad._
    override def receive: Receive = {
      case SAD =>println("Son is sad")
      case HAPPY=>println("Son is happy")
      case Happy11=>println("Son is happy to his dish")
      case Sad11=>println("Son is sad to his dish")
    }
  }
  import Dad._
  import Son._
  val system=ActorSystem("ActorSystem")
  val dad=system.actorOf(Props[Son],"dad" )

  dad ! Food(VEG)
  dad ! Ask("how are you")


}