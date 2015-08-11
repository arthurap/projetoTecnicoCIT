package serviceBean;

import to.ReceiveLogisticDataRequest;
import to.ReceiveLogisticDataResponse;
import to.ReturnShortestPathRequest;
import to.ReturnShortestPathResponse;

public interface DadosManager {

	ReceiveLogisticDataResponse insereDados (ReceiveLogisticDataRequest p_request) throws Exception;
	
	ReturnShortestPathResponse buscaMenorCaminho (ReturnShortestPathRequest p_request) throws Exception;
	
}