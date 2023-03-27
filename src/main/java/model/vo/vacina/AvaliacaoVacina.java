package model.vo.vacina;

public enum AvaliacaoVacina {
	PESSIMO(1), RUIM(2), SATISFATORIO(3), BOM(4), OTIMO(5);
	
	private int valor;

	private AvaliacaoVacina(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public static AvaliacaoVacina getTipoPessoaPorValor(int valor) {
		AvaliacaoVacina avaliacaoVacina = null;
		for(AvaliacaoVacina elemento : AvaliacaoVacina.values()) {
			if(elemento.getValor() == valor) {
				avaliacaoVacina = elemento;
			}
		}
		return avaliacaoVacina;
		
	}
	

}
