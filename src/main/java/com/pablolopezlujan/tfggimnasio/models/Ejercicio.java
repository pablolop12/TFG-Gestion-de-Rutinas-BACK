/**
 * 
 */
package com.pablolopezlujan.tfggimnasio.models;

import jakarta.persistence.*;

/**
 * Contiene detalles sobre los ejercicios disponibles que los usuarios pueden
 * agregar a sus rutinas. Incluye una descripción y una imagen para ilustrar
 * cómo realizar cada ejercicio.
 */
@Entity
@Table(name = "ejercicios")
public class Ejercicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
	private String descripcion;

	@Column(name = "categoria", nullable = false)
	private String categoria;

	@Column(name = "imagen_url")
	private String imagenUrl;

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

}