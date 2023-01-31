package co.com.jwtrolebasedauthjpa.service;

import java.util.List;

import co.com.jwtrolebasedauthjpa.model.User;

public interface IUserService {

	public List<User> findAll();
	
	public User findById(Integer id);
	
	public User save(User user);
	
	public void delete(Integer id);
}
