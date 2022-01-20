/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.maurodev.dao;

import br.com.maurodev.jdbc.ConnectionFactory;
import br.com.maurodev.model.FornecedorModel;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauro
 */
public class FornecedorDao {
   
    private java.sql.Connection con;
    
    //construtor
    public FornecedorDao(){
    this.con = new ConnectionFactory().getConnetion();
    }
    
    public void cadastrarFornecedor(FornecedorModel fornecedor){
            
        try {
                String sql ="INSERT INTO loja.tb_fornecedores(nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                  + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1,fornecedor.getNome());
                ps.setString(2,fornecedor.getCnpj());
                ps.setString(3,fornecedor.getEmail());
                ps.setString(4,fornecedor.getTelefone());
                ps.setString(5,fornecedor.getCelular());
                ps.setString(6,fornecedor.getCep());
                ps.setString(7,fornecedor.getEndereco());
                ps.setInt(8,fornecedor.getNumero());
                ps.setString(9,fornecedor.getComplemento());
                ps.setString(10,fornecedor.getBairro());
                ps.setString(11,fornecedor.getCidade());
                ps.setString(12,fornecedor.getEstado());
                
                ps.execute();
                ps.close();
                
                JOptionPane.showMessageDialog(null,"Fornecedor Cadastrado com SUCESSO!");
       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ORA - ERRO DE BANCO"+ e);
        }
       
    
                   
    }
    
}
