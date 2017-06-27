package net.unesc.gsm.glc.frames;

import com.sun.glass.events.KeyEvent;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.unesc.gsm.glc.actionlisteners.MainActionListener;
import net.unesc.gsm.glc.controllers.Producao;
import net.unesc.gsm.glc.utils.Eliminacoes;

public final class MainFrame extends javax.swing.JFrame {

    private ArrayList<Producao> gramatica;
    
    public MainFrame() {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
                System.out.println(ex.getMessage());
        } 
        initComponents();
        
        int colPos = 15;
        int colSimbolWidth = 15;
        int colDividerWidth = 28;
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        tbPrincipal.getColumnModel().getColumn(0).setPreferredWidth(colPos);
        tbPrincipal.getColumnModel().getColumn(0).setMinWidth(colPos);
        tbPrincipal.getColumnModel().getColumn(0).setWidth(colPos);
        tbPrincipal.getColumnModel().getColumn(0).setMaxWidth(colPos);
        tbPrincipal.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        tbPrincipal.getColumnModel().getColumn(1).setPreferredWidth(colSimbolWidth);
        tbPrincipal.getColumnModel().getColumn(1).setMinWidth(colSimbolWidth);
        tbPrincipal.getColumnModel().getColumn(1).setWidth(colSimbolWidth);
        tbPrincipal.getColumnModel().getColumn(1).setMaxWidth(colSimbolWidth);
        tbPrincipal.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        
        tbPrincipal.getColumnModel().getColumn(2).setPreferredWidth(colDividerWidth);
        tbPrincipal.getColumnModel().getColumn(2).setMinWidth(colDividerWidth);
        tbPrincipal.getColumnModel().getColumn(2).setWidth(colDividerWidth);
        tbPrincipal.getColumnModel().getColumn(2).setMaxWidth(colDividerWidth);
        
        tbPrincipal.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        tbPrincipal.setTableHeader(null);
        
        MainActionListener mainAction = new MainActionListener(this);
        
        gramatica = new ArrayList<>();
        atualizaTabela();
        
        btnProducao.addActionListener(mainAction);
        btnExcluirGramatica.addActionListener(mainAction);
        btnGerar.addActionListener(mainAction);
        
        /* Test: Produções Vazias  */
        /*
        txtSimbolo.setText("J");
        txtProducoes.setText("aWBbH|bBb|aHa");
        btnProducao.doClick();
        
        txtSimbolo.setText("B");
        txtProducoes.setText("bWa|bBH|a");
        btnProducao.doClick();
        
        txtSimbolo.setText("H");
        txtProducoes.setText("bHB|aBB|&");
        btnProducao.doClick();
        
        txtSimbolo.setText("W");
        txtProducoes.setText("bB|bb|&");
        btnProducao.doClick();
        
        txtSimbolo.setText("");
        txtProducoes.setText("");
        
        this.gramatica = Eliminacoes.removerVazias(this.gramatica);
        atualizaTabela();
        */
        
        /* Test: Produções Unitárias  */
        /*
        txtSimbolo.setText("J");
        txtProducoes.setText("aCb|CA");
        btnProducao.doClick();
        
        txtSimbolo.setText("A");
        txtProducoes.setText("bC|aCc|C");
        btnProducao.doClick();
        
        txtSimbolo.setText("C");
        txtProducoes.setText("bC|ab|D");
        btnProducao.doClick();
        
        txtSimbolo.setText("D");
        txtProducoes.setText("aA|bb");
        btnProducao.doClick();
        
        txtSimbolo.setText("");
        txtProducoes.setText("");
        
        this.gramatica = Eliminacoes.removerUnitarias(this.gramatica);
        atualizaTabela();
        */
        
        /* Test: Simbolos inúteis  */
        
        txtSimbolo.setText("S");
        txtProducoes.setText("baB|bBcG");
        btnProducao.doClick();
        
        txtSimbolo.setText("A");
        txtProducoes.setText("baB|a");
        btnProducao.doClick();
        
        txtSimbolo.setText("B");
        txtProducoes.setText("bFa|aG|&");
        btnProducao.doClick();
        
        txtSimbolo.setText("E");
        txtProducoes.setText("aE|a");
        btnProducao.doClick();
        
        txtSimbolo.setText("F");
        txtProducoes.setText("aB|bEa");
        btnProducao.doClick();
        
        txtSimbolo.setText("G");
        txtProducoes.setText("baG|aGb");
        btnProducao.doClick();
        
        
    }
    
    public int getIndexOfComboBoxSimplificacao() {
        return cbSimplificacao.getSelectedIndex();
    }
    
    public String[] getProducao(){
        String[] prod = new String[2];
        prod[0] = txtSimbolo.getText().toUpperCase();
        prod[1] = txtProducoes.getText();
        return prod;
    }
    

    public void adicionarNaTabela(ArrayList<Producao> gramaticaParcial) {
        
        for(Producao p: gramaticaParcial){
            if(!checkDuplicated(p))
                this.gramatica.add(p);
        }
        
        atualizaTabela();
    }
    
    public boolean checkDuplicated(Producao p){
        
        for(Producao p1: this.gramatica)
            if(p1.equals(p))
                return true;
        
        return false;
    }
    
    public void atualizaTabela(){
        DefaultTableModel tbModel = (DefaultTableModel) tbPrincipal.getModel();
                
        tbModel.setRowCount(0);
        
        for(Producao p : gramatica){
            String[] row = new String[4];
            
            row[0] = Integer.toString(gramatica.indexOf(p) + 1);
            row[1] = p.getEsquerda().getCaracter();
            row[2] = "::=   ";
            row[3] = p.getDireitaConcat();
            
            tbModel.addRow(row);
        }
        tbPrincipal.setModel(tbModel);
    }
    
    public void setGramatica(ArrayList<Producao> gramatica) {
        this.gramatica = gramatica;
        atualizaTabela();
    }
    
    public ArrayList<Producao> getGramatica() {
        return this.gramatica;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMain = new javax.swing.JPanel();
        btnExcluirGramatica = new javax.swing.JButton();
        spTabela = new javax.swing.JScrollPane();
        tbPrincipal = new javax.swing.JTable();
        btnExcluirProd = new javax.swing.JButton();
        pnSimplificacao = new javax.swing.JPanel();
        cbSimplificacao = new javax.swing.JComboBox<>();
        btnGerar = new javax.swing.JButton();
        pnPosicao = new javax.swing.JPanel();
        btnSubirNivel = new javax.swing.JButton();
        btnDescerNivel = new javax.swing.JButton();
        lbGramatica = new javax.swing.JLabel();
        pnProducao = new javax.swing.JPanel();
        txtProducoes = new javax.swing.JTextField();
        lbDivider = new javax.swing.JLabel();
        txtSimbolo = new javax.swing.JTextField();
        btnProducao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simplificador de GLC");
        setPreferredSize(new java.awt.Dimension(860, 260));
        setResizable(false);

        pnMain.setMinimumSize(new java.awt.Dimension(860, 260));
        pnMain.setPreferredSize(new java.awt.Dimension(860, 260));
        pnMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExcluirGramatica.setText("Excluir gramática");
        btnExcluirGramatica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnMain.add(btnExcluirGramatica, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 130, 30));

        spTabela.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tbPrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Símbolo", "", "Produção"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPrincipal.setFocusable(false);
        tbPrincipal.setGridColor(new java.awt.Color(255, 255, 255));
        tbPrincipal.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbPrincipal.setRowHeight(18);
        tbPrincipal.setRowMargin(0);
        tbPrincipal.getTableHeader().setResizingAllowed(false);
        tbPrincipal.getTableHeader().setReorderingAllowed(false);
        spTabela.setViewportView(tbPrincipal);
        if (tbPrincipal.getColumnModel().getColumnCount() > 0) {
            tbPrincipal.getColumnModel().getColumn(0).setResizable(false);
            tbPrincipal.getColumnModel().getColumn(1).setResizable(false);
            tbPrincipal.getColumnModel().getColumn(2).setResizable(false);
            tbPrincipal.getColumnModel().getColumn(3).setResizable(false);
        }

        pnMain.add(spTabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 180));

        btnExcluirProd.setText("Excluir produção");
        btnExcluirProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluirProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirProdActionPerformed(evt);
            }
        });
        pnMain.add(btnExcluirProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 120, 30));

        pnSimplificacao.setBorder(javax.swing.BorderFactory.createTitledBorder("Simplificações"));
        pnSimplificacao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbSimplificacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Símbolos Inúteis", "Produções Unitárias", "Produções Vazias", "Combinada" }));
        cbSimplificacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbSimplificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSimplificacaoActionPerformed(evt);
            }
        });
        pnSimplificacao.add(cbSimplificacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 170, 40));

        btnGerar.setText("Gerar");
        btnGerar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnSimplificacao.add(btnGerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 170, 42));

        pnMain.add(pnSimplificacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 190, 130));

        pnPosicao.setBorder(javax.swing.BorderFactory.createTitledBorder("Posição"));
        pnPosicao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSubirNivel.setText("Subir um nível");
        btnSubirNivel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSubirNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirNivelActionPerformed(evt);
            }
        });
        pnPosicao.add(btnSubirNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, 42));

        btnDescerNivel.setText("Descer um nível");
        btnDescerNivel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDescerNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescerNivelActionPerformed(evt);
            }
        });
        pnPosicao.add(btnDescerNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, 43));

        pnMain.add(pnPosicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 150, 130));

        lbGramatica.setText("Gramática");
        pnMain.add(lbGramatica, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 20));

        pnProducao.setBorder(javax.swing.BorderFactory.createTitledBorder("Produção(ões)"));
        pnProducao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtProducoes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProducoesKeyTyped(evt);
            }
        });
        pnProducao.add(txtProducoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 280, 30));

        lbDivider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDivider.setText("::=");
        lbDivider.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnProducao.add(lbDivider, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 30, 30));

        txtSimbolo.setMargin(new java.awt.Insets(2, 10, 2, 2));
        txtSimbolo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSimboloKeyTyped(evt);
            }
        });
        pnProducao.add(txtSimbolo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 30, 30));

        btnProducao.setText("Adicionar");
        btnProducao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProducao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProducaoActionPerformed(evt);
            }
        });
        pnProducao.add(btnProducao, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 140, 30));

        pnMain.add(pnProducao, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 360, 100));

        getContentPane().add(pnMain, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(857, 297));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbSimplificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSimplificacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSimplificacaoActionPerformed

    private void btnProducaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProducaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProducaoActionPerformed

    private void btnDescerNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescerNivelActionPerformed
        int fromPos = tbPrincipal.getSelectedRow();
        int toPos = (fromPos == (this.gramatica.size() - 1) ? fromPos : fromPos + 1);
        this.gramatica.add(toPos, this.gramatica.remove(fromPos));
        atualizaTabela();
        selecionarLinha(toPos);
    }//GEN-LAST:event_btnDescerNivelActionPerformed

    private void txtProducoesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProducoesKeyTyped
        char c = evt.getKeyChar();
        
        if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACKSPACE)|| c==KeyEvent.VK_DELETE || '|' == c || '&' == c))
            evt.consume();
    }//GEN-LAST:event_txtProducoesKeyTyped

    private void txtSimboloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSimboloKeyTyped
        char c = evt.getKeyChar();
        int size = txtSimbolo.getText().length();
        
        if(!(Character.isAlphabetic(c) ||  (c==KeyEvent.VK_BACKSPACE)||  c==KeyEvent.VK_DELETE ))
            evt.consume();
        if(size > 0)
            txtSimbolo.setText(txtSimbolo.getText().substring(0, 0));
    }//GEN-LAST:event_txtSimboloKeyTyped

    private void btnExcluirProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirProdActionPerformed
        int pos = tbPrincipal.getSelectedRow();
        this.gramatica.remove(pos);
        int limit = this.gramatica.size() - 1;
        atualizaTabela();
        if(this.gramatica.size() > 0)
            selecionarLinha(pos <= limit ? pos : limit);
        
    }//GEN-LAST:event_btnExcluirProdActionPerformed

    private void btnSubirNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirNivelActionPerformed
        int fromPos = tbPrincipal.getSelectedRow();
        int toPos = (fromPos == 0 ? fromPos : fromPos - 1);
        this.gramatica.add(toPos, this.gramatica.remove(fromPos));
        atualizaTabela();
        selecionarLinha(toPos);
    }//GEN-LAST:event_btnSubirNivelActionPerformed

    public void selecionarLinha(int linha){
        ListSelectionModel selectionModel = tbPrincipal.getSelectionModel();
        selectionModel.setSelectionInterval(linha, linha);
    }
    
    public void clearGramatica() {
        this.gramatica = new ArrayList<>();
        atualizaTabela();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescerNivel;
    private javax.swing.JButton btnExcluirGramatica;
    private javax.swing.JButton btnExcluirProd;
    private javax.swing.JButton btnGerar;
    private javax.swing.JButton btnProducao;
    private javax.swing.JButton btnSubirNivel;
    private javax.swing.JComboBox<String> cbSimplificacao;
    private javax.swing.JLabel lbDivider;
    private javax.swing.JLabel lbGramatica;
    private javax.swing.JPanel pnMain;
    private javax.swing.JPanel pnPosicao;
    private javax.swing.JPanel pnProducao;
    private javax.swing.JPanel pnSimplificacao;
    private javax.swing.JScrollPane spTabela;
    private javax.swing.JTable tbPrincipal;
    private javax.swing.JTextField txtProducoes;
    private javax.swing.JTextField txtSimbolo;
    // End of variables declaration//GEN-END:variables






}
