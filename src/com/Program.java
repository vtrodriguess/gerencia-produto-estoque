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

		Scanner sc = new Scanner(System.in);

		Set<Produto> listaProduto = new HashSet<>();
		List<Pedido> pedidos = new ArrayList<>();
		List<Vendedor> vendedores = new ArrayList<>();
		Estoque estoque = new Estoque();
		Empresa empresa = new Empresa();
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
					menuEmpresa(sc, listaProduto, estoque, empresa, pedidos);
					break;
				} else {
					System.out.println("Senha inválida para acesso a Empresa");
					break;
				}
			case 2:
				System.out.println("Senha: ");
				int senhaVendedor = sc.nextInt();
				if (senhaVendedor == 123) {
					menuVendedor(sc, pedidos, pedido, listaProduto, vendedores);
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

	public static void menuEmpresa(Scanner sc, Set<Produto> listaProdutos, Estoque estoque, Empresa empresa, List<Pedido> pedidos) {
		
		System.out.println("1-Cadastrar Produto \n2-Estoque \n3-Faturar");
		int subMenu = sc.nextInt();
		if (subMenu == 1) {
			cadastrarProduto(listaProdutos, estoque, empresa);
		} else if (subMenu == 2) {
			System.out.println(empresa);
			System.out.println("Saldo: " + empresa.getSaldo());
		} else if (subMenu == 3) {
			faturamento(sc, pedidos, listaProdutos, empresa);
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

	public static void cadastrarProduto(Set<Produto> listaProdutos, Estoque estoque, Empresa empresa) {
		Produto novoProduto = empresa.cadastrar();
		listaProdutos.add(novoProduto);
		estoque.adicionar(novoProduto);

	}

	public static void faturamento(Scanner sc, List<Pedido> pedidos, Set<Produto> listaProdutos, Empresa empresa) {
		if (pedidos.isEmpty()) {
			System.out.println("sem produto");
		} else {
			for (int i = 0; i < pedidos.size(); i++) {
				System.out.println("Posição do pedido: " + i + "\n" + pedidos.get(i));
			}
			System.out.println("Numero do pedido: ");
			int numeroPedido = sc.nextInt();

			empresa.faturar(pedidos, listaProdutos, numeroPedido);
		}

	}

	public static void mostrarEstoque(Estoque estoque, Set<Produto> listaProdutos) {

		if (listaProdutos.isEmpty()) {
			System.out.println("Estoque vazio");

		} else {
			System.out.println(estoque);

		}

	}

	public static void menuVendedor(Scanner sc, List<Pedido> pedidos, Pedido p, Set<Produto> listaProduto,
			List<Vendedor> vendedores) {

		System.out.println("1 - Cadastrar Vendedor \n2 - Gerar Pedido de Venda");
		int menu = sc.nextInt();

		if (menu == 1) {
			cadastrarVendedor(sc, vendedores);

		} else if (menu == 2) {
			cadastrarPedido(sc, pedidos, vendedores, listaProduto);
		}
	}

	public static void cadastrarPedido(Scanner sc, List<Pedido> pedidos, List<Vendedor> vendedores, Set<Produto> listaProduto) {
		Pedido novoPedido = new Pedido();
		Vendedor vendedorSelecionado = null;
		int menu = 0;
		do {

			System.out.println("ID do Vendedor: ");
			Long idVendedor = sc.nextLong();

			for (Vendedor vendedor : vendedores) {
				if (vendedor.getId().equals(idVendedor)) {
					vendedorSelecionado = vendedor;
					ItemPedido produto = novoPedido.dadosPedido(listaProduto);
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
