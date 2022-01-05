/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.maurodev.dao;

import br.com.maurodev.jdbc.ConnectionFactory;
import br.com.maurodev.model.FuncionarioModel;
import br.com.maurodev.webservices.WebServiceCep;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauro
 */
public class FuncionarioDao {
   
    private java.sql.Connection con;
    
    //construtor
    public FuncionarioDao(){
    this.con = new ConnectionFactory().getConnetion();
    }
    
    //metodo cadastro Funcionario
    public void cadastraFuncionario(FuncionarioModel funcionario){
    
        try {
            // SQL para inserir dado em banco
            String sql ="INSERT INTO loja.tb_funcionarios(nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado,nivel_acesso,cargo,senha) "
                      + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            //conectar o banco de dados
             PreparedStatement stmt = con.prepareStatement(sql);
             stmt.setString(1,funcionario.getNome());
             stmt.setString(2,funcionario.getRg());
             stmt.setString(3,funcionario.getCpf());
             stmt.setString(4,funcionario.getEmail());
             stmt.setString(5,funcionario.getTel());
             stmt.setString(6,funcionario.getCelular());
             stmt.setString(7,funcionario.getCep());
             stmt.setString(8,funcionario.getEndereco());
             stmt.setInt(9,funcionario.getNumero());
             stmt.setString(10,funcionario.getComplemento());
             stmt.setString(11,funcionario.getBairro());
             stmt.setString(12,funcionario.getCidade());
             stmt.setString(13,funcionario.getEstado());
             stmt.setString(14,funcionario.getNivel());
             stmt.setString(14,funcionario.getCargo());
             stmt.setString(14,funcionario.getSenha());
             
             
              // executa o comando sql
             stmt.execute();
             stmt.close();
             
             JOptionPane.showMessageDialog(null, " Funcionário Cadastrado!!");
             
        } catch (Exception e) {
           
            JOptionPane.showMessageDialog(null, "ORA - Erro de Acesso ao banco, consulte o Desenvolvedo");
        }
    }
    
     // metodo achar Funcionario LOGIN
    public FuncionarioModel loginFuncionario(String cpf, String senha){
                        
         try {
        
         
         } catch (Exception e) {
       
        }
        return null;
     }
    
       //consulta cep
        
         public FuncionarioModel buscaCep(String cep) {
       
             WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        FuncionarioModel obj = new FuncionarioModel();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setUf(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }
}
