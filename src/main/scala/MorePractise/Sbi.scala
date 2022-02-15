package MorePractise

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

object Sbi extends App {

  case class Deposit(x: Int)

  case class Withdraw(y: Int)

  case object Print

  case class Error(msg: String)

  class Sbi extends Actor with ActorLogging{
    override def receive: Receive = bank(0)

    def bank(balance: Int): Receive = {
      case Deposit(amt) => context.become(bank(balance + amt))
        println(s"Depositing the amount is $amt")
      case Withdraw(amt) =>
        amt match {
          case x if amt > balance => println("Insufficient balance")
          case amt => context.become(bank(balance - amt))
            println(s"Your depositing the Amount is $amt")
        }
      case Print => println(s"Your current Balance is $balance")
    }
  }

  val system = ActorSystem("Bank-Iob")
  val sbi = system.actorOf(Props[Sbi], "Sbi")

  sbi ! Deposit(1000)
  sbi ! Print
  sbi ! Withdraw(1900)
  sbi ! Print

}
