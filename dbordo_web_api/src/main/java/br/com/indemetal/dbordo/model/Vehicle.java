package br.com.indemetal.dbordo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "veiculos")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String modelo;
	
	@Column(nullable = false, unique = true, length = 10)
	private String placa;
	
	@Column(name = "ultimo_km", nullable = false)
	private int ultimoKm;
	
	@Column(nullable = false)
	private boolean status;
	
	// Getters and setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public int getUltimoKm() {
		return ultimoKm;
	}
	
	public void setUltimoKm(int ultimoKm) {
		this.ultimoKm = ultimoKm;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
