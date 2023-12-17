package com.agencia.viagem.model;


import java.util.HashSet;
import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "destino")
public class Destino {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String imgUrl;
	private String estado;
	private String pais;
	private String valor;
	private String dataInicio;
	private String dataFim;
	
	
	@ManyToMany(mappedBy = "destinos", cascade = {CascadeType.ALL})
	private Set<Cliente> clientes = new HashSet<>();
	
	public Destino() {
	}

	
	
	public Destino(String dataInicio, String dataFim) {
		super();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}



	public Destino(Long id, String nome, String imgUrl, String estado, String pais, String data, String valor,
			Set<Cliente> clientes) {
		super();
		this.id = id;
		this.nome = nome;
		this.imgUrl = imgUrl;
		this.estado = estado;
		this.pais = pais;
		this.valor = valor;
		this.clientes = clientes;
	}

	public Long getId() {
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	

    // Outros atributos e métodos

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }
	
    public String getDataFim() {
        return dataInicio;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
}
