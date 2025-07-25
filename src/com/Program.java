package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.entities.Empresa;
import com.entities.Estoque;
import com.entities.Produto;
import com.entities.Vendedor;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Set<Produto> list = new HashSet<>();
		List<Produto> pendencia = new ArrayList<>();
		Estoque estoque = new Estoque();
		Produto produto = new Produto();
		Empresa empresa = new Empresa();
		Vendedor vendedor = new Vendedor();

		int menu = 0;

		do {

			System.out.println("1-Empresa \n2-Vendedor");
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				empresa(list, estoque, produto, empresa, pendencia);
				break;
			case 2:
				vendedor(estoque, pendencia, vendedor);
				break;
			case 3:
				System.out.println("Saindo");
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + menu);
			}

		} while (menu != 3);

	}

	public static void empresa(Set<Produto> list, Estoque estoque, Produto produto, Empresa empresa,
			List<Produto> pendencia) {
		Scanner sc = new Scanner(System.in);

		int menu = 0;

		do {

			System.out.println("1-Cadastrar Produto \n2-Faturar");
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				System.out.println("1-GRUPO A \n2-GRUPO B \n3-GRUPO C");
				int grupo = sc.nextInt();
				Produto novoProduto = empresa.cadastrar(grupo);
				list.add(novoProduto);
				estoque.adicionar(novoProduto);
				break;
			case 2:
				if(pendencia.isEmpty()) {
					System.out.println("sem produto");
				}
				else {
				for(int i=0; i < pendencia.size(); i++) {
					System.out.println(i + " | " + pendencia.get(i));
				}

				System.out.println("Pedido");
				int pedido = sc.nextInt();
				
				Long id = pendencia.get(pedido).getId();
				int quantidade = pendencia.get(pedido).getQuantidade();

				boolean encontrado = false;
				for (Produto p : list) {
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
				break;
			case 3:
				System.out.println(estoque);
				break;
			case 4:
				System.out.println("retornando");
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + menu);
			}

		}while(menu!=4);System.out.println(list);

	}

	public static void vendedor(Estoque estoque, List<Produto> pendencia, Vendedor vendedor) {
		Scanner sc = new Scanner(System.in);

		int menu = 0;

		do {

			System.out.println("1-Estoque \n2-Vender");
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				System.out.println(estoque);
				break;
			case 2:
				Produto pr = vendedor.vender();	
				pendencia.add(pr);
				break;
			case 3:
				System.out.println();
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + menu);
			}

		} while (menu != 3);
	}

}
