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
		List<Pedido> pedidos = new ArrayList<>();
		Estoque estoque = new Estoque();
		Produto produto = new Produto();
		Empresa empresa = new Empresa();
		Vendedor vend = new Vendedor();
		Pedido pedido = new Pedido();
		empresa.setEstoque(estoque);

		int menu = 0;

		do {

			System.out.println("1 - Empresa \n2 - Vendedor \n3 - Estoque");
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				System.out.println("Senha: ");
				int senhaEmpresa = sc.nextInt();
				if (senhaEmpresa == 1234) {
					menuEmpresa(sc, listaProduto, estoque, produto, empresa, pedidos, pedido);
					break;
				} else {
					System.out.println("Senha inválida para acesso a Empresa");
					break;
				}
			case 2:
				System.out.println("Senha: ");
				int senhaVendedor = sc.nextInt();
				if (senhaVendedor == 123) {
					Pedido novoPedido = new Pedido();
					cadastrarPedido(pedidos, novoPedido, listaProduto);
					break;
				} else {
					System.out.println("Senha inválida para acesso a área de vendedor");
					break;
				}
			case 3:
				mostrarEstoque(estoque, listaProduto);
				break;
			case 4:
				System.out.println("Encerrando");
			default:
				throw new IllegalArgumentException("Unexpected value: " + menu);
			}

		} while (menu != 4);

	}

	public static void menuEmpresa(Scanner sc, Set<Produto> listaProdutos, Estoque estoque, Produto produto,
			Empresa empresa, List<Pedido> pedidos, Pedido pedido) {
		System.out.println("1-Cadastrar Produto \n2-Estoque \n3-Faturar");
		int subMenu = sc.nextInt();
		if (subMenu == 1) {
			cadastrarProduto(listaProdutos, estoque, produto, empresa);
		} else if (subMenu == 2) {
			System.out.println(empresa);
			System.out.println("Saldo: " + empresa.getSaldo());
		} else if (subMenu == 3) {
			faturamento(pedidos, listaProdutos, empresa, pedido);
		} else if (subMenu == 4) {
			System.out.println("Id: ");
			Long id = sc.nextLong();
			System.out.println("Quantidade: ");
			int quantidade = sc.nextInt();
			for (Produto reposicaoEstoque : listaProdutos) {
				empresa.reporEstoque(id, quantidade, reposicaoEstoque);
			}

		} else {
			System.out.println("Opção inválida");

		}
	}

	public static void cadastrarProduto(Set<Produto> listaProdutos, Estoque estoque, Produto produto, Empresa empresa) {
		Scanner sc = new Scanner(System.in);

		Produto novoProduto = empresa.cadastrar();
		listaProdutos.add(novoProduto);
		estoque.adicionar(novoProduto);

	}

	public static void faturamento(List<Pedido> pedidos, Set<Produto> listaProdutos, Empresa empresa, Pedido pedido) {
		Scanner sc = new Scanner(System.in);

		if (pedidos.isEmpty()) {
			System.out.println("sem produto");
		} else {
			for (int i = 0; i < pedidos.size(); i++) {
				System.out.println("Posição do pedido: " + i + "\n" + pedidos.get(i));
			}
			System.out.println("Pedido: ");
			int numeroPedido = sc.nextInt();

			empresa.faturar(pedido, pedidos, listaProdutos, numeroPedido);
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
