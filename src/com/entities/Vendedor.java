package com.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Vendedor {
	
	private static long contadorId = 1;
	private Long id;
	private String nome;
	List<Pedido> pedido = new ArrayList<Pedido>();
	
	public Vendedor() {
		
	}
	
	public Vendedor(String nome) {
		this.id = contadorId++;
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void addPedido(Pedido p) {
		pedido.add(p);
	}
	

}
