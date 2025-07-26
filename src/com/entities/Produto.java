package com.entities;

import java.util.Objects;

import com.enums.GrupoProduto;

public class Produto {

	private static long contadorId = 1;
	private Long id;
	private String produto;
	private GrupoProduto gp;
	private double valor;
	private int quantidade;

	public Produto() {

	}
	
	public Produto(String produto, double valor, int quantidade) {
		this.id = contadorId++;
		this.produto = produto;
		this.valor = valor;
		this.quantidade = quantidade;
	}

	public Produto(Long id, int quantidade) {
		super();
		this.id = id;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public GrupoProduto getGp() {
		return gp;
	}

	public void setGp(GrupoProduto gp) {
		this.gp = gp;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Produtos \nID: " + id + " | Nome: " + produto + " | Grupo: " + gp + " | Valor: R$" + valor
				+ " | Quantidade: " + quantidade + "\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, produto);
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
		return Objects.equals(id, other.id) && Objects.equals(produto, other.produto);
	}

}
