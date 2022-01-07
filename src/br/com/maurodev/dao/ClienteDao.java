/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.maurodev.dao;
import br.com.maurodev.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.maurodev.model.ClienteModel;
import br.com.maurodev.webservices.WebServiceCep;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;


/**
 *
 * @author Mauro
 */
public class ClienteDao {
    
    private Connection con;
    
    //construtor
    public ClienteDao(){
    this.con = new ConnectionFactory().getConnetion();
    }



//MetodoCadastra
    public void cadastraCliente(ClienteModel cliente){
        try {
            // criar o comando sql
          String sql = "insert into loja.tb_clientes(nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                     + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
         
             //conectar o banco de dados
             PreparedStatement stmt = con.prepareStatement(sql);
             stmt.setString(1,cliente.getNome());
             stmt.setString(2,cliente.getRg());
             stmt.setString(3,cliente.getCpf());
             stmt.setString(4,cliente.getEmail());
             stmt.setString(5,cliente.getTel());
             stmt.setString(6,cliente.getCelular());
             stmt.setString(7,cliente.getCep());
             stmt.setString(8,cliente.getEndereco());
             stmt.setInt(9,cliente.getNumero());
             stmt.setString(10,cliente.getComplemento());
             stmt.setString(11,cliente.getBairro());
             stmt.setString(12,cliente.getCidade());
             stmt.setString(13,cliente.getEstado());
            
             
             // executa o comando sql
             stmt.execute();
             stmt.close();
             
             JOptionPane.showMessageDialog(null, "Cadastrado!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ORA - Erro no sistema!! "+ e);
        }
    }
        //metodo listar todos os clientes
    public List<ClienteModel> ListarClientes(){
       
        try {
             //1 criar a listar
        List<ClienteModel> lista = new ArrayList<>();
        
        // criar a consulta do banco!
        String sql ="select * "
                  + "from loja.tb_clientes ";
        PreparedStatement stmt = con.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            ClienteModel cliente = new ClienteModel();
            
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setRg(rs.getString("rg"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTel(rs.getString("telefone"));
            cliente.setCelular(rs.getString("Celular"));
            cliente.setCep(rs.getString("cep"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setNumero(rs.getInt("numero"));
            cliente.setComplemento(rs.getString("complemento"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setCidade(rs.getString("Cidade"));
            cliente.setUf(rs.getString("estado"));
            
            lista.add(cliente);
            
        }
            return lista;
       
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Erro ao carregar Lista "+ e);
              return null;
        }

       
        
        
    }
    
    //metodoAlterar
    public void alteraCliente(ClienteModel cliente){
    try {
            // criar o comando sql
          String sql = "update loja.tb_clientes "
                     + "set nome=?,rg=?,cpf=?,email=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? "
                     + "where id = ?";
                     ;
         
             //conectar o banco de dados
             PreparedStatement stmt = con.prepareStatement(sql);
             stmt.setString(1,cliente.getNome());
             stmt.setString(2,cliente.getRg());
             stmt.setString(3,cliente.getCpf());
             stmt.setString(4,cliente.getEmail());
             stmt.setString(5,cliente.getTel());
             stmt.setString(6,cliente.getCelular());
             stmt.setString(7,cliente.getCep());
             stmt.setString(8,cliente.getEndereco());
             stmt.setInt(9,cliente.getNumero());
             stmt.setString(10,cliente.getComplemento());
             stmt.setString(11,cliente.getBairro());
             stmt.setString(12,cliente.getCidade());
             stmt.setString(13,cliente.getEstado());
             stmt.setInt(14,cliente.getId());
            
             
             // executa o comando sql
             stmt.execute();
             stmt.close();
             
             JOptionPane.showMessageDialog(null, "Alterado com sucesso!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Deu Merda na alteração!! "+ e);
        }
        
    }
   
    
    //metodoDeletar
    public void deletarCliente(ClienteModel cliente){
       try {
            // criar o comando sql
          String sql = "delete from loja.tb_clientes "
                     + "where id = ?";
         
             //conectar o banco de dados
             PreparedStatement stmt = con.prepareStatement(sql);
             stmt.setInt(1,cliente.getId());
            
            
             
             // executa o comando sql
             stmt.execute();
             stmt.close();
             
             JOptionPane.showMessageDialog(null, "Excluido com sucesso!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Deu Merda na exlusão!! "+ e);
        } 
    }
    
    
        public List<ClienteModel> pesquisaCliente(String nome){
        try {
             //1 criar a listar
        List<ClienteModel> lista = new ArrayList<>();
        
        // 2 criar a consuta do banco!
        String sql ="select * "
                  + "from loja.tb_clientes "
                  + "where nome like ?";    
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.setString(1, nome);
        
        ResultSet rs = stmt.executeQuery();
        
         //(.next) se ele conseguir percorre
        while(rs.next()){
            ClienteModel cliente = new ClienteModel();
            
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setRg(rs.getString("rg"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTel(rs.getString("telefone"));
            cliente.setCelular(rs.getString("Celular"));
            cliente.setCep(rs.getString("cep"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setNumero(rs.getInt("numero"));
            cliente.setComplemento(rs.getString("complemento"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setCidade(rs.getString("Cidade"));            
            cliente.setUf(rs.getString("uf"));
            
            lista.add(cliente);
            
        }
            return lista;
       
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Erro ao carregar Lista "+ e);
              return null;
        }
        }
        // metodo consulta cliente por nome
        public ClienteModel consultaPorNome(String nome){
           
            //1 passo consulta no banco
            try {
                  String sql = "select * "
                       + "from loja.tb_clientes "
                       + " where nome = ?";
            
           PreparedStatement stmt = con.prepareStatement(sql);
        
            stmt.setString(1, nome);
            
             ResultSet rs = stmt.executeQuery();
                      
            ClienteModel cliente = new ClienteModel();
            
             if(rs.next()){
         
            
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setRg(rs.getString("rg"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTel(rs.getString("telefone"));
            cliente.setCelular(rs.getString("Celular"));
            cliente.setCep(rs.getString("cep"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setNumero(rs.getInt("numero"));
            cliente.setComplemento(rs.getString("complemento"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setCidade(rs.getString("Cidade"));
            cliente.setUf(rs.getString("estado"));
                                   
        }
            return cliente;
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"cliente não encontrado");
                return null;
                        
            }      
        
        }
        
        
        //consulta cep
        
         public ClienteModel buscaCep(String cep) {
       
             WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        ClienteModel obj = new ClienteModel();

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
