package Actor

import akka.actor.{Actor, ActorRef, ActorSystem, Kill, PoisonPill, Props}

object Poc extends App {
  case class PolicyId(s: Int)

  case class InsuranceId(s: Int)

  case class First(s: Int)

  case class Second(s: Int)

  case class Third(s: Int)

  case class Fourth(s: Int)

  case class Fifth(s: Int)

  case class Nominee(name: ActorRef)

  case class NomineeCheck(x: String)

  case object Checked

  case object PolicyDetails

  case class InvestAmount(x: Int)

  case object Validity
  case object Check

  val Ajith = "Ajith Nominee is ==> ' GodWin '  "
  val Ashok = "Ashok Nominee is ==>  ' Subash ' "
  val Abi = "Abi Nominee is ==> ' Chan ' "
  val Akil = "Akil Nominee is ==> ' Malik '"
  val Ajil = "Ajil Nominee is ==> ' Suzil ' "

  class Lic extends Actor {
    override def receive: Receive = {
      case PolicyId(s) => s match {
        case x if s == 1 => println(s"your policy id is $s and your Name is Ajith")
        case x if s == 2 => println(s"your policy id is $s and your Name is Ashok")
        case x if s == 3 => println(s"your policy id is $s and your Name is Abi")
        case x if s == 4 => println(s"your policy id is $s and your Name is Ajil")
        case x if s == 5 => println(s"your policy id is $s and your Name is Akil")
        case _ => println("Your Id is not in list ")
        //          sender() ! Kill
      }
      case InsuranceId(s) => s match {
        case x if s == 1 => insurance ! First(s)
        case x if s == 2 => insurance ! Second(s)
        case x if s == 3 => insurance ! Third(s)
        case x if s == 4 => insurance ! Fourth(s)
        case x if s == 5 => insurance ! Fifth(s)
        case _ => println("your insurance id is not in list ")
        //          sender() ! Kill
      }
      case Nominee(ref) =>
//        ref ! NomineeCheck(Ajith)
        ref ! NomineeCheck(Ashok)
//        ref ! NomineeCheck(Abi)
//        ref ! NomineeCheck(Ajil)
//        ref ! NomineeCheck(Akil)
      case Checked => println("Nominee Checked")
      case PolicyDetails =>context.become( invesement(0,0))

    }

    def invesement(amt: Int, y: Int): Receive = {
      case InvestAmount(s) => context.become(invesement(amt + s, y))
        println(s"your Investing Amount is $s")
      case Validity => amt match {
        case x if amt <= 1000 => context.become(invesement(amt, y + 10))
        case x if amt <= 2000 => context.become(invesement(amt, y + 20))
        case x if amt <= 3000 => context.become(invesement(amt, y + 30))
        case x if amt <= 4000 => context.become(invesement(amt, y + 40))
        case x if amt <= 5000 => context.become(invesement(amt, y + 50))
        case _ => println("Excess of Amount")
      }
      case Check => println(s"Your Policy Amount is = $amt And Your Validity is = $y Years")

    }

    val insurance = context.actorOf(Props[Insurance1], "insurance")
  }

  class Insurance1 extends Actor {
    override def receive: Receive = {
      case First(s) => println(s"Your Insurance Policy is Id $s And your Id is ==> {Cs11--11} ")
      case Second(s) => println(s"Your Insurance Policy is Id $s And your Id is ==> {Cs12--12} ")
      case Third(s) => println(s"Your Insurance Policy is Id $s And your Id is ==> {Cs13--13} ")
      case Fourth(s) => println(s"Your Insurance Policy is Id $s And your Id is ==> {Cs14--14} ")
      case Fifth(s) => println(s"Your Insurance Policy is Id $s And your Id is ==> {Cs15--15} ")
    }
  }

  class Nominee1 extends Actor {
    override def receive: Receive = {
      case NomineeCheck(Ajith) => println(Ajith)
        sender() ! Checked
      case NomineeCheck(Ashok) => println(Ashok)
        sender() ! Checked
      case NomineeCheck(Abi) => println(Abi)
        sender() ! Checked
      case NomineeCheck(Ajil) => println(Ajil)
        sender() ! Checked
      case NomineeCheck(Akil) => println(Akil)
        sender() ! Checked

    }
  }

  val system = ActorSystem("System")
  val lic = system.actorOf(Props[Lic], "Lic")
  val nominee = system.actorOf(Props[Nominee1], "nominee")

//  lic ! PolicyId(1)
//  lic ! PolicyId(5)
//  lic ! InsuranceId(1)
//  lic ! InsuranceId(5)
//  lic ! InsuranceId(6)
//
//  lic ! Nominee(nominee)
  lic ! PolicyDetails
  lic ! InvestAmount(1000)
  lic ! InvestAmount(6000)
//  lic ! InvestAmount(5000)
  lic ! Validity
  lic ! Check


}
