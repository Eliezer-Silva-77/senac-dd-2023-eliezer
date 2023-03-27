package model.vo.vacina;

public enum EstagioPesquisa {
	INICIAL(1), TESTE(2), APLICACAO_EM_MASSA(3);
	
	
	private int valor;

	private EstagioPesquisa(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public static EstagioPesquisa getTipoPessoaPorValor(int valor) {
		EstagioPesquisa estagioPesquisa = null;
		for(EstagioPesquisa elemento : EstagioPesquisa.values()) {
			if(elemento.getValor() == valor) {
				estagioPesquisa = elemento;
			}
		}
		return estagioPesquisa;
		
	}

}
