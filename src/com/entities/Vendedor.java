package com.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Vendedor {

	public Produto vender(Set<Produto> listaProdutos) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Id produto: ");
		Long id = sc.nextLong();
		System.out.println("Quantidade: ");
		int quantidade = sc.nextInt();

		for (Produto p : listaProdutos) {
			if (p.getId().equals(id)) {
				return new Produto(id, quantidade);
			}
		}

		System.out.println("ID DE PRODUTO INVÁLIDO");

		return null;

	}

}
