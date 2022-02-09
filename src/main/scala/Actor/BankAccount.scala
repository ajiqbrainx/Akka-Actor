package Actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import _root_.Actor.BankAccount.Bank.Withdraw

object BankAccount extends App {
  object Bank {
    case class Withdraw(money: Int)

    case class Deposit(money: Int)

    case object Statement

    case class TransSuccess(message: String)

    case class TransFailure(reason: String)

  }

  class Bank extends Actor {

    import Bank._

    var fund = 0

    override def receive: Receive = {
      case Deposit(money) =>
        if (money < 0) sender() ! TransFailure("You don't have a deposited ")
        else {
          fund += money
          sender() ! TransSuccess(s"Success fully deposited $money")
        }
      case Withdraw(money) =>
        if (money < 0) sender() ! TransFailure("You don't have Withdraw ")
        else if (money > fund) sender() ! TransFailure("You don't have money ")
        else {
          fund -= money
          sender() ! TransSuccess(s"Successfully Withdraw $money")
        }
      case Statement => sender() ! s"Your balance is $fund"

    }
  }

  object Person {
    case class Live(account: ActorRef)
  }

  class Person extends Actor {

    import Person._
    import Bank._

    override def receive: Receive = {
      case Live(account) =>
        account ! Deposit(10000)
        account ! Withdraw(11000)
        account ! Withdraw(1000)
        account ! Statement
      case msg => println(msg)
    }
  }

  import Person._

  val system = ActorSystem("Actor")
  val account = system.actorOf(Props[Bank], "Ajith")
  val person = system.actorOf(Props[Person], "Aji")


  person ! Live(account)

}
