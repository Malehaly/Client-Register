package br.com.thiago.loja.dao;

import java.util.List;

public interface MainDAO<ENTITY> {
	
	public void salvar(ENTITY entidade);
	
	public void remover(ENTITY entidade);
	
	public ENTITY alterar(ENTITY entidade);
	
	public ENTITY buscar(Long number);
	
	public List<ENTITY> listarTodos();
}
