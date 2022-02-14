import akka.actor.{Actor, ActorSystem, Props}

class Hello extends Actor {
  override def receive: Receive = {
    case msg: String => println(msg + " " + self.path.name)
    case _ => println("No value")
      println(self.path.root)
  }
}

object Hello extends App {
  val actorSystem = ActorSystem("Ajith")
  val actor = actorSystem.actorOf(Props[Hello], "Ajith1")
  actor ! "SS"
  actor ! 23
  val range = 1 to 1000
  val ss = range.map(f=>f*2).toList
  println(ss)

}