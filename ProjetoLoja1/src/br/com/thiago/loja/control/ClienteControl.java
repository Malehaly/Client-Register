package br.com.thiago.loja.control;

import br.com.thiago.loja.dao.ClienteDAO;
import br.com.thiago.loja.model.Cliente;
import br.com.thiago.loja.model.Municipio;

import java.util.List;

import javax.swing.JOptionPane;

public class ClienteControl  {

    public static void salvar(Long codigo, String nome, String endereco, Integer numero, 
    		String bairro, String cep, Municipio municipio) throws Exception {
        Cliente c = new Cliente(codigo, nome, endereco, numero, bairro, cep, municipio);
	    new ClienteDAO().salvar(c);
    }

    public static void alterar(Long id, Long codigo, String nome, String endereco, Integer numero, 
    		String bairro, String cep, Municipio municipio) throws Exception {
        Cliente c = new ClienteDAO().buscar(id);
        Cliente cTesteCodigo = new ClienteDAO().buscarPorCodigo(codigo);
        
        if(cTesteCodigo != null){
        	if(cTesteCodigo.getId() == c.getId()){
            c.setCodigo(codigo);
            c.setNome(nome);
            c.setEndereco(endereco);
            c.setNumero(numero);
            c.setBairro(bairro);
            c.setCep(cep);
            c.setMunicipio(municipio);
            new ClienteDAO().alterar(c);
        	} else {
    			JOptionPane.showMessageDialog(null, "Já existe um cliente com este código.", "", JOptionPane.INFORMATION_MESSAGE);
        	}
        } else {
            c.setCodigo(codigo);
            c.setNome(nome);
            c.setEndereco(endereco);
            c.setNumero(numero);
            c.setBairro(bairro);
            c.setCep(cep);
            c.setMunicipio(municipio);
            new ClienteDAO().alterar(c);
        }
    }
    
    public static void remover(Long id) throws Exception {
		ClienteDAO dao= new ClienteDAO();
		Cliente cliente = dao.buscar(id);
    	dao.remover(cliente);
    }
    
    public static Cliente buscar(Long id) throws Exception {
        ClienteDAO dao = new ClienteDAO();
        return dao.buscar(id);
    }

    public List<Cliente> listarTodos() throws Exception {
        ClienteDAO dao = new ClienteDAO();
            return dao.listarTodos();
    }
}