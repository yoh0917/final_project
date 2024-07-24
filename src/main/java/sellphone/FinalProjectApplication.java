package sellphone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy
public class FinalProjectApplication {

	public static final Logger log = LogManager.getLogger(FinalProjectApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
		log.info("\n===============================\n\n"
				+ "     Application Start" +
				"\n\n===============================\n");

		
	
		
		
		
		
	}

}
