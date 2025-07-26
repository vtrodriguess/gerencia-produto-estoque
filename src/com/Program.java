package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.entities.Empresa;
import com.entities.Estoque;
import com.entities.Pedido;
import com.entities.Produto;
import com.entities.Vendedor;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Set<Produto> listaProduto = new HashSet<>();
		List<Produto> pendencia = new ArrayList<>();
		List<Pedido> pedidos = new ArrayList<>();
		Estoque estoque = new Estoque();
		Produto produto = new Produto();
		Empresa empresa = new Empresa();
		Vendedor vend = new Vendedor();
		empresa.setEstoque(estoque);

		int menu = 0;

		do {

			System.out.println("1-Empresa \n2-Vendedor");
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				System.out.println("1-Cadastrar Produto \n2-Estoque \n3-Faturar");
				int subMenu = sc.nextInt();
				if (subMenu == 1) {
					cadastrarProduto(listaProduto, estoque, produto, empresa);
					break;
				} else if (subMenu == 2) {
					System.out.println(empresa);
					System.out.println("Saldo: " + empresa.getSaldo());
					break;
				} else if (subMenu == 3) {
					faturamento(pendencia, listaProduto, empresa);
					break;

				} else {
					System.out.println("Opção inválida");
					break;
				}
			case 2:
				mostrarEstoque(estoque, listaProduto);
				break;
			case 3:
				Pedido novoPedido = new Pedido();
				cadastrarPedido(pedidos, novoPedido, listaProduto);
				break;
			case 4:
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + menu);
			}

		} while (menu != 4);

	}

	public static void cadastrarProduto(Set<Produto> listaProdutos, Estoque estoque, Produto produto, Empresa empresa) {
		Scanner sc = new Scanner(System.in);

		Produto novoProduto = empresa.cadastrar();
		listaProdutos.add(novoProduto);
		estoque.adicionar(novoProduto);

	}

	public static void faturamento(List<Produto> pendencia, Set<Produto> listaProdutos, Empresa empresa) {
		Scanner sc = new Scanner(System.in);

		if (pendencia.isEmpty()) {
			System.out.println("sem produto");
		} else {
			for (int i = 0; i < pendencia.size(); i++) {
				System.out.println("Posição do pedido: " + i + "\n" + pendencia.get(i));
			}

			System.out.println("Pedido");
			int pedido = sc.nextInt();

			Long id = pendencia.get(pedido).getId();
			int quantidade = pendencia.get(pedido).getQuantidade();

			boolean encontrado = false;
			for (Produto p : listaProdutos) {
				if (p.getId().equals(id)) {
					empresa.faturar(id, quantidade, p);
					encontrado = true;
					break;
				}
			}
			if (!encontrado) {
				System.out.println("Pedido nao encontrado");
			}
			pendencia.remove(pedido);
		}

	}

	public static void mostrarEstoque(Estoque estoque, Set<Produto> listaProdutos) {

		if (listaProdutos.isEmpty()) {
			System.out.println("Estoque vazio");

		} else {
			System.out.println(estoque);

		}

	}

	public static void cadastrarPedido(List<Pedido> pedidos, Pedido p, Set<Produto> listaProduto) {
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		do {
			Produto produto = p.dadosPedido(listaProduto);
			p.addProduto(produto);
			System.out.println("Adicionar mais produto? \n1-SIM \n2-NAO");
			menu = sc.nextInt();

		} while (menu != 2);

		pedidos.add(p);
	}

}
