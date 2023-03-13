package executavel;

import java.util.ArrayList;
import java.util.List;

import model.dao.telefonia.EnderecoDAO;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Endereco;
import model.vo.telefonia.Telefone;

public class ExecutavelTelefonia {

	public static void main(String[] args) {
		
		List<Telefone> telefonesDoPele = null;
		Endereco endereco1 = new Endereco("0001123123", "Mauro Ramos", "10", "SC", "Florianopolis", "Centro");
		EnderecoDAO salvarEnderecos = new EnderecoDAO();
		salvarEnderecos.inserir(endereco1);
		
		if(endereco1.getId() != null) {
			System.out.println("Novo endereço cadastrado");
		} else {
			System.out.println("erro");
		}
		Cliente pele = new Cliente("Edson Aranes","111222333444", telefonesDoPele, true, endereco1 );
		
		List<Telefone> telefonesDoSocrates = new ArrayList<Telefone>();
		Cliente socrates = new Cliente("Sócrates Brasileiro","33323334443", telefonesDoSocrates, true, endereco1 );
		telefonesDoSocrates.add(new Telefone("48", "8451-2364", true, true));
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(socrates);
		clientes.add(pele);
		
		System.out.println("--------------Clientes da Firma--------------");
		
		for(Cliente c: clientes) {
			System.out.println(c.toString());
		}
	}

}
