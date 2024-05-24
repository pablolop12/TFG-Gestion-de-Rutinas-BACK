/**
 * 
 */
package com.pablolopezlujan.tfggimnasio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.pablolopezlujan.tfggimnasio.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * UsuarioRepository es una interfaz que permite la interacción con la base de datos
 * para la entidad Usuario. Extiende JpaRepository, que es parte de Spring Data JPA,
 * el cual proporciona métodos CRUD predefinidos.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    	
	Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
	
}

