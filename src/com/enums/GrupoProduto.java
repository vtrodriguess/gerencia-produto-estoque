package com.enums;

public enum GrupoProduto {
	
	A(1), B(2), C(3);
	private final int valor;
	
	GrupoProduto(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}

}
