package org.h3nk3

import akka.actor.{Props, ActorLogging, Actor, ActorSystem}

object WiredAtmosMain extends App {
  val system = ActorSystem("MyActorSystem")
  val sampleActor = system.actorOf(Props[SampleActor], "SampleActor")
  sampleActor ! "Message at " + System.nanoTime
}

class SampleActor extends Actor with ActorLogging {
  def receive = {
    case x => log.info("Received: " + x)
  }
}
