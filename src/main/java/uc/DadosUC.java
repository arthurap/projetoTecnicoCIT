package uc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.hibernate.SessionFactory;

import to.DadoLogistica;
import to.ReceiveLogisticDataRequest;
import to.ReceiveLogisticDataResponse;
import to.ReturnShortestPathRequest;
import to.ReturnShortestPathResponse;
import uc.menorCaminhoTO.PontoDestino;
import uc.menorCaminhoTO.Vertice;
import dao.DadosDAO;
import entity.Dados;

public class DadosUC {
	
	public DadosUC () {
		
	}
	
	public ReceiveLogisticDataResponse insereDados(
			ReceiveLogisticDataRequest p_request, SessionFactory p_sessionFactory) throws Exception {
 		
		DadosDAO dadosDAO = new DadosDAO();
		List<Dados> lstDados = new ArrayList<Dados>();
		
		List<DadoLogistica> dadosLogistica = p_request.getDadosLogistica();
		if ((dadosLogistica==null) || (dadosLogistica.isEmpty())) {
			throw new Exception("Nenhum dado para ser inserido no Banco de Dados");
		}
		
		for (DadoLogistica dadoLogistica : dadosLogistica) {
			Dados dado = new Dados();
			dado.setDistancia(dadoLogistica.getDistancia());
			dado.setPontoDestino(dadoLogistica.getPontoDestino());
			dado.setPontoOrigem(dadoLogistica.getPontoOrigem());
			lstDados.add(dado);
		}
		
		dadosDAO.insertDados(lstDados,p_sessionFactory);
		
		return new ReceiveLogisticDataResponse();
	}
	
	public ReturnShortestPathResponse buscaMenorCaminho(
			ReturnShortestPathRequest p_request, SessionFactory p_sessionFactory) throws Exception {
		
		
		if (p_request.getAutonomia().equals(0D)) {
			throw new Exception("Autonomia deve ser diferente de 0");
		}
		Map<String, Vertice> verticePorNome = montaVertices(p_sessionFactory);
		
		if (verticePorNome==null || verticePorNome.isEmpty() || verticePorNome.get(p_request.getPontoOrigem())==null || verticePorNome.get(p_request.getPontoDestino())==null) {
			throw new Exception("Não foi encontrado Rota entre Origem e Destino");
		}
		
		computaCaminhos(verticePorNome.get(p_request.getPontoOrigem()));
		Double tamanho = verticePorNome.get(p_request.getPontoDestino()).getDistanciaMinima();
		if (tamanho.equals(Double.POSITIVE_INFINITY)) {
			throw new Exception("Não foi encontrado Rota entre Origem e Destino");
		}
		Double custo = (tamanho*p_request.getPrecoCombustivel())/p_request.getAutonomia();
		List<Vertice> rota = obtemMenorCaminho(verticePorNome.get(p_request.getPontoDestino()));
		
		return new ReturnShortestPathResponse(custo,rota.toString());
		
	}
	
	private Map<String, Vertice>  montaVertices (SessionFactory p_sessionFactory) throws Exception {
		
		Map<String, Vertice> verticePorNome = new HashMap<String, Vertice>();

		
		DadosDAO dadosDAO = new DadosDAO();
		List<Dados> todosDados = dadosDAO.getTodosDados(p_sessionFactory);
		if (todosDados==null || todosDados.isEmpty()) {
			throw new Exception("Não há nenhum Dado de logística Contratado");
		}
		
		for (Dados dado : todosDados) {
			
			String pontoOrigem = dado.getPontoOrigem();
			String pontoDestino = dado.getPontoDestino();
			
			Vertice verticeOrigem = verticePorNome.get(pontoOrigem);
			if (verticeOrigem==null) {
				verticeOrigem = new Vertice(pontoOrigem);
			}
			
			Vertice verticeDestino = verticePorNome.get(pontoDestino);
			if (verticeDestino==null) {
				verticeDestino = new Vertice(pontoDestino);
			}
			
			verticeOrigem.getPontosDestino().add(new PontoDestino(verticeDestino,dado.getDistancia()));
			
			verticePorNome.put(pontoOrigem,verticeOrigem);
			verticePorNome.put(pontoDestino,verticeDestino);
			
		}
		
		return verticePorNome;
		
	}
	
	private void computaCaminhos(Vertice origem)
    {
		origem.setDistanciaMinima(0D);
		
        PriorityQueue<Vertice> listaVertices = new PriorityQueue<Vertice>();
        listaVertices.add(origem);

        while (!listaVertices.isEmpty()) {
        	Vertice atual = listaVertices.poll();

        	// Visita todos os vértices saindo do atual
        	for (PontoDestino e : atual.getPontosDestino())
        	{
        		Vertice proximo = e.getVerticeDestino();
        		double distancia = e.getDistancia();
        		double distanciaPeloAtaul = atual.getDistanciaMinima() + distancia;
                if (distanciaPeloAtaul < proximo.getDistanciaMinima()) {
                	listaVertices.remove(proximo);
                	proximo.setDistanciaMinima(distanciaPeloAtaul);
                	proximo.setPontoAnterior(atual);
                	listaVertices.add(proximo);
                }
        	}
        }
    }

    private List<Vertice> obtemMenorCaminho(Vertice p_destino)
    {
        List<Vertice> rota = new ArrayList<Vertice>();
        for (Vertice vertice = p_destino; vertice != null; vertice = vertice.getPontoAnterior())
        	rota.add(vertice);

        Collections.reverse(rota);
        return rota;
    }
	
}
