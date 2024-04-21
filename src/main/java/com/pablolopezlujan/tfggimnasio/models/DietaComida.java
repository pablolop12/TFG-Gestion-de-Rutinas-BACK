/**
 * 
 */
package com.pablolopezlujan.tfggimnasio.models;

import jakarta.persistence.*;

/**
 * Establece qué comidas se incluyen en cada dieta y en qué días, facilitando la
 * organización de un plan de comidas semanal.
 */
@Entity
@Table(name = "dietas_comidas")
public class DietaComida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "dieta_id", nullable = false)
	private Dieta dieta;

	@ManyToOne
	@JoinColumn(name = "comida_id", nullable = false)
	private Comida comida;

	@Column(name = "dia", nullable = false)
	private String dia;

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dieta getDieta() {
		return dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}

	public Comida getComida() {
		return comida;
	}

	public void setComida(Comida comida) {
		this.comida = comida;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

}
