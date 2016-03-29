package neil.demo.hugl201603;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <P>Spring Boot will scan the class files, and if it finds
 * a {@code @Bean} of type {@code Config}, it will create a
 * Hazelcast instance using that config.
 * </P>
 * <P>As {@code spring-boot-starter-web} is in the classpath,
 * when the jar file is run, an embedded Tomcat will be started
 * and the application will be web-enabled. The port will default
 * to 8080.
 * </P>
 */
@SpringBootApplication
public class Server {

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

}
