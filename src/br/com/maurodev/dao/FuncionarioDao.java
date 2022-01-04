/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.maurodev.dao;

import br.com.maurodev.jdbc.ConnectionFactory;
import br.com.maurodev.model.FuncionarioModel;
import br.com.maurodev.webservices.WebServiceCep;
import com.sun.jdi.connect.spi.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauro
 */
public class FuncionarioDao {
   
    private Connection con; 
    
    //construtor
    public FuncionarioDao(){
    this.con = (Connection) new ConnectionFactory().getConnetion();
    }
    
    //metodo cadastro Funcionario
    public void cadastraFuncionario(){
    
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
