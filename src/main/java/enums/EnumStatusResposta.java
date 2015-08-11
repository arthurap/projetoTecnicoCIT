package enums;

public enum EnumStatusResposta {

	OK ("OK"),
	NOK ("NOK");
	
	private String valor;
	
	private EnumStatusResposta (String p_valor) {
		valor = p_valor;
	}
	
	public String getValor()
	{
		return valor;
	}
	
}
