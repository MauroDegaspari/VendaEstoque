/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.maurodev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mauro
 */
public class ConnectionFactory {
    public Connection getConnetion(){
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/VendaEstoque","postgres","123");
          } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }   
}
