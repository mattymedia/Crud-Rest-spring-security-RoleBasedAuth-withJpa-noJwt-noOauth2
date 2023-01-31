package co.com.jwtrolebasedauthjpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jwtrolebasedauthjpa.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	//Optional hace referencia a una variable u objecto que puede tener 
	//un valor asignado o que puede contener un valor null, pudiendo aludir error de compilacion
			
	//es util aqui ya que podemos o no encontrar un usuario
	Optional<User> findByUsername(String username);
}
