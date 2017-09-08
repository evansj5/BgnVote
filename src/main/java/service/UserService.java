package service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import model.User;
import repository.UserRepository;
import viewmodel.user.UserViewModel;

@Component
public class UserService implements IUserService{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DozerBeanMapper mapper;
	
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
}
