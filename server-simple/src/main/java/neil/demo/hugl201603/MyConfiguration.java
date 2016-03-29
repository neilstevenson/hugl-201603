package neil.demo.hugl201603;

import java.util.Arrays;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.TcpIpConfig;

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
	 * <P>The existance of this bean triggers Spring Boot to create
	 * a Hazelcast instance, and to make this Hazelcast instance
	 * available as a bean also.
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
	 * @param host From {@code hugl.properties}
	 */
	public void setHost(String host) {
		this.host = host;
	}

}
