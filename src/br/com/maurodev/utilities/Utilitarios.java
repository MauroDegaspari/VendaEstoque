/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.maurodev.utilities;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Mauro
 */
public class Utilitarios {
    
    //Metodo para limpar a tela quando necessario
    public void LimparTela(JPanel painel){
        Component components[]= painel.getComponents();
        for(Component component: components){
            if(component instanceof JTextField){
                ((JTextField)component).setText(null);
            }
        }
           
            
    };
}
