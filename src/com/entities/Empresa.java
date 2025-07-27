package com.entities;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.enums.GrupoProduto;

public class Empresa {

	private Estoque estoque;
	private double saldo;
	
	public void setEstoque(Estoque estoque) {
	    this.estoque = estoque;
	}

	public Estoque getEstoque() {
	    return estoque;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public Produto cadastrar(String produto, double valor, int quantidade, int x) {
		Produto p = new Produto(produto, valor, quantidade);

		if (x == 1) {
			p.setGp(GrupoProduto.A);
		} else if (x == 2) {
			p.setGp(GrupoProduto.B);
		} else if (x == 3) {
			p.setGp(GrupoProduto.C);
		} else {
			System.out.println("Invalido");
		}

		return p;

	}
	

	public void reporEstoque(Long id, int quantidade, Produto p) {
		if(id.equals(p.getId())) {
			p.setQuantidade(p.getQuantidade() + quantidade);
		}
		else {
			System.out.println("Id inválido");
		}
	}
	
	public void faturar(List<Pedido> pedidos, Set<Produto> listaProdutos, int numeroPedido) {
		Pedido pedido = new Pedido();
		pedido = pedidos.get(numeroPedido);
		for (Produto p : listaProdutos) {
			for (ItemPedido produtosPedido : pedido.getPedidos()) {
				Long id = produtosPedido.getProduto().getId();
				int quantidade = produtosPedido.getQuantidade();

				if (p.getId().equals(id)) {
					p.setQuantidade(p.getQuantidade() - quantidade);
					saldo = saldo + (p.getValor() * quantidade);
				}
			}
		}
		pedidos.remove(pedido);
		System.out.println("Pedido faturado com sucesso");
	}

	@Override
	public String toString() {
		return estoque.toString();
	}
	
	

}
