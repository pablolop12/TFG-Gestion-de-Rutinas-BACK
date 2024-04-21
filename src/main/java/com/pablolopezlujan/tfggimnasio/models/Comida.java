/**
 * 
 */
package com.pablolopezlujan.tfggimnasio.models;

import jakarta.persistence.*;

/**
 * Contiene información sobre diferentes tipos de comidas que pueden ser
 * incluidas en las dietas.
 */
@Entity
@Table(name = "comidas")
public class Comida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tipo_comida", nullable = false)
	private String tipoComida;

	@Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
	private String descripcion;

	@Column(name = "calorias")
	private Integer calorias;

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoComida() {
		return tipoComida;
	}

	public void setTipoComida(String tipoComida) {
		this.tipoComida = tipoComida;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCalorias() {
		return calorias;
	}

	public void setCalorias(Integer calorias) {
		this.calorias = calorias;
	}

}
