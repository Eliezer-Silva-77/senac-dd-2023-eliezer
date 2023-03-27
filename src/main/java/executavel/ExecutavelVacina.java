package executavel;

import java.time.LocalDate;

import model.dao.vacina.UsuarioDAO;
import model.dao.vacina.VacinaDAO;
import model.vo.vacina.EstagioPesquisa;
import model.vo.vacina.Usuario;
import model.vo.vacina.TipoUsuario;
import model.vo.vacina.Vacina;

public class ExecutavelVacina {
	public static void main(String[] args) {
		// Teste Cadastrar Pessoa
		Usuario novaPessoa  = new Usuario(null, "Lucas Capua","093474515", LocalDate.of(2005,02,16), "M", TipoUsuario.GERAL);
		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.cadastrarPessoaDAO(novaPessoa);
	
////		//Teste Cadastrar Vacina
		Vacina novaVacina = new Vacina(null, usuarioDao.consultarUsuarioId(2), "Brasil", EstagioPesquisa.APLICACAO_EM_MASSA, LocalDate.now());
		VacinaDAO vacinaDao = new VacinaDAO();
		vacinaDao.cadastrarVacinaDAO(novaVacina);
		
		System.out.println(vacinaDao.consultarVacinaId(1));

//		Teste Consultas
		for (Vacina vacina : vacinaDao.consultarVacinaTodas()) {
			System.out.println(vacina.getPesquisador().getNome());

		}
//		System.out.print(usuarioDao.consultarUsuarioId(1));
//		
//		Teste exclusão
//		System.out.println(usuarioDao.excluirUsuario(1));

	}
}
