/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.maurodev.dao;

import br.com.maurodev.jdbc.ConnectionFactory;
import br.com.maurodev.model.FuncionarioModel;
import br.com.maurodev.view.MenuForm;
import br.com.maurodev.webservices.WebServiceCep;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
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
             stmt.setString(15,funcionario.getCargo());
             stmt.setString(16,funcionario.getSenha());
             
             
              // executa o comando sql
             stmt.execute();
             stmt.close();
             
             JOptionPane.showMessageDialog(null, " Funcionário Cadastrado!!");
             
        } catch (Exception e) {
           
            JOptionPane.showMessageDialog(null, "ORA - Erro de Acesso ao banco, consulte o Desenvolvedo");
        }
    }
    
     // metodo achar Funcionario LOGIN
    public FuncionarioModel loginFuncionario(String email, String senha){
                        
         try {
             
             //consulta sql
             String sql ="select * from loja.tb_funcionarios "
                       + "where email= ? "
                       + "and senha= ? ";
             
             PreparedStatement stmt = con.prepareStatement(sql);
             
             stmt.setString(1, email);
             stmt.setString(2, senha);
                   
            // sempre que for fazer select
             ResultSet rs = stmt.executeQuery();
            
             //(.next) se ele conseguir percorre
             if(rs.next()){
                 
                  //Usuario logou
                  FuncionarioModel funcNivel = new FuncionarioModel();
                  funcNivel.setNivel(rs.getString("nivel_acesso"));
                  funcNivel.setNome(rs.getString("nome"));
                  String nivel = funcNivel.getNivel();
                  String nome = funcNivel.getNome();
                  
                  switch (nivel) {
                      case "MASTER":
                           
                            JOptionPane.showMessageDialog(null, "Bem Vindo "+ nome + " Usuario MASTER a VENDAS&ESTOQUE");
                            MenuForm tela = new MenuForm();
                            tela.setVisible(true);
                            break;
                      case "SIMPLES":
                            JOptionPane.showMessageDialog(null, "Bem Vindo SIMPLES a VENDAS&ESTOQUE");
                            MenuForm tela2 = new MenuForm();
                            tela2.setVisible(true);
                          break;
                          
                          default:
                              
                            JOptionPane.showMessageDialog(null, "Usuario cadastrado mas SEM HIERARQUIA, por favor Peça para seu Administrador cadastra seu nivel de acesso.");
                              
                         throw new AssertionError(); 
                  }
                  
               
             }else{
                 //usuario invalido
                  JOptionPane.showMessageDialog(null, "DADOS ERRADO");
             }
         
         } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "erro ORA "+ e);
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
