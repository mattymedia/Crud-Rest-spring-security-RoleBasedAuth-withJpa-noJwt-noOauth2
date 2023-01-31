package co.com.jwtrolebasedauthjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.jwtrolebasedauthjpa.model.User;
import co.com.jwtrolebasedauthjpa.repository.IUserRepository;

@Service
public class ImplUserService implements IUserService{
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

}
