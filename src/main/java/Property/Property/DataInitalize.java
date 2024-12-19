package Property.Property;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitalize {
	CommandLineRunner inital() {
		return args->{
			System.out.println("test");
		};
	}
}
