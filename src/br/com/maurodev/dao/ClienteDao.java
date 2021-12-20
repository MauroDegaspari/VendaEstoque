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
            JOptionPane.showMessageDialog(null, "Deu Merda!! "+ e);
        }
    }
        //metodo listar todos os clientes
    public List<ClienteModel> ListarClientes(){
       
        try {
             //1 criar a listar
        List<ClienteModel> lista = new ArrayList<>();
        
        // criar a consuta do banco!
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
    public void AlteraCliente(){
        
    }
   
    
    //metodoDeletar
    public void deletarCliente(){
        
    }
}
