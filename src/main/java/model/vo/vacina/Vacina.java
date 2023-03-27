package model.vo.vacina;

import java.time.LocalDate;

public class Vacina {
	private Integer id;
	private Usuario pesquisador;
	private String pais;
	private EstagioPesquisa estagioPesquisa;
	private LocalDate inicioPesquisa;

	public Vacina(Integer id, Usuario pesquisador, String pais, EstagioPesquisa estagioPesquisa,
			LocalDate inicioPesquisa) {
		super();
		this.id = id;
		this.pesquisador = pesquisador;
		this.pais = pais;
		this.estagioPesquisa = estagioPesquisa;
		this.inicioPesquisa = inicioPesquisa;
	}

	public Vacina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Usuario nomePesquisador) {
		this.pesquisador = nomePesquisador;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public EstagioPesquisa getEstagioPesquisa() {
		return estagioPesquisa;
	}

	public void setEstagioPesquisa(EstagioPesquisa estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}

	public LocalDate getInicioPesquisa() {
		return inicioPesquisa;
	}

	public void setInicioPesquisa(LocalDate inicioPesquisa) {
		this.inicioPesquisa = inicioPesquisa;
	}

	@Override
	public String toString() {
		return "Vacina [id=" + id + ", pesquisador=" + pesquisador.getNome() + ", pais=" + pais + ", estagioPesquisa="
				+ estagioPesquisa + ", inicioPesquisa=" + inicioPesquisa + "]";
	}

}
