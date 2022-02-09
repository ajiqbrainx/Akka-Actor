package Actor

import akka.actor.{Actor, ActorSystem, Props}
//import _root_.Actor.BankAccount.Bank.Deposit
//import _root_.Actor.BankAccount.Bank.Withdraw

object BankPattern extends App {

  object Bank {

    case class Withdraw(amount: Int)

    case class Deposit(amount: Int)

    case object Statement

    case class Failure1(reason: String)

    case class Success1(message: String)

  }

  class Bank extends Actor {

    import Bank._

    var balance = 0


    override def receive: Receive = {
      case Deposit(amt) => amt match {
        case 0 => println(Failure1("Your depositing 0 Balnace"))
        case amt => balance += amt
          println(Success1(s"Your depositing $amt"))
      }
      case Withdraw(amt) => amt match {
        case 0 => println(Failure1("Your withdraw 0 amount"))
        case x if (amt > balance) =>println( Failure1("Insufficient "))
        case amt => balance -= amt
          println(Success1(s"Your withdraw amount$amt"))
      }
      case Statement => println(s"Your balance is $balance")
    }
  }

  import Bank._

  val system = ActorSystem("ActorSystem")
  val actor = system.actorOf(Props[Bank], "Bank")
  actor ! Deposit(1000)
  actor ! Withdraw(10000)
//  actor ! Statement
//  actor ! Deposit(0)
//  actor ! Withdraw(0)
  actor ! Statement

}
