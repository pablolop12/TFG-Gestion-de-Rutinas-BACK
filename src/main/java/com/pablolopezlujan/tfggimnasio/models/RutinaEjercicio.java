/**
 * 
 */
package com.pablolopezlujan.tfggimnasio.models;

import jakarta.persistence.*;

/**
 * Define la relación entre rutinas y ejercicios, especificando qué ejercicios
 * forman parte de cada rutina, incluyendo detalles como los días y la cantidad
 * de repeticiones y series.
 */
@Entity
@Table(name = "rutinas_ejercicios")
public class RutinaEjercicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "rutina_id", nullable = false)
	private Rutina rutina;

	@ManyToOne
	@JoinColumn(name = "ejercicio_id", nullable = false)
	private Ejercicio ejercicio;

	@Column(name = "dias", nullable = false, length = 100)
	private String dias;

	@Column(name = "repeticiones")
	private Integer repeticiones;

	@Column(name = "series")
	private Integer series;

	@Column(name = "peso")
	private Float peso;

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Rutina getRutina() {
		return rutina;
	}

	public void setRutina(Rutina rutina) {
		this.rutina = rutina;
	}

	public Ejercicio getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(Ejercicio ejercicio) {
		this.ejercicio = ejercicio;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public Integer getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(Integer repeticiones) {
		this.repeticiones = repeticiones;
	}

	public Integer getSeries() {
		return series;
	}

	public void setSeries(Integer series) {
		this.series = series;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

}