package co.com.jwtrolebasedauthjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.jwtrolebasedauthjpa.model.UserSecurity;
import co.com.jwtrolebasedauthjpa.repository.IUserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepository
				.findByUsername(username)
				.map(UserSecurity::new)
				.orElseThrow(() -> 
					new UsernameNotFoundException("username not found: " + username));
	}
}
