package com.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Estoque {

	private Set<Produto> produto =  new HashSet<>();

	public Set<Produto> getProduto() {
		return produto;
	}

	public void setProduto(Set<Produto> produto) {
		this.produto = produto;
	}

	public void adicionar(Produto p) {
		produto.add(p);
	}

	public void vender() {

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Produto p : produto) {
			sb.append(p.toString());
			sb.append("-------------------------\n");
		}
		return sb.toString();
	}

}
