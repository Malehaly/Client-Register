package br.com.thiago.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.thiago.loja.fabrica.Fabrica;
import br.com.thiago.loja.model.Municipio;
import br.com.thiago.loja.view.MunicipioFrame;

public class MunicipioDAO implements MainDAO<Municipio>{
	
	private EntityManager em = Fabrica.getEntityManager();

	//Adicionado pelo View-Lista: Acessa bd para popular lista.
	public MunicipioDAO(){
	}
	
	@Override
	public Municipio buscar(Long id) {
		// TODO Auto-generated method stub
        return this.em.find(Municipio.class, id);
	}
	
	@Override
	public void salvar(Municipio municipio){
		if(buscarPorNome(municipio.getNome()) != null || buscarPorCodigo(municipio.getCodigo()) != null){
			JOptionPane.showMessageDialog(null, "Já existe um município com este código ou nome.", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
	    	this.em.getTransaction().begin();
	        this.em.persist(municipio);
	        this.em.flush();
	        this.em.getTransaction().commit();
	        this.em.close();
	        MunicipioFrame.limpar();
            JOptionPane.showMessageDialog(null, "Município salvo!", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void remover(Municipio municipio) {
    	this.em.getTransaction().begin();
        this.em.remove(municipio);
        this.em.flush();
        this.em.getTransaction().commit();
        this.em.close();
	}

	@Override
	public Municipio alterar(Municipio municipio){
    	this.em.getTransaction().begin();
        this.em.merge(municipio);
        this.em.flush();
        this.em.getTransaction().commit();
        this.em.close();
        MunicipioFrame.limpar();
        JOptionPane.showMessageDialog(null, "Município salvo!", "", JOptionPane.INFORMATION_MESSAGE);
        return null;
	}
	
	@Override
	public List<Municipio> listarTodos() {
    	this.em.getTransaction().begin();
        TypedQuery<Municipio> query = em.createQuery("SELECT m FROM Municipio m", Municipio.class);
        this.em.getTransaction().commit();
        return query.getResultList();
	}
	
	public Municipio buscarPorCodigo(Long codigo) {
		try{
			Municipio m = (Municipio) this.em.createNamedQuery("Municipio.findByCodigo").setParameter("codigo", codigo).getSingleResult(); 
			return m;
		} catch (NoResultException nre){
			return null;
		}
	}
	public Municipio buscarPorNome(String nome) {
		try{
			Municipio m = (Municipio) this.em.createNamedQuery("Municipio.findByNome").setParameter("nome", nome.toLowerCase()).getSingleResult(); 
			return m;
		} catch (NoResultException nre){
			return null;
		}
	}

}
