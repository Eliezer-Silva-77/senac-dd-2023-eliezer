package model.vo.vacina;

public enum TipoUsuario {
	PESQUISADOR(1), VOLUNTARIO(2), GERAL(3);
	
	
	private int valor;

	private TipoUsuario(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public static TipoUsuario getTipoPessoaPorValor(int valor) {
		TipoUsuario tipoPessoa = null;
		for(TipoUsuario elemento : TipoUsuario.values()) {
			if(elemento.getValor() == valor) {
				tipoPessoa = elemento;
			}
		}
		return tipoPessoa;
		
	}
	

	

}
