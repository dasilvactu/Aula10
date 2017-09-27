/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula10tabelas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ice
 */
public class JanelaTabela extends JFrame{

   private JTable tabela; 
   private JTextField txtNome = new JTextField(20);
   private JTextField txtIdade = new JTextField(2);
   private JTextField txtEmail = new JTextField(40);
   private JButton btnAdicionar = new JButton("Adicionar");
   private JButton btnSalvar = new JButton("Salvar");
   private JButton btnRemover = new JButton("Remover");
   private List<Pessoa> dados;
   public JanelaTabela(List<Pessoa> dados) throws HeadlessException {
        super("Tabelas 01");
        this.dados = dados;
        Object[] titulos = new Object[]{"Nome", "Idade","E-mail"};
        
        tabela= new JTable(new PessoaTableModel(dados));
        btnRemover.setEnabled(false);
        btnSalvar.setEnabled(false);
        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(2, 3));
        formulario.add(txtNome);
        formulario.add(txtIdade);
        formulario.add(txtEmail);
        formulario.add(btnAdicionar);
        formulario.add(btnSalvar);
        formulario.add(btnRemover);
        
        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tabela.getSelectedRowCount()==0){
                    btnRemover.setEnabled(false);
                    btnSalvar.setEnabled(false);
                    btnAdicionar.setEnabled(true);
                }else{
                    btnAdicionar.setEnabled(false);
                    btnSalvar.setEnabled(true);
                    btnRemover.setEnabled(true);
                    PessoaTableModel modelo = (PessoaTableModel)tabela.getModel();
                    int linha = tabela.getSelectedRow();
                    txtNome.setText((String)modelo.getValueAt(linha, 0));
                    txtIdade.setText(modelo.getValueAt(linha, 1).toString());
                    txtEmail.setText((String)modelo.getValueAt(linha, 2));
                }
            }
        });
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PessoaTableModel modelo = (PessoaTableModel)tabela.getModel();
                Pessoa p = new Pessoa(
                    txtNome.getText(),
                    Integer.parseInt(txtIdade.getText()),
                    txtEmail.getText());
                modelo.add(p);
             
                txtNome.setText("");
                txtIdade.setText("");
                txtEmail.setText("");
                txtNome.requestFocus();
            }
        });
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabela.getSelectedRowCount()==0) return;
                PessoaTableModel modelo = (PessoaTableModel)tabela.getModel();
                modelo.remove(tabela.getSelectedRow());
                txtNome.setText("");
                txtIdade.setText("");
                txtEmail.setText("");
                txtNome.requestFocus();
            }
        });
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabela.getSelectedRowCount()==0) return;
                PessoaTableModel modelo = (PessoaTableModel)tabela.getModel();
                int linha = tabela.getSelectedRow();
                modelo.setValueAt(txtNome.getText(), linha, 0);
                modelo.setValueAt(txtIdade.getText(), linha, 1);
                modelo.setValueAt(txtEmail.getText(), linha, 2);
                txtNome.setText("");
                txtIdade.setText("");
                txtEmail.setText("");
                tabela.clearSelection();
                txtNome.requestFocus();
                
            }
        });
        
    }
    
}
