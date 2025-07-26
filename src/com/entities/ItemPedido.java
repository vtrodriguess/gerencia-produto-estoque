package com.entities;

public class ItemPedido {
	
	private Produto produto;
	private int quantidade;
	
	public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
	
	public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    @Override
    public String toString() {
        return "Id: | " + produto.getId() + " | Produto: " + produto.getProduto() + " | Qtd: " + quantidade + "\n";
    }

}
