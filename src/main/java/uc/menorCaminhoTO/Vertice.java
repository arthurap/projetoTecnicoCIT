package uc.menorCaminhoTO;

import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice>{

	private String pontoOrigem;
	private List<PontoDestino> pontosDestino;
	private Double distanciaMinima;
	private Vertice pontoAnterior;
	
	public Vertice () {
		distanciaMinima = Double.POSITIVE_INFINITY;
		pontosDestino = new ArrayList<PontoDestino>();
	}
	
	public Vertice (String p_pontoOrigem)
	{ 
		distanciaMinima = Double.POSITIVE_INFINITY;
		pontosDestino = new ArrayList<PontoDestino>();
		pontoOrigem = p_pontoOrigem; 
	}
	
	public String toString() {
		return pontoOrigem;
	}
	
    public int compareTo(Vertice other)
    {
        return Double.compare(distanciaMinima, other.distanciaMinima);
    }

	public String getPontoOrigem() {
		return pontoOrigem;
	}

	public void setPontoOrigem(String pontoOrigem) {
		this.pontoOrigem = pontoOrigem;
	}

	public List<PontoDestino> getPontosDestino() {
		return pontosDestino;
	}

	public void setPontosDestino(List<PontoDestino> pontosDestino) {
		this.pontosDestino = pontosDestino;
	}

	public Double getDistanciaMinima() {
		return distanciaMinima;
	}

	public void setDistanciaMinima(Double distanciaMinima) {
		this.distanciaMinima = distanciaMinima;
	}

	public Vertice getPontoAnterior() {
		return pontoAnterior;
	}

	public void setPontoAnterior(Vertice pontoAnterior) {
		this.pontoAnterior = pontoAnterior;
	}
}
