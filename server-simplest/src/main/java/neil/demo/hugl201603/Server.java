package neil.demo.hugl201603;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <P>If Spring Boot finds Hazelcast jars in the classpath and
 * the {@code hazelcast.xml} file, it will start a Hazelcast
 * instance using that XML file.
 * </P>
 */
@SpringBootApplication
public class Server {

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

}
