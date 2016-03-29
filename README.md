# Hazelcast User Group, London

Code resources & slides accompanying the "Spring Boot" talk, 30th March 2016

#### Instructions
There are three variations of the server module, and one of the client module.

#### `server-simplest`
Use `mvn install` to build. The Maven build uses _spring-boot-maven-plugin_
to make the output Jar file executable standalone.

Run this Jar file with `java -jar server-simplest/target/server-simplest-0.0.1-SNAPSHOT.jar`.
Spring Boot should start a Hazelcast server instance.

The purpose of this module is to show the bare minimum required to launch Hazelcast
within Spring Boot. Look for the one Java class and one XML file.

#### `server-simpler`
Following the same procedure as for the previous example, use `mvn install` and
`java -jar server-simpler/target/server-simpler-0.0.1-SNAPSHOT.jar` to use
Spring Boot to start a Hazelcast server instance.

The purpose of this module is to show how Spring Boot can then be extended in
incremental steps to make the application production strength.

The application remains as a _jar_ file for easy deployment, but embeds a
web server for easy management. In this case, URLs such as <http://localhost:8080/info>
and <http://localhost:8080/health> are exposed.

#### `server-simple` & `client`
Again following the same procedure as before, use `mvn install` and
`java -jar server-simpler/target/server-simpler-0.0.1-SNAPSHOT.jar` to use
Spring Boot to start a Hazelcast server instance. 

Once the server has started, use `java -jar server-simpler/target/server-simpler-0.0.1-SNAPSHOT.jar` to
start a Hazelcast client instance which should connect to the running server instance.

The server has a command line interface to allow interaction, for simplicity instead of
using _spring-shell_. Allowed commands can add items to a queue and shut the server down.

The client is a web application, but again deployed as a _jar_ file so the deployment
unit is self-contained. This exposes a URL <http://localhost:8081> to read from queue on the server.



