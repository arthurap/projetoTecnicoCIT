package to;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import enums.EnumStatusResposta;

@XmlRootElement(name="ReturnShortestPathResponse")
public class ReturnShortestPathResponse {

	private Double custo;
	private String rota;
	private String status;
	private String errorMessage;
	
	public ReturnShortestPathResponse () {
		preencheAcerto();
	}
	
	public ReturnShortestPathResponse (Double p_custo, String p_rota) {
		preencheAcerto();;
		custo =p_custo;
		rota = p_rota;
	}
	
	public ReturnShortestPathResponse (Exception e) {
		if (e!=null) {
			preencheErro(e.getMessage());
		}
		else {
			preencheAcerto();
		}
	}

	private void preencheErro(String p_mensagemErro) {
		status = EnumStatusResposta.NOK.getValor();
		if (p_mensagemErro!=null) {
			errorMessage = p_mensagemErro;
		}
		else {
			errorMessage = "Erro Desconhecido";
		}
	}
	
	private void preencheAcerto() {
		status = EnumStatusResposta.OK.getValor();
		errorMessage = null;
	}
	
	@XmlElement(name="custo", required=false)
	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	@XmlElement(name="rota", required=false)
	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}
	
	@XmlElement(name="status", required=true)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@XmlElement(name="errorMessage", required=false)
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
