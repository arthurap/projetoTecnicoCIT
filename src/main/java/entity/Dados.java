package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name="DADOS")
@IdClass(DadosPK.class)
public class Dados implements Serializable {
	
	@Id
	@Column(name="pontoOrigem", nullable=false)
	private String pontoOrigem;
	
	@Id
	@Column(name="pontoDestino", nullable=false)
	private String pontoDestino;
	
	@Column(name="distancia", nullable=false)
	private Double distancia;

	public Dados () {
		
	}
	
	public String getPontoOrigem() {
		return pontoOrigem;
	}

	public void setPontoOrigem(String pontoOrigem) {
		this.pontoOrigem = pontoOrigem;
	}

	public String getPontoDestino() {
		return pontoDestino;
	}

	public void setPontoDestino(String pontoDestino) {
		this.pontoDestino = pontoDestino;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
	
}
