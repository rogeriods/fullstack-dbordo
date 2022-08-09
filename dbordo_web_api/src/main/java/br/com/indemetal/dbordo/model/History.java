package br.com.indemetal.dbordo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "historicos")
public class History {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_saida", nullable = false)
	private Date dataSaida;
	
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	@Column(name = "hora_saida", nullable = false)
	private Date horaSaida;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_chegada")
	private Date dataChegada;
	
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	@Column(name = "hora_chegada")
	private Date horaChegada;
	
	@Column(name = "km_saida", nullable = false)
	private int kmSaida;
	
	@Column(name = "km_chegada")
	private int kmChegada;
	
	@Column(nullable = false, length = 100)
	private String condutor;
	
	@Column(nullable = false)
	private String destino;
	
	@Column(name = "liberado_por", nullable = false, length = 100)
	private String liberadoPor;
	
	@ManyToOne
	@JoinColumn(name = "id_veiculo")
	private Vehicle veiculo;
	
	// Constructors
	public History() {}
	
	public History(Date dataSaida, Date horaSaida) {
		this.dataSaida = dataSaida;
		this.horaSaida = horaSaida;
	}
	
	// Getters and setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDataSaida() {
		return dataSaida;
	}
	
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	
	public Date getHoraSaida() {
		return horaSaida;
	}
	
	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}
	
	public Date getDataChegada() {
		return dataChegada;
	}
	
	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}
	
	public Date getHoraChegada() {
		return horaChegada;
	}
	
	public void setHoraChegada(Date horaChegada) {
		this.horaChegada = horaChegada;
	}
	
	public int getKmSaida() {
		return kmSaida;
	}
	
	public void setKmSaida(int kmSaida) {
		this.kmSaida = kmSaida;
	}
	
	public int getKmChegada() {
		return kmChegada;
	}
	
	public void setKmChegada(int kmChegada) {
		this.kmChegada = kmChegada;
	}
	
	public String getCondutor() {
		return condutor;
	}
	
	public void setCondutor(String condutor) {
		this.condutor = condutor;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getLiberadoPor() {
		return liberadoPor;
	}
	
	public void setLiberadoPor(String liberadoPor) {
		this.liberadoPor = liberadoPor;
	}
	
	public Vehicle getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Vehicle veiculo) {
		this.veiculo = veiculo;
	}
	
}
