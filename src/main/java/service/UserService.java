package service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import model.User;
import model.UserRole;
import model.UserRoleModel;
import repository.UserRepository;
import repository.UserRoleRepository;
import viewmodel.user.UserViewModel;

@Component
@Service
public class UserService implements IUserService{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Override
	public UserViewModel getCurrentUser() {
		User user = userRepository.findOneByUsername(getCurrentUsername());
		return mapper.map(user, UserViewModel.class);
	}
	
	public String getCurrentUsername() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}
	
	public List<UserViewModel> getAll() {
		return userRepository.findAll()
			.stream()
			.map(user -> this.mapper.map(user, UserViewModel.class))
			.collect(Collectors.toList());
	}
	
	public List<UserViewModel> getAllSortByIdAsc(Collection<Integer> userIds) {
		return userRepository.findAllByIdIn(userIds, new Sort(Direction.ASC, "id"))
				.stream()
				.map(user -> this.mapper.map(user, UserViewModel.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findOneByUsername(username);
		
		List<SimpleGrantedAuthority> roles = this.userRoleRepository.findAllByUsername(username)
				.stream()
				.map(r -> new SimpleGrantedAuthority(r.getRole().toString()))
				.collect(Collectors.toList());		
		user.setAuthorities(roles);
		return user;
	}
	
	
}
