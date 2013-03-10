WiredAtmos
==========

A sample project for how to set up Atmos when running an Akka based system.

Start by cloning this project to your local machine:
> git clone git@github.com:henrikengstrom/wiredatmos.git

Now run SBT to generate the One-Jar file:
> sbt one-jar

Next step is to run the application (replace <path_to> with the directory where the AspectJ weaver JAR file is).
> java -javaagent:<path_to>/aspectjweaver-1.7.2.jar -jar target/WiredAtmos-1.0-SNAPSHOT-one-jar.jar

After a while you should see an exception of some sort since the instrumented code cannot send data when no Atmos Collector is running.
To get rid of the exception just start a Collector (the atmos script is part of the download from Typesafe), e.g.:
> ./atmos -collector

Re-run the application and now you should instead see the following:
[INFO] [03/10/2013 19:19:46.286] [MyActorSystem-akka.actor.default-dispatcher-2] [akka://MyActorSystem/user/SampleActor] Received: Message at 1362939586283915000

Please note that the instrumentation of the Akka code is done at start-up and therefore it takes some time (+30s) before the above line is printed.