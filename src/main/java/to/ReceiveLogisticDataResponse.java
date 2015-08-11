package to;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import enums.EnumStatusResposta;

@XmlRootElement(name="ReceiveLogisticDataResponse")
public class ReceiveLogisticDataResponse {
	
	private String status;
	private String errorMessage;
	
	public ReceiveLogisticDataResponse () {
		preencheAcerto();
	}
	
	public ReceiveLogisticDataResponse (Exception e) {
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
