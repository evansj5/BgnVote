package service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.sendgrid.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import model.RegistrationKey;
import model.User;
import model.UserRole;
import model.UserRoleModel;
import repository.RegistrationKeyRepository;
import repository.UserRepository;
import repository.UserRoleRepository;
import viewmodel.user.RegisterUserDto;

@Component
public class RegistrationService implements IRegistrationService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RegistrationKeyRepository regKeyRepository;
	
	@Autowired
	private UserRoleRepository roleRepository;
	
	/* (non-Javadoc)
	 * @see service.IRegistrationService#registerUser(viewmodel.user.RegisterUserDto)
	 */
	@Override
	public void registerUser(RegisterUserDto registerUserDto) {
		RegistrationKey regKey = validateRegisterUserDto(registerUserDto);
		
		User user = new User();		
		user.setUsername(registerUserDto.getUsername());
		user.setFirstName(registerUserDto.getFirstName());
		user.setLastName(registerUserDto.getLastName());
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
		user.setBggUsername(registerUserDto.getBggUsername().orElse(null));
		user.setEmail(registerUserDto.getEmail());
		
		userRepository.save(user);
		
		UserRoleModel role = new UserRoleModel();
		role.setRole(regKey.getUserRole());
		role.setUsername(registerUserDto.getUsername());
		
		roleRepository.save(role);
		regKeyRepository.delete(regKey);
	}
	
	private RegistrationKey validateRegisterUserDto(RegisterUserDto dto) {
		User user = userRepository.findOneByEmail(dto.getEmail());
		
		if(user != null) {
			throw new RuntimeException("Already have a user with that email.");
		}
		
		user = userRepository.findOneByUsername(dto.getUsername());
		
		if(user != null) {
			throw new RuntimeException("Already have a user with that username.");
		}
		
		RegistrationKey regKey = regKeyRepository.findOneByRegistrationKeyAndEmail(dto.getRegKey(), dto.getEmail());
		
		if(regKey == null) {
			throw new RuntimeException("Registration key was not valid for this email address.");
		}
		
		if(regKey.isUsed()) {
			throw new RuntimeException("That registration key is already used.");
		}
		
		if(regKey.getExpireDate().compareTo(new Date()) < 0) {
			throw new RuntimeException("That registration key is expired.");
		}
		
		return regKey;
	}
	
	public void issueNewRegistrationKeys(List<String> emailAddresses) {
		emailAddresses.forEach(email -> {
			Date oneWeek = new Date();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(oneWeek);
			cal.add(Calendar.DATE, 7);
			
			RegistrationKey key = new RegistrationKey();
			key.setEmail(email);
			key.setExpireDate(cal.getTime());
			key.setUserRole(UserRole.ROLE_USER);
			key.setUsed(false);
			
			key = regKeyRepository.save(key);
			sendRegKeyEmail(key);
		});
	}
	
	private void sendRegKeyEmail(RegistrationKey key) {
		Email to = new Email(key.getEmail());
		Email from = new Email("jasonevans13@gmail.com");
		Content content = new Content("text/plain", "Registration key: " + key.getRegistrationKey());
		String subject = "Registration key for Board Game Voting";
		Mail mail = new Mail(from, subject, to, content);
		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();
		try {
			request.method = Method.POST;
			request.endpoint = "mail/send";
			request.body = mail.build();
			Response response = sg.api(request);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
