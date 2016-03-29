package neil.demo.hugl201603;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.core.HazelcastInstance;

/**
 * <P>Define any Spring beans needed on top of those added
 * automatically.
 * </P>
 */
@Configuration
public class MyConfiguration {
	
	/**
	 * <P>This version (1.3.3) of Spring Boot will automatically
	 * build a Hazelcast server but not a Hazelcast client. This is
	 * a known issue (<a href="https://github.com/spring-projects/spring-boot/issues/4918">4918</a>).
	 * </P>
	 * <P>So, create the Hazelcast instance as a Spring bean directly, taking
	 * the configuration from the named XML file in the classpath.
	 * </P>
	 * 
	 * @return A Hazelcast instance for the client
	 * @throws Exception If XML not found
	 */
    @Bean
    public HazelcastInstance hazelcastInstance() throws Exception {
            ClientConfig clientConfig = new XmlClientConfigBuilder("hazelcast-client.xml").build();
            return HazelcastClient.newHazelcastClient(clientConfig);
    }	

}
