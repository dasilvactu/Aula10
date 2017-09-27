/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula10tabelas;

import javax.swing.JFrame;

/**
 *
 * @author ice
 */
public class Aula10Tabelas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JanelaTabela janela = new JanelaTabela();
        janela.setSize(400,300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
    
}
