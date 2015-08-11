package entity;

import java.io.Serializable;

public class DadosPK implements Serializable {

	protected String pontoOrigem;
	protected String pontoDestino;
	
	public DadosPK () {
		
	}

	public DadosPK(String pontoOrigem, String pontoDestino) {
		this.pontoOrigem = pontoOrigem;
		this.pontoDestino = pontoDestino;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((pontoDestino == null) ? 0 : pontoDestino.hashCode());
		result = prime * result
				+ ((pontoOrigem == null) ? 0 : pontoOrigem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DadosPK other = (DadosPK) obj;
		if (pontoDestino == null) {
			if (other.pontoDestino != null)
				return false;
		} else if (!pontoDestino.equals(other.pontoDestino))
			return false;
		if (pontoOrigem == null) {
			if (other.pontoOrigem != null)
				return false;
		} else if (!pontoOrigem.equals(other.pontoOrigem))
			return false;
		return true;
	}
	
}
