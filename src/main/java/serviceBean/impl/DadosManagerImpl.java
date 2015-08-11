package serviceBean.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import serviceBean.DadosManager;
import to.ReceiveLogisticDataRequest;
import to.ReceiveLogisticDataResponse;
import to.ReturnShortestPathRequest;
import to.ReturnShortestPathResponse;
import uc.DadosUC;

@Service
public class DadosManagerImpl implements DadosManager {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public ReceiveLogisticDataResponse insereDados(
			ReceiveLogisticDataRequest p_request) throws Exception {
		DadosUC dadosUC = new DadosUC();
		return dadosUC.insereDados(p_request,sessionFactory);
	}

	@Transactional
	public ReturnShortestPathResponse buscaMenorCaminho(
			ReturnShortestPathRequest p_request)  throws Exception {
		DadosUC dadosUC = new DadosUC();
		return dadosUC.buscaMenorCaminho(p_request,sessionFactory);
	}

}
