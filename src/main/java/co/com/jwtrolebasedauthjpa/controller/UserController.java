package co.com.jwtrolebasedauthjpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.jwtrolebasedauthjpa.model.Role;
import co.com.jwtrolebasedauthjpa.model.User;
import co.com.jwtrolebasedauthjpa.repository.IRoleRepository;
import co.com.jwtrolebasedauthjpa.service.IUserService;

@RestController
@RequestMapping
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired 
	private IRoleRepository roleRepository;
			
	@GetMapping("/users")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User findById(@PathVariable Integer id) {
		return userService.findById(id);
	}
	
	@PostMapping("/users/save")
	public ResponseEntity<String> save(@RequestBody User user) {	
				
		List<Role> roles = user.getRoles();
		List<Integer> idRole = new ArrayList<>();
		
		for(Role role : roles) {
			idRole.add(role.getId());
			System.out.println("roles: " + idRole);
		}
		
		user.setRoles(roleRepository.findAllById(idRole));
				
		userService.save(user);
		
		return new ResponseEntity<String>("the user: " + user.getUsername() 
		+ ", have been saved successfully.", HttpStatus.OK);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> edit(@PathVariable Integer id, @RequestBody User user) {
		
		User userById = userService.findById(id);
		List<Role> roles = user.getRoles();
		List<Integer> idRole = new ArrayList<>();
		
		for(Role role : roles) {
			idRole.add(role.getId());
		}
		
		
		userById.setUsername(user.getUsername());
		userById.setPassword(user.getPassword());
		userById.setRoles(roleRepository.findAllById(idRole));
		
		
		userService.save(userById);
		
		return new ResponseEntity<String>("the changes made in user: " 
		+ userById.getId() + ", have been saved successfully.", HttpStatus.OK);
	}
	
	@DeleteMapping("/users/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		
		User userById = userService.findById(id);
		
		userService.delete(id);
		
		return new ResponseEntity<String>("user: " + userById.getUsername() + 
				", was successfully deleted from the database.",HttpStatus.OK);
		
	}
	
}
