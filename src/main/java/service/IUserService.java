package service;

import java.util.Collection;
import java.util.List;

import viewmodel.user.UserViewModel;

public interface IUserService {
	UserViewModel getCurrentUser();
	String getCurrentUsername();
	List<UserViewModel> getAll();
	List<UserViewModel> getAllSortByIdAsc(Collection<Integer> userIds);
}
