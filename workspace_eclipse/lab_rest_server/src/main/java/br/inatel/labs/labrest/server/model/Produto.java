package br.inatel.labs.labrest.server.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto {

	private Long id;
	
	private String descrisao;
	
	private BigDecimal preco;

	// construtores
	public Produto(Long id, String descrisao, BigDecimal preco) {
		super();
		this.id = id;
		this.descrisao = descrisao;
		this.preco = preco;
	}
	
	public Produto() {
		
	}
	
	//acessores...
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrisao() {
		return descrisao;
	}

	public void setDescrisao(String descrisao) {
		this.descrisao = descrisao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
}
