package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.entities.Empresa;
import com.entities.Estoque;
import com.entities.ItemPedido;
import com.entities.Pedido;
import com.entities.Produto;
import com.entities.Vendedor;

public class Program {

	public static void main(String[] args) {

		iniciarSistema();

	}

	public static void iniciarSistema() {
		Scanner sc = new Scanner(System.in);
		
		final int SENHA_EMPRESA = 1234;
		final int SENHA_VENDEDOR = 123;

		Set<Produto> produtosCadastrados = new HashSet<>();
		List<Pedido> pedidos = new ArrayList<>();
		List<Vendedor> vendedores = new ArrayList<>();
		Estoque estoque = new Estoque();
		Empresa empresa = new Empresa();
		empresa.setEstoque(estoque);

		int menu = 0;

		do {

			System.out.println("1 - Empresa \n2 - Vendedor \n3 - Estoque");
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				System.out.println("Senha: ");
				int senhaEmp = sc.nextInt();
				if (senhaEmp == SENHA_EMPRESA) {
					menuEmpresa(sc, produtosCadastrados, estoque, empresa, pedidos);
					break;
				} else {
					System.out.println("Senha inválida para acesso a Empresa");
					break;
				}
			case 2:
				System.out.println("Senha: ");
				int senhaVen = sc.nextInt();
				if (senhaVen == SENHA_VENDEDOR ) {
					menuVendedor(sc, pedidos, produtosCadastrados, vendedores);
					break;
				} else {
					System.out.println("Senha inválida para acesso a área de vendedor");
					break;
				}
			case 3:
				mostrarEstoque(estoque, produtosCadastrados);
				break;
			case 4:
				System.out.println("Encerrando");
			default:
				throw new IllegalArgumentException("Unexpected value: " + menu);
			}

		} while (menu != 4);

	}

	public static void menuEmpresa(Scanner sc, Set<Produto> produtosCadastrados, Estoque estoque, Empresa empresa,
			List<Pedido> pedidos) {

		System.out.println("1-Cadastrar Produto \n2-Estoque \n3-Faturar");
		int subMenu = sc.nextInt();
		if (subMenu == 1) {
			cadastrarProduto(produtosCadastrados, estoque, empresa);
		} else if (subMenu == 2) {
			System.out.println(empresa);
			System.out.println("Saldo: " + empresa.getSaldo());
		} else if (subMenu == 3) {
			faturamento(sc, pedidos, produtosCadastrados, empresa);
		} else if (subMenu == 4) {
			System.out.println("Id: ");
			Long id = sc.nextLong();
			System.out.println("Quantidade: ");
			int quantidade = sc.nextInt();

			for (Produto reposicaoEstoque : produtosCadastrados) {
				empresa.reporEstoque(id, quantidade, reposicaoEstoque);
			}

		} else {
			System.out.println("Opção inválida");

		}
	}

	public static void cadastrarProduto(Set<Produto> produtosCadastrados, Estoque estoque, Empresa empresa) {
		Scanner sc = new Scanner(System.in);
		System.out.print("1: GRUPO A \n2: GRUPO B \n3: GRUPO C \n: ");
		int x = sc.nextInt();
		sc.nextLine();
		System.out.print("Produto: ");
		String produto = sc.nextLine();
		System.out.print("Valor: R$");
		double valor = sc.nextDouble();
		System.out.print("Quantidade: ");
		int quantidade = sc.nextInt();
		
		Produto novoProduto = empresa.cadastrar(produto, valor, quantidade, x);
		produtosCadastrados.add(novoProduto);
		estoque.adicionar(novoProduto);

	}

	public static void faturamento(Scanner sc, List<Pedido> pedidos, Set<Produto> produtosCadastrados,
			Empresa empresa) {
		if (pedidos.isEmpty()) {
			System.out.println("sem produto");
		} else {
			for (int i = 0; i < pedidos.size(); i++) {
				System.out.println("Posição do pedido: " + i + "\n" + pedidos.get(i));
			}
			System.out.println("Numero do pedido: ");
			int numeroPedido = sc.nextInt();

			empresa.faturar(pedidos, produtosCadastrados, numeroPedido);
		}

	}

	public static void mostrarEstoque(Estoque estoque, Set<Produto> produtosCadastrados) {

		if (produtosCadastrados.isEmpty()) {
			System.out.println("Estoque vazio");

		} else {
			System.out.println(estoque);

		}

	}

	public static void menuVendedor(Scanner sc, List<Pedido> pedidos, Set<Produto> produtosCadastrados,
			List<Vendedor> vendedores) {

		System.out.println("1 - Cadastrar Vendedor \n2 - Gerar Pedido de Venda");
		int menu = sc.nextInt();

		if (menu == 1) {
			cadastrarVendedor(sc, vendedores);

		} else if (menu == 2) {
			cadastrarPedido(sc, pedidos, vendedores, produtosCadastrados);
		}
	}

	public static void cadastrarPedido(Scanner sc, List<Pedido> pedidos, List<Vendedor> vendedores,
			Set<Produto> produtosCadastrados) {
		Pedido novoPedido = new Pedido();
		Vendedor vendedorSelecionado = null;
		int menu = 0;
		do {

			System.out.println("ID do Vendedor: ");
			Long idVendedor = sc.nextLong();

			for (Vendedor vendedor : vendedores) {
				if (vendedor.getId().equals(idVendedor)) {
					vendedorSelecionado = vendedor;
					ItemPedido produto = novoPedido.dadosPedido(produtosCadastrados);
					if (produto != null) {
						novoPedido.addProduto(produto);
					}
				}
			}

			System.out.println("Adicionar mais produto? \n1-SIM \n2-NAO");
			menu = sc.nextInt();

		} while (menu != 2);

		novoPedido.setVendedor(vendedorSelecionado);
		vendedorSelecionado.addPedido(novoPedido);
		pedidos.add(novoPedido);
	}

	public static void cadastrarVendedor(Scanner sc, List<Vendedor> vendedores) {
		sc.nextLine();
		System.out.println("Nome Vendedor: ");
		String nome = sc.nextLine();
		vendedores.add(new Vendedor(nome));
	}

}
