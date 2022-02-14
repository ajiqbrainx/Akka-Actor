package Practise

import Practise.BankPatz.Iob.Deposit
import akka.actor.{Actor, ActorSystem, Props}

object BankPatz extends App {


  object Iob {
    case class Deposit(amount: Int)

    case class Withdraw(amount: Int)

    case object Statement

    case class PaymentSuccess(message: String)

    case class PaymentFailure(reason: String)

  }

  class Iob extends Actor {

    import Iob._

    var amount = 0

    override def receive: Receive = {
      case "Deposit" =>println("Deposit")
      case Deposit(amt) => amt match {

        case 0 => println("Zero amount deposit ")
        case amt => println(s"Your Amount is Deposited = $amt")
          amount += amt
      }
      case Withdraw(amt) => amt match {
        case 0 => println("Zero amount Withdraw")
        case x if (amt > amount) => println("Insufficient balance")
        case amt => println(s"Your amount is Withraw = $amt ")
          amount -= amt
      }
      case Statement => println(s"Your amount is $amount")
    }

  }

  val system = ActorSystem("ActorSystem")
  val iob=system.actorOf(Props[Iob],"Iob")
  import Iob._
  iob ! Statement

}
