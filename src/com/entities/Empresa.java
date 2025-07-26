package com.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

	public Produto cadastrar() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Id: ");
		Long id = sc.nextLong();
		System.out.print("1: GRUPO A \n2: GRUPO B \n3: GRUPO C \n: ");
		int x = sc.nextInt();
		sc.nextLine();
		System.out.print("Produto: ");
		String produto = sc.nextLine();
		System.out.print("Valor: R$");
		double valor = sc.nextDouble();
		System.out.print("Quantidade: ");
		int quantidade = sc.nextInt();

		Produto p = new Produto(id, produto, valor, quantidade);

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
	

	public void faturar(Long id, int quantidade, Produto p) {

		if (id.equals(p.getId())) {
			p.setQuantidade(p.getQuantidade() - quantidade);
			saldo = saldo + (p.getValor() * quantidade);

		} else {
			System.out.println("Id inválido");
		}
	}

	@Override
	public String toString() {
		return estoque.toString();
	}
	
	

}
