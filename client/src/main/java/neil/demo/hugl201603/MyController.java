package neil.demo.hugl201603;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

/**
 * <P>Expose some Hazelcast client operations over the web,
 * another form of REST client.
 * </P>
 * 
 */
@RestController
public class MyController {
	private static final Logger log = LoggerFactory.getLogger(MyController.class);
	
	@Autowired
	private HazelcastInstance hazelcastInstance;
	
	/**
	 * <P>Calls to the root URL of this application 
	 * <a href="http://localhost:8081/">http://localhost:8081/</a>
	 * are mapped to a call to this method.
	 * </P>
	 * <P>As this is a REST call, the String returned is returned
	 * directly to the caller, it is not the name of a view to render.
	 * </P>
	 * 
	 * @return An item from the queue
	 */
	@RequestMapping(value = "/")
    public String poll() {
		
		IQueue<String> iQueue = this.hazelcastInstance.getQueue("default");
		
		String s = iQueue.poll();
		
		log.info("Poll '{}' got '{}'", iQueue.getName(), s);
		
		if (s == null) {
			return "_empty_";
		} else {
			return s.toString();
		}

	}    

}
