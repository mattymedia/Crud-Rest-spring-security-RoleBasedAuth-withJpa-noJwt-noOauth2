package co.com.jwtrolebasedauthjpa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jwtrolebasedauthjpa.model.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

	List<Role> findAllById(Integer id);	
}
