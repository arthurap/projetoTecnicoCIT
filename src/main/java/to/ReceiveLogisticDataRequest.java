package to;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ReceiveLogisticDataRequest")
public class ReceiveLogisticDataRequest {

	private String nomeMapa;
	private List<DadoLogistica> dadosLogistica;
	
	public ReceiveLogisticDataRequest () {
		
	}
	@XmlElement(name="nomeMapa", required=true)
	public String getNomeMapa() {
		return nomeMapa;
	}
	public void setNomeMapa(String nomeMapa) {
		this.nomeMapa = nomeMapa;
	}
	@XmlElementWrapper(name = "dadosLogistica")
    @XmlElement(name = "dadoLogistica")
	public List<DadoLogistica> getDadosLogistica() {
		return dadosLogistica;
	}
	public void setDadosLogistica(List<DadoLogistica> dadosLogistica) {
		this.dadosLogistica = dadosLogistica;
	}
}
