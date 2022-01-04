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
    public void cadastraFuncionario(){
    
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
