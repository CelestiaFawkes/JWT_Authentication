import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dto.LoginUserdto;
import dto.RegisterUserDto;
import entity.User;
import repositories.Userrepository;



@Service
public class AuthenticationService {
	
	private Userrepository userrepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	
	
	public AuthenticationService(Userrepository userrepository, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager) {
		super();
		this.userrepository = userrepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}
	
	public User signup(RegisterUserDto input)
	{
        User user = new User()
        		.setEmail(input.getEmail());
        		.setFullname(input.getFullname())
                .setPassword(passwordEncoder.encode(input.getPassword()));

		
		return userrepository.save(user);
	}

	public User authenticate(LoginUserdto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userrepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

	
	
	

}
