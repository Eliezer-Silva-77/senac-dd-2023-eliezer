package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.dao.Banco;
import model.vo.telefonia.Telefone;

public class TelefoneDAO {
	public Telefone inserirTelefone(Telefone novoTelefone) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO TELEFONE (idCliente, DDD, NUMERO, ATIVO, MOVEL)"
				+ "VALUES (?,?,?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		try {
			query.setLong(1, novoTelefone.getIdCliente());
			query.setString(2, novoTelefone.getDdd());
			query.setString(3, novoTelefone.getNumero());
			query.setBoolean(4, novoTelefone.isAtivo());
			query.setBoolean(5, novoTelefone.isMovel());
			
			ResultSet resultado = query.getGeneratedKeys();
			if(resultado.next()) {
				novoTelefone.setId(resultado.getInt(1));
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao inserir o Telefone /nMotivo: " + e.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return novoTelefone;
		
	}
}
