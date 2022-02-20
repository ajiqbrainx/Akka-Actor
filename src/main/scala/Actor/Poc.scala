package Actor

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Kill, PoisonPill, Props}

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

  case class Checked(s: String)

  case object PolicyApply

  case class InvestAmount(x: Int)

  case object Validity

  case object SchemeDetails

  case object Check

  case object ForgotYourId

  val Ajith = "Ajith Nominee is ==> ' GodWin '  "
  val Ashok = "Ashok Nominee is ==>  ' Subash ' "
  val Abi = "Abi Nominee is ==> ' Chan ' "
  val Akil = "Akil Nominee is ==> ' Malik '"
  val Ajil = "Ajil Nominee is ==> ' Suzil ' "

  class Lic extends Actor with ActorLogging {
    override def receive: Receive = {
      case PolicyId(s) => s match {
        case _ if s == 1 => println(s"your policy id is $s and your Name is Ajith")
        case _ if s == 2 => println(s"your policy id is $s and your Name is Ashok")
        case _ if s == 3 => println(s"your policy id is $s and your Name is Abi")
        case _ if s == 4 => println(s"your policy id is $s and your Name is Ajil")
        case _ if s == 5 => println(s"your policy id is $s and your Name is Akil")
        case _ => log.warning("You Policy Id its might be not in list")
          throw new IllegalArgumentException("You are not in list ")
      }
      case InsuranceId(s) => s match {
        case _ if s == 1 => insurance ! First(s)
        case _ if s == 2 => insurance ! Second(s)
        case _ if s == 3 => insurance ! Third(s)
        case _ if s == 4 => insurance ! Fourth(s)
        case _ if s == 5 => insurance ! Fifth(s)
        case _ => log.warning("Your Insurance Id its might be not in list")
          throw new IllegalArgumentException("You are not in list ")
      }
      case Nominee(ref) =>
        ref ! NomineeCheck(Ajith)
        ref ! NomineeCheck(Ashok)
        ref ! NomineeCheck(Abi)
        ref ! NomineeCheck(Ajil)
        ref ! NomineeCheck(Akil)
      case Checked(s) => println(s"Nominee is Checked == $s")
      case SchemeDetails => println()
      case ForgotYourId => println("Enter Your Name First and then Enter Your Mobile number ")
        context.become(change)
      case PolicyApply => log.info("Select your InvestAmount  ")
        log.info("And Check Your Validity Period ")
        context.become(invesement(0, 0))

    }

    def change: Receive = {
      case x: String => x match {
        case _ if x == "Ajith" => context.become(ajith)
        case _ if x == "Ashok" => context.become(ashok)
      }
    }

    def ajith: Receive = {
      case x: Int => x match {
        case _ if x == 11111 => log.info("Your are Policy Id is == 1")
          context.unbecome()
      }
    }

    def ashok: Receive = {
      case x: Int => x match {
        case _ if x == 22222 => log.info("Your are Policy Id is == 2")
          context.unbecome()
      }
    }
        def invesement(amt: Int, y: Int): Receive = {
          case InvestAmount(s) => context.become(invesement(amt + s, y))
            println(s"your Investing Amount is $s")
          case Validity => amt match {
            case _ if amt <= 1000 => context.become(invesement(amt, y + 10))
            case _ if amt <= 2000 => context.become(invesement(amt, y + 20))
            case _ if amt <= 3000 => context.become(invesement(amt, y + 30))
            case _ if amt <= 4000 => context.become(invesement(amt, y + 40))
            case _ if amt <= 5000 => context.become(invesement(amt, y + 50))
            case _ => println("Excess of Amount")
          }
          case Check => println(s"Your Investing Amount is = $amt And Your Validity is = $y Years")

        }

        val insurance: ActorRef = context.actorOf(Props[Insurance1], "insurance")
    }

  class Insurance1 extends Actor {
    override def receive: Receive = {
      case First(s) => println(s"Your  Policy is Id $s And Insurance Id is ==> {Cs11--11} ")
      case Second(s) => println(s"Your  Policy is Id $s And your Insurance Id is ==> {Cs12--12} ")
      case Third(s) => println(s"Your  Policy is Id $s And your Insurance Id is ==> {Cs13--13} ")
      case Fourth(s) => println(s"Your  Policy is Id $s And Insurance your Id is ==> {Cs14--14} ")
      case Fifth(s) => println(s"Your  Policy is Id $s And your Insurance Id is ==> {Cs15--15} ")
    }
  }

  class Nominee1 extends Actor {
    override def receive: Receive = {
      case NomineeCheck(Ajith) =>
        sender() ! Checked(Ajith)
      case NomineeCheck(Ashok) =>
        sender() ! Checked(Ashok)
      case NomineeCheck(Abi) =>
        sender() ! Checked(Abi)
      case NomineeCheck(Ajil) =>
        sender() ! Checked(Ajil)
      case NomineeCheck(Akil) =>
        sender() ! Checked(Akil)

    }
  }

  val system = ActorSystem("System")
  val lic = system.actorOf(Props[Lic], "Lic")
  val nominee = system.actorOf(Props[Nominee1], "nominee")

  //  lic ! PolicyId(1)
  //  lic ! PolicyId(6)
  //  lic ! InsuranceId(1)
  //  lic ! InsuranceId(5)
  //  lic ! InsuranceId(6)
  //
  //  lic ! Nominee(nominee)
  //  lic ! PolicyApply
  //  lic ! InvestAmount(1000)
  // lic ! InvestAmount(6000)
  //  //  lic ! InvestAmount(5000)
  //  lic ! Validity
  //  lic ! Check

  lic ! ForgotYourId
  lic ! "Ashok"
  lic ! 22222

}
