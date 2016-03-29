package neil.demo.hugl201603;

import java.util.Arrays;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.TcpIpConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * <P>A configuration class, defining some Spring beans for this
 * application.
 * </P>
 * <P>The {@code @ConfigurationProperties} annotation directs
 * Spring Boot to map property values from the specified file
 * into the fields using the setters.
 * </P>
 */
@Configuration
@ConfigurationProperties(locations="classpath:hugl.properties")
public class MyConfiguration {
	
	private String host;
	
	/**
	 * <P>Define the Hazelcast config as a bean, multicast off, TCP on.
	 * </P>
	 * 
	 * @return Hazelcast config
	 */
	@Bean
	public Config config() {
		Config config = new Config();
				
		config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);

		TcpIpConfig tcpIpConfig = config.getNetworkConfig().getJoin().getTcpIpConfig();
		tcpIpConfig.setEnabled(true);
		tcpIpConfig.setMembers(Arrays.asList(this.host));
		
		return config;
	}

	/**
	 * <P>Create a Hazelcast instance manually. Spring Boot won't
	 * make one if one already is created explicitly.
	 * </P>
	 * 
	 * @param config Same config for both
	 * @return A first Hazelcast server
	 */
	@Bean(name="server1")
	public HazelcastInstance server1(Config config) {
		return Hazelcast.newHazelcastInstance(config);
	}

	/**
	 * <P>Create a second Hazelcast instance manually in this JVM,
	 * using the same config for both.
	 * </P>
	 * 
	 * @param config Same config for both
	 * @return A second Hazelcast server
	 */
	@Bean(name="server2")
	public HazelcastInstance server2(Config config) {
		return Hazelcast.newHazelcastInstance(config);
	}
	
	/**
	 * @param host From {@code hugl.properties}
	 */
	public void setHost(String host) {
		this.host = host;
	}

}
