package com.entities;

import java.util.ArrayList;
import java.util.List;

public class Pedidos {
	
	List<Produto> produtos = new ArrayList<>();
	
	public void addProduto(Produto p) {
		produtos.add(p);
	}

}
