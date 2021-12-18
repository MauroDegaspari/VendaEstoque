/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.maurodev.jdbc;

import javax.swing.JOptionPane;

/**
 *
 * @author Mauro
 */
public class TestConnetoion {
    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConnetion();
            JOptionPane.showConfirmDialog(null,"COnectado com sucesso!!");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,"Deu MErda!! "+e);
        }
    }
}
