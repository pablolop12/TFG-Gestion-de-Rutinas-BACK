/**
 * 
 */
package com.pablolopezlujan.tfggimnasio.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pablolopezlujan.tfggimnasio.models.Usuario;

/**
 * UsuarioRepository es una interfaz que permite la interacción con la base de datos
 * para la entidad Usuario. Extiende JpaRepository, que es parte de Spring Data JPA,
 * el cual proporciona métodos CRUD predefinidos.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}

