package br.com.thiago.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.thiago.loja.fabrica.Fabrica;
import br.com.thiago.loja.model.Cliente;
import br.com.thiago.loja.view.ClienteFrame;

public class ClienteDAO implements MainDAO<Cliente>{
	
	private EntityManager em = Fabrica.getEntityManager();

	@Override
    public Cliente buscar(Long id){
        return this.em.find(Cliente.class, id);
    }
	
	@Override
	public void salvar(Cliente cliente) {
		if(buscarPorCodigo(cliente.getCodigo()) != null){
			JOptionPane.showMessageDialog(null, "Já existe um cliente com este código.", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
	    	this.em.getTransaction().begin();
	        this.em.merge(cliente);
	        this.em.flush();
	        this.em.getTransaction().commit();
	        this.em.close();
	        ClienteFrame.limpar();
            JOptionPane.showMessageDialog(null, "Cliente salvo!", "", JOptionPane.INFORMATION_MESSAGE);
		}
    }

	@Override
	public void remover(Cliente cliente) {
    	this.em.getTransaction().begin();
        this.em.remove(cliente);
        this.em.flush();
        this.em.getTransaction().commit();
        this.em.close();
	}

	@Override
	public Cliente alterar(Cliente cliente) {
    	this.em.getTransaction().begin();
        this.em.merge(cliente);
        this.em.flush();
        this.em.getTransaction().commit();
        this.em.close();
        ClienteFrame.limpar();
        JOptionPane.showMessageDialog(null, "Cliente salvo!", "", JOptionPane.INFORMATION_MESSAGE);
		return null;
	}

	@Override
	public List<Cliente> listarTodos() {
        TypedQuery<Cliente> query = em.createQuery("SELECT m FROM Cliente m", Cliente.class);
        return query.getResultList();
	}
	
	public Cliente buscarPorCodigo(Long codigo) {
		try{
			Cliente c = (Cliente) this.em.createNamedQuery("Cliente.findByCodigo").setParameter("codigo", codigo).getSingleResult(); 
			return c;
		} catch (NoResultException nre){
			return null;
		}
	}
	
}
