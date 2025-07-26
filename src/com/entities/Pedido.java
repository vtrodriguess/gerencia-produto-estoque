package com.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Pedido {
	
	List<ItemPedido> pedidos = new ArrayList<>();
	
	public void addProduto(ItemPedido p) {
		pedidos.add(p);
	}
	
	
	
	public List<ItemPedido> getPedidos() {
		return pedidos;
	}



	public void setPedidos(List<ItemPedido> pedidos) {
		this.pedidos = pedidos;
	}



	public ItemPedido dadosPedido(Set<Produto> listaProdutos) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Id produto: ");
		Long id = sc.nextLong();
		System.out.println("Quantidade: ");
		int quantidade = sc.nextInt();

		for (Produto p : listaProdutos) {
			if (p.getId().equals(id)) {
				if (p.getQuantidade() >= quantidade) {
					return new ItemPedido(p, quantidade);
					//Produto a = new Produto(p.getId(), p.getProduto(), p.getGp(), p.getValor(), quantidade);
					//return a;
				}else {
					System.out.println("Estoque insuficiente. Disponível: " + p.getQuantidade());
					return null;
				}
			}
		}

		System.out.println("ID DE PRODUTO INVÁLIDO");

		return null;

	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for (ItemPedido p : pedidos) {
	        sb.append(p.toString());
	        sb.append("-------------------------\n");
	    }
	    return sb.toString();
	}

}
