package api.authentication;

import api.authentication.model.Role;
import api.authentication.model.UserModel;
import api.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AuthenticationApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<UserModel> userList = userRepository.findAll();

		if(!userList.isEmpty()){
			userRepository.deleteAll();
		}

		UserModel user1 = new UserModel("cesar9811",passwordEncoder.encode("1234"),"cesarjml98@gmail.com","cesar jesus","leon martin", Set.of(Role.USER));

		userRepository.save(user1);
	}
}

