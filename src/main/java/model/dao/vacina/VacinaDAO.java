package model.dao.vacina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.dao.Banco;
import model.vo.vacina.EstagioPesquisa;
import model.vo.vacina.Vacina;

public class VacinaDAO {

	public Vacina cadastrarVacinaDAO(Vacina novaVacina) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO VACINA (IDPESQUISADOR, IDSTATUS_PESQUISA, PAIS, INICIO_PESQUISA)"
				+ " VALUES (?,?,?,?) ";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			query.setInt(1, novaVacina.getPesquisador().getId());
			query.setInt(2, novaVacina.getEstagioPesquisa().getValor());
			query.setString(3, novaVacina.getPais());
			query.setString(4, novaVacina.getInicioPesquisa().toString());
			query.execute();

			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novaVacina.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao inserir vacina" + "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return novaVacina;

	}

	public boolean atualizarVacina(Vacina vacinaEditada) {
		boolean statusAtualizacao = false;
		Connection conexao = Banco.getConnection();
		String sql = "UPDATE VACINA" + " SET IDPESQUISADOR = ?, IDSTATUS_PESQUISA = ?, PAIS = ?, INICIO_PESQUISA = ?"
				+ " WHERE ID = ?";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, vacinaEditada.getPesquisador().getId());
			query.setInt(2, vacinaEditada.getEstagioPesquisa().getValor());
			query.setString(3, vacinaEditada.getPais());
			query.setString(4, vacinaEditada.getInicioPesquisa().toString());

			query.setInt(6, vacinaEditada.getId());
			statusAtualizacao = query.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar vacina." + "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return statusAtualizacao;
	}

	public Vacina consultarVacinaId(int idVacina) {
		Vacina vacinaConsultada = null;
		Connection conexao = Banco.getConnection();
		String sql = "SELECT * FROM VACINA WHERE ID = ?";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		try {
			query.setInt(1, idVacina);
			ResultSet resultadoConsulta = query.executeQuery();

			if (resultadoConsulta.next()) {
				vacinaConsultada = new Vacina();
				vacinaConsultada = this.converterResultSetParaEntidade(resultadoConsulta);
			}

		} catch (Exception e) {
			System.out.println("ERRO ao buscar vacina com id: " + idVacina + "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return vacinaConsultada;

	}

	public List<Vacina> consultarVacinaTodas() {
		Connection conexao = Banco.getConnection();
		List<Vacina> vacinas = new ArrayList<Vacina>();
		String sql = "SELECT * FROM VACINA ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Vacina vacinaConsultada = converterResultSetParaEntidade(resultado);
				vacinas.add(vacinaConsultada);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao buscar todas as vacinas." + "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return vacinas;
	}

	private Vacina converterResultSetParaEntidade(ResultSet resultado) throws SQLException {
		Vacina vacinaConsultada = new Vacina();
		UsuarioDAO pessoaDao = new UsuarioDAO();

		vacinaConsultada.setId(resultado.getInt("ID"));
		vacinaConsultada.setPesquisador(pessoaDao.consultarUsuarioId(resultado.getInt("IDPESQUISADOR")));
		vacinaConsultada.setEstagioPesquisa(EstagioPesquisa.getTipoPessoaPorValor(resultado.getInt("IDSTATUS_PESQUISA")));
		vacinaConsultada.setPais(resultado.getString("PAIS"));
		vacinaConsultada.setInicioPesquisa(LocalDate.parse(resultado.getString("INICIO_PESQUISA"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

		return vacinaConsultada;

	}

	public boolean excluirVacina(int idVacina) {
		boolean statusExclusao = false;

		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM VACINA WHERE ID = ?";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, idVacina);
			statusExclusao = query.executeUpdate() > 0;

		} catch (Exception e) {
			System.out.println("ERRO ao excluir vacina.\n" + "Causa " + e.getMessage());

		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return statusExclusao;
	}

}
