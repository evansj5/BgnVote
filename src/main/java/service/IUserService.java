package service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import viewmodel.user.UserViewModel;

public interface IUserService extends UserDetailsService{
	UserViewModel getCurrentUser();
	String getCurrentUsername();
	List<UserViewModel> getAll();
	List<UserViewModel> getAllSortByIdAsc(Collection<Integer> userIds);
}
