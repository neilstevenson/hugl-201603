package neil.demo.hugl201603;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.boot.actuate.endpoint.InfoEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.TcpIpConfig;
import com.hazelcast.core.HazelcastInstance;

/**
 * <P>A configuration class, defining some Spring beans for this
 * application.
 * </P>
 */
@Configuration
public class MyConfiguration {
	
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
		tcpIpConfig.setMembers(Arrays.asList("127.0.0.1"));
		
		return config;
	}

	/**
	 * <P>Create static information about the application that can be
	 * displayed with <a href="http://localhost:8080/info">http://localhost:8080/info</a>.
	 * If you want dynamic information, investigate health endpoints.
	 * </P>
	 * <P>Spring Boot will create a Hazelcast Instance as a bean because
	 * of the existance of the {@code Config} bean above. Spring will inject
	 * the instance as an argument when creating this bean.
	 * 
	 * @param hazelcastInstance Using the {@code Config} bean
	 * @return Static info about this Hazelcast instance
	 */
	@Bean
	public InfoEndpoint infoEndpoint(HazelcastInstance hazelcastInstance) {
		
		HashMap<String, String> info = new HashMap<>();
		
		info.put("Name", hazelcastInstance.getName());
		info.put("Group", hazelcastInstance.getConfig().getGroupConfig().getName());
		
		return new InfoEndpoint(info);
	}
}
