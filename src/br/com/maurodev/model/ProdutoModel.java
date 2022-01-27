/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.maurodev.model;

/**
 *
 * @author Mauro
 */
public class ProdutoModel {
    
      private int id;
    private String descricao;
    private String preco;
    private String qtd_estoque;
    
    private FornecedorModel fornecedo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(String qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public FornecedorModel getFornecedo() {
        return fornecedo;
    }

    public void setFornecedo(FornecedorModel fornecedo) {
        this.fornecedo = fornecedo;
    }
    
    
}
