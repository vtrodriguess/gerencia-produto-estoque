package com.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Vendedor {

	public Produto vender(Set<Produto> listaProdutos) {
		
		List<Pedidos> pedidos = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		System.out.println("Id produto: ");
		Long id = sc.nextLong();
		System.out.println("Quantidade: ");
		int quantidade = sc.nextInt();

		for (Produto p : listaProdutos) {
			if (p.getId().equals(id)) {
				if (p.getQuantidade() >= quantidade) {
					return new Produto(id, p.getProduto(), p.getGp(), p.getValor(), quantidade);
				}else {
					System.out.println("Estoque insuficiente. Disponível: " + p.getQuantidade());
					return null;
				}
			}
		}

		System.out.println("ID DE PRODUTO INVÁLIDO");

		return null;

	}

}
