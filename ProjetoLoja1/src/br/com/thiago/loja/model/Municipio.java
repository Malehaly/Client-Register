package br.com.thiago.loja.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value={@NamedQuery(name = "Municipio.findByCodigo", query = "SELECT f FROM Municipio f WHERE f.codigo = :codigo"),
		@NamedQuery(name = "Municipio.findByNome", query = "SELECT f FROM Municipio f WHERE f.nome = lower(:nome)")})
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="codigo", length = 6, unique = true)
	private Long codigo;
	
	@Column(name="nome", length = 30, unique = true)
	private String nome;
	
	@Column(name="estado", length = 2)
	private String estado;
	
	public Municipio() {
	}
	public Municipio(Long codigo, String nome, String estado) {
		this.codigo = codigo;
		this.nome = nome;
		this.estado = estado;
	}
	public Municipio(Long id, Long codigo, String nome, String estado) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.estado = estado;
	}
	
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
