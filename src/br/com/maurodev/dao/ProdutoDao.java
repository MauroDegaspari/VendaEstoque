/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.maurodev.dao;

import br.com.maurodev.jdbc.ConnectionFactory;
import java.sql.Connection;

/**
 *
 * @author Mauro
 */
public class ProdutoDao {

    private Connection con;

    public ProdutoDao() {
       this.con = new ConnectionFactory().getConnetion();
    }
}
