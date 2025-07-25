package com.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vendedor {

	public Produto vender() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Id produto: ");
		Long id = sc.nextLong();
		System.out.println("Quantidade: ");
		int quantidade = sc.nextInt();

		Produto venda = new Produto(id, quantidade);

		return venda;
	}

}
