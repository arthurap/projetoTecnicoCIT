package to;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ReturnShortestPathRequest")
public class ReturnShortestPathRequest {

	private String pontoOrigem;
	private String pontoDestino;
	private Double autonomia;
	private Double precoCombustivel;
	
	public ReturnShortestPathRequest () {
		
	}

	@XmlElement(name="pontoOrigem", required=true)
	public String getPontoOrigem() {
		return pontoOrigem;
	}

	public void setPontoOrigem(String pontoOrigem) {
		this.pontoOrigem = pontoOrigem;
	}

	@XmlElement(name="pontoDestino", required=true)
	public String getPontoDestino() {
		return pontoDestino;
	}

	public void setPontoDestino(String pontoDestino) {
		this.pontoDestino = pontoDestino;
	}

	@XmlElement(name="autonomia", required=true)
	public Double getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(Double autonomia) {
		this.autonomia = autonomia;
	}

	@XmlElement(name="precoCombustivel", required=true)
	public Double getPrecoCombustivel() {
		return precoCombustivel;
	}

	public void setPrecoCombustivel(Double precoCombustivel) {
		this.precoCombustivel = precoCombustivel;
	}
	
}
