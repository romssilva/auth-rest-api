package auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import auth.model.User;
import auth.repository.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User(null, "romulosilva", "Romulo Silva", "roms.silva@gmail.com", "12345"));
	}
}
