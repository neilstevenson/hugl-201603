package neil.demo.hugl201603;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

/**
 * <P>
 * Use Spring Boot to initialise Hazelcast, and to start command line control.
 * </P>
 */
@SpringBootApplication
public class Server implements CommandLineRunner {

	/**
	 * <P>
	 * Use Spring Boot to create the necessary beans and initiate the
	 * {@link #run()} method, and to shutdown then the command line ends.
	 * </P>
	 * 
	 * @param args
	 *            From command line
	 */
	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
		System.exit(0);
	}

	@Autowired
	private HazelcastInstance hazelcastInstance;

	private enum Command {
		ADD, QUIT,
	}

	/**
	 * <P>Simple implementation of command line processing, searching
	 * for matches against the {@code Command} enum.
	 * </P>
	 */
	@Override
	public void run(String... arg0) throws Exception {
		try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {

			this.banner();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tokens = line.toLowerCase().split(" ");
				if (tokens[0].length() > 0) {
					try {
						Command command = Command.valueOf(tokens[0].toUpperCase());
						System.out.println("> " + command);
						
						switch (command) {

						case ADD:
							if (tokens.length!=2) {
								System.out.println("'" + command + "' needs one argument.");
							} else {
								this.add(tokens[1]);
							}
							break;

						case QUIT:
							return;
						}

					} catch (IllegalArgumentException illegalArgumentException) {
						System.out.println("'" + line + "' unrecognised");
					} catch (Exception exception) {
						exception.printStackTrace(System.out);
					}
				}
				this.banner();
			}
		}
	}

	
	/**
	 * <P>Put a message onto a queue.
	 * </P>
	 * 
	 * @param payload Text to add
	 */
	private void add(String payload) {
		IQueue<String> iQueue = this.hazelcastInstance.getQueue("default");

		System.out.println("Queue '" + iQueue.getName() + "' add '" + payload + "'");
		
		iQueue.add(payload);
	}

	/**
	 * <P>List the available commands.</P>
	 */
	private void banner() {
		System.out.println("================ " + this.hazelcastInstance.getName() + " ================");
		System.out.println(Arrays.asList(Command.values()));
	}
}
