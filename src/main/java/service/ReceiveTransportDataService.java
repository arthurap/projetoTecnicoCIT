/**
 * 
 */
package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import serviceBean.DadosManager;
import to.ReceiveLogisticDataRequest;
import to.ReceiveLogisticDataResponse;
import to.ReturnShortestPathRequest;
import to.ReturnShortestPathResponse;

/**
 * @author arthur.pedro
 *
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle=SOAPBinding.ParameterStyle.WRAPPED, use=SOAPBinding.Use.LITERAL)
public class ReceiveTransportDataService {
	
	@WebMethod(operationName = "recebeDadosLogistica")
	@RequestWrapper(className="to.ReceiveLogisticDataRequest")
	@ResponseWrapper(className="to.ReceiveLogisticDataResponse")
	@WebResult(name="reponse")
	public ReceiveLogisticDataResponse recebeDadosLogistica (@WebParam(name="request",mode=Mode.IN)ReceiveLogisticDataRequest p_request) {
		try {
			DadosManager dadosManager = getDadosBean();
			return dadosManager.insereDados(p_request);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ReceiveLogisticDataResponse(e);
		}
	}
	
	@WebMethod(operationName = "retornaMenorCaminho")
	@RequestWrapper(className="to.ReturnShortestPathRequest")
	@ResponseWrapper(className="to.ReturnShortestPathResponse")
	@WebResult(name="reponse")
	public ReturnShortestPathResponse retornaMenorCaminho (@WebParam(name="request",mode=Mode.IN)ReturnShortestPathRequest p_request) {
		try {
			DadosManager dadosManager = getDadosBean();
			return dadosManager.buscaMenorCaminho(p_request);
		}
		catch (Exception e) {
			return new ReturnShortestPathResponse(e);
		}
	}
	
	private DadosManager getDadosBean() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		return (DadosManager) ctx.getBean("dadosManagerImpl");
	}
	
}
