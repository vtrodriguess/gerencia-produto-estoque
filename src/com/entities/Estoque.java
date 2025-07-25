package com.entities;

import java.util.ArrayList;
import java.util.List;

public class Estoque  {

	List<Produto> produto = new ArrayList<Produto>();
	
	public void adicionar(Produto p) {
		produto.add(p);
	}
	
	public void vender() {
		
	}

	@Override
	public String toString() {
		return "Estoque " + produto;
	}
	
	


}
