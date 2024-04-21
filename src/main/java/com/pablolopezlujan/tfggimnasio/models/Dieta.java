/**
 * 
 */
package com.pablolopezlujan.tfggimnasio.models;

import java.util.List;
import jakarta.persistence.*;

/**
 * Registra las dietas que los usuarios crean y siguen, vinculando cada dieta a
 * un usuario específico.
 */
@Entity
@Table(name = "dietas")
public class Dieta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre_dieta", nullable = false)
	private String nombreDieta;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@OneToMany(mappedBy = "dieta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DietaComida> dietaComidas;

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreDieta() {
		return nombreDieta;
	}

	public void setNombreDieta(String nombreDieta) {
		this.nombreDieta = nombreDieta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DietaComida> getDietaComidas() {
		return dietaComidas;
	}

	public void setDietaComidas(List<DietaComida> dietaComidas) {
		this.dietaComidas = dietaComidas;
	}

}