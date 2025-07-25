package com.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.enums.GrupoProduto;

public class Empresa {

	List<Estoque> estoque = new ArrayList<Estoque>();

	public Produto cadastrar(int grupo) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Id: ");
		Long id = sc.nextLong();
		sc.nextLine();
		System.out.println("Produto: ");
		String produto = sc.nextLine();
		System.out.println("Valor: ");
		double valor = sc.nextDouble();
		System.out.println("Quantidade: ");
		int quantidade = sc.nextInt();

		Produto p = new Produto(id, produto, valor, quantidade);

		if (grupo == 1) {
			p.setGp(GrupoProduto.A);
		} else if (grupo == 2) {
			p.setGp(GrupoProduto.B);
		} else if (grupo == 3) {
			p.setGp(GrupoProduto.C);
		} else {
			System.out.println("Invalido");
		}

		return p;

	}

	public void faturar(Long id, int quantidade, Produto p) {

		if (id.equals(p.getId())) {
			p.setQuantidade(p.getQuantidade() - quantidade);

		} else {
			System.out.println("Id inválido");
		}
	}

}
