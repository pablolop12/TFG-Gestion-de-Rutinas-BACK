/**
 * 
 */
package com.pablolopezlujan.tfggimnasio.models;

import jakarta.persistence.*;
import java.util.List;

/**
 * Almacena información sobre los usuarios de la aplicación, incluyendo sus
 * datos personales y credenciales de acceso. Cada usuario puede tener múltiples
 * rutinas y dietas asociadas.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre_completo", nullable = false, length = 50)
	private String nombreCompleto;

	@Column(name = "movil", nullable = false, length = 15)
	private String movil;

	@Column(name = "correo_electronico", nullable = false, length = 50)
	private String correoElectronico;

	@Column(name = "tipo_usuario", nullable = false)
	private String tipoUsuario;

	@Column(name = "contrasena", nullable = false)
	private String contrasena;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Rutina> rutinas;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Dieta> dietas;

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public List<Rutina> getRutinas() {
		return rutinas;
	}

	public void setRutinas(List<Rutina> rutinas) {
		this.rutinas = rutinas;
	}

	public List<Dieta> getDietas() {
		return dietas;
	}

	public void setDietas(List<Dieta> dietas) {
		this.dietas = dietas;
	}

}