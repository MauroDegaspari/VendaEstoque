/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.maurodev.dao;

import br.com.maurodev.jdbc.ConnectionFactory;
import br.com.maurodev.model.FuncionarioModel;
import br.com.maurodev.view.LoginForm;
import br.com.maurodev.view.MenuForm;
import br.com.maurodev.webservices.WebServiceCep;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
    
    
     //metodo de Listar todos os Funcionarios
    public List<FuncionarioModel> listarFuncionarios(){
        try {
               //1 criar lista de arrys de funcionarios
               List<FuncionarioModel> lista = new ArrayList<>();
               
               //2 consulta em banco
               String sql ="SELECT * FROM loja.tb_funcionarios";
               
               PreparedStatement ps = con.prepareStatement(sql);
               
                ResultSet rs = ps.executeQuery();
        
                while(rs.next()){

                    FuncionarioModel func = new FuncionarioModel();
                    
                    func.setId(rs.getInt("id"));
                    func.setNome(rs.getString("nome"));
                    func.setCargo(rs.getString("cargo"));
                    func.setNivel(rs.getString("nivel_acesso"));
                    func.setRg(rs.getString("rg"));
                    func.setCpf(rs.getString("cpf"));
               
                    lista.add(func);
                }
                         
                       
               return lista;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar Lista "+ e);
            return null;
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
                      case "Admin":                           
                            JOptionPane.showMessageDialog(null, "Bem Vindo funcionário "+ nome + ", Seu nivel de acesso é de ADMINISTRADOR DO SISTEMA");
                            MenuForm tela = new MenuForm();
                            tela.funcionarioLogadoLabel =rs.getString("nome");
                            tela.FuncionarioNivelLabel = rs.getString("nivel_acesso");
                            tela.funcionarioCargo = rs.getString("cargo");
                            tela.setVisible(true);
                            break;
                      case "Funcionario":
                            JOptionPane.showMessageDialog(null, "Bem Vindo "+ nome+", nivel de acesso SIMPLES tenha uma bom trabalho!");
                            MenuForm tela2 = new MenuForm();
                            tela2.funcionarioLogadoLabel =rs.getString("nome");
                            tela2.FuncionarioNivelLabel = rs.getString("nivel_acesso");
                            tela2.funcionarioCargo = rs.getString("cargo");
                            tela2.setVisible(true);
                          break;
                          
                          default:
                              
                            JOptionPane.showMessageDialog(null, "Usuario cadastrado mas SEM HIERARQUIA, por favor Peça para seu Administrador cadastra seu nivel de acesso.");
                              
                         throw new AssertionError(); 
                  }
                  
               
             }else{
                 //usuario invalido
                  JOptionPane.showMessageDialog(null, "DADOS ERRADO");
                  
                    //manter a tela aberta caso os dados estejam errados
                  new LoginForm().setVisible(true);
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
