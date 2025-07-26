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
	    StringBuilder sb = new StringBuilder();
	    for (Produto p : produto) {
	        sb.append(p.toString());
	        sb.append("-------------------------\n");
	    }
	    return sb.toString();
	}
	
	


}
