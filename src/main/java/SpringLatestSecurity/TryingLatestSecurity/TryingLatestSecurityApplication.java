package SpringLatestSecurity.TryingLatestSecurity;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TryingLatestSecurityApplication {


	public static void main(String[] args) {
		SpringApplication.run(TryingLatestSecurityApplication.class, args);
		System.out.println("server has been started");
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
	}

}
