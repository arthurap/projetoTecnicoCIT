package uc.menorCaminhoTO;

public class PontoDestino {

	private Vertice verticeDestino;
	private Double distancia;
	
	public PontoDestino () {
		
	}
	
	public PontoDestino (Vertice p_verticeDestino, Double p_distancia) {
		
		verticeDestino = p_verticeDestino;
		distancia = p_distancia;
		
	}

	public Vertice getVerticeDestino() {
		return verticeDestino;
	}

	public void setVerticeDestino(Vertice verticeDestino) {
		this.verticeDestino = verticeDestino;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
	
}
