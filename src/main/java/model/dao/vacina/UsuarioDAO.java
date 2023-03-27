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
import model.vo.vacina.Usuario;
import model.vo.vacina.TipoUsuario;

public class UsuarioDAO {

	public Usuario cadastrarPessoaDAO(Usuario novaPessoa) {
		// Conectar ao banco
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO PESSOA (NOME, CPF, DTNASCIMENTO, SEXO, IDSTATUS_PESSOA)" + " VALUES (?,?,?,?,?) ";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		// Executar o INSERT
		try {
			query.setString(1, novaPessoa.getNome());
			query.setString(2, novaPessoa.getCpf());
			query.setString(3, novaPessoa.getDataNascimento().toString());
			query.setString(4, novaPessoa.getSexo());
			query.setInt(5, novaPessoa.getTipoUsuario().getValor());
			query.execute();

			// Preencher o ID gerado no banco no objeto
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novaPessoa.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao inserir usuario" + "\nCausa: " + erro.getMessage());
		} finally {
			// Fechar a conex�o
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return novaPessoa;

	}

	public boolean atualizarUsuario(Usuario usuarioEditado) {
		boolean statusAtualizacao = false;
		Connection conexao = Banco.getConnection();
		String sql = "UPDATE PESSOA" + " SET IDSTATUS_PESSOA = ?, NOME = ?, CPF = ?, DTNASCIMENTO = ?, SEXO = ?"
				+ " WHERE ID = ?";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, usuarioEditado.getTipoUsuario().getValor());
			query.setString(2, usuarioEditado.getNome());
			query.setString(3, usuarioEditado.getCpf());
			query.setString(4, usuarioEditado.getDataNascimento().toString());
			query.setString(5, usuarioEditado.getSexo());

			query.setInt(6, usuarioEditado.getId());
			statusAtualizacao = query.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar usuario." + "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return statusAtualizacao;
	}

	public Usuario consultarUsuarioId(int idUsuario) {
		Usuario usuarioConsultado = null;
		Connection conexao = Banco.getConnection();
		String sql = "SELECT * FROM PESSOA WHERE ID = ?";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		try {
			query.setInt(1, idUsuario);
			ResultSet resultadoConsulta = query.executeQuery();

			if (resultadoConsulta.next()) {
				usuarioConsultado = new Usuario();
				usuarioConsultado = this.converterResultSetParaEntidade(resultadoConsulta);
			}

		} catch (Exception e) {
			System.out.println("ERRO ao buscar o usuario com id: " + idUsuario + "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return usuarioConsultado;

	}

	public List<Usuario> consultarPessoaTodas() {
		Connection conexao = Banco.getConnection();
		List<Usuario> pessoas = new ArrayList<Usuario>();
		String sql = "SELECT * FROM PESSOA ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Usuario pessoaConsultada = converterResultSetParaEntidade(resultado);
				pessoas.add(pessoaConsultada);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao buscar todos os usuarios." + "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return pessoas;
	}

	private Usuario converterResultSetParaEntidade(ResultSet resultado) throws SQLException {
		Usuario UsuarioConsultado = new Usuario();
		UsuarioConsultado.setId(resultado.getInt("ID"));
		UsuarioConsultado.setTipoUsuario(TipoUsuario.getTipoPessoaPorValor(resultado.getInt("IDSTATUS_PESSOA")));
		UsuarioConsultado.setNome(resultado.getString("NOME"));
		UsuarioConsultado.setCpf(resultado.getString("CPF"));
		UsuarioConsultado.setDataNascimento(
				LocalDate.parse(resultado.getString("DTNASCIMENTO"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		UsuarioConsultado.setSexo(resultado.getString("SEXO"));
		return UsuarioConsultado;

	}

	public boolean excluirUsuario(int idPessoa) {
		boolean statusExclusao = false;

		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM PESSOA WHERE ID = ?";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, idPessoa);
			statusExclusao = query.executeUpdate() > 0;

		} catch (Exception e) {
			System.out.println("ERRO ao excluir usuario.\n" + "Causa " + e.getMessage());

		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return statusExclusao;
	}

}
