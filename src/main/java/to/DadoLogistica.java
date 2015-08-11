package to;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="DadoLogistica")
@XmlSeeAlso({ReceiveLogisticDataRequest.class})
public class DadoLogistica {

	private String pontoOrigem;
	private String pontoDestino;
	private Double distancia;
	
	public DadoLogistica() {
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

	@XmlElement(name="distancia", required=true)
	public Double getDistancia() {
		return distancia;
	}
	
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
}
