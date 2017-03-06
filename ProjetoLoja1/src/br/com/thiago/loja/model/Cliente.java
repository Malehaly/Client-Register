package br.com.thiago.loja.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Cliente.findByCodigo", query = "SELECT f FROM Cliente f WHERE f.codigo = :codigo")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="codigo", length = 6, unique = true)
	private Long codigo;
	
	@Column(name="nome", length = 30)
	private String nome;
	
	@Column(name="endereco", length = 30)
	private String endereco;
	
	@Column(name="numero", length = 8)
	private Integer numero;
	
	@Column(name="bairro", length = 20)
	private String bairro;
	
	@Column(name="cep", length = 10)
	private String cep;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Municipio municipio;
		
	public Cliente(){
	}	
	public Cliente(Long codigo, String nome, String endereco, Integer numero, String bairro, String cep,
			Municipio municipio) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.municipio = municipio;
	}	
	public Cliente(Long id, Long codigo, String nome, String endereco, Integer numero, String bairro, String cep,
			Municipio municipio) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.municipio = municipio;
	}	
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}	
	
}
