/**
 * 
 */
package com.pablolopezlujan.tfggimnasio.models;

import java.util.List;

import jakarta.persistence.*;

/**
 * Almacena las rutinas de ejercicios que los usuarios crean. Cada rutina está
 * vinculada a un usuario específico y puede contener varios ejercicios.
 */
@Entity
@Table(name = "rutinas")
public class Rutina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre_rutina", nullable = false, length = 100)
	private String nombreRutina;

	@Column(name = "descripcion", columnDefinition = "TEXT")
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@Column(name = "is_active", nullable = false)
	private boolean isActive;

	@Column(name = "start_date")
	private java.sql.Date startDate;

	@OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RutinaEjercicio> rutinaEjercicios;

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreRutina() {
		return nombreRutina;
	}

	public void setNombreRutina(String nombreRutina) {
		this.nombreRutina = nombreRutina;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public java.sql.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}

	public List<RutinaEjercicio> getRutinaEjercicios() {
		return rutinaEjercicios;
	}

	public void setRutinaEjercicios(List<RutinaEjercicio> rutinaEjercicios) {
		this.rutinaEjercicios = rutinaEjercicios;
	}

}
