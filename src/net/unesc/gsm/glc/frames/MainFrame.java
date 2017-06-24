package net.unesc.gsm.glc.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.unesc.gsm.glc.actionlisteners.MainActionListener;
import net.unesc.gsm.glc.controllers.Producao;

public final class MainFrame extends javax.swing.JFrame {

    private ArrayList<Producao> gramatica;
    
    public MainFrame() {
        initComponents();
        
        int colSimbolWidth = 80;
        int colDividerWidth = 40;
        
        tbPrincipal.getColumnModel().getColumn(0).setPreferredWidth(colSimbolWidth);
        tbPrincipal.getColumnModel().getColumn(0).setMinWidth(colSimbolWidth);
        tbPrincipal.getColumnModel().getColumn(0).setWidth(colSimbolWidth);
        tbPrincipal.getColumnModel().getColumn(0).setMaxWidth(colSimbolWidth);
        
        tbPrincipal.getColumnModel().getColumn(1).setPreferredWidth(colDividerWidth);
        tbPrincipal.getColumnModel().getColumn(1).setMinWidth(colDividerWidth);
        tbPrincipal.getColumnModel().getColumn(1).setWidth(colDividerWidth);
        tbPrincipal.getColumnModel().getColumn(1).setMaxWidth(colDividerWidth);
        
        tbPrincipal.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        MainActionListener mainAction = new MainActionListener(gramatica, this);
        
        gramatica = new ArrayList<>();
        atualizaTabela();
        
        btnProducao.addActionListener(mainAction);
    }
    public String[] getProducao(){
        String[] prod = new String[2];
        prod[0] = txtSimbolo.getText().toUpperCase();
        prod[1] = txtProducoes.getText();
        return prod;
    }
    

    public void adicionarNaTabela(ArrayList<Producao> gramaticaParcial) {
        
        for(Producao p: gramaticaParcial)
            this.gramatica.add(p);
        
        atualizaTabela();
    }
    
    public void atualizaTabela(){
        DefaultTableModel tbModel = (DefaultTableModel) tbPrincipal.getModel();
        
        for(Producao p : gramatica){
            String[] row = new String[3];
            
            row[0] = p.getEsquerda().getCaracter();
            row[1] = "::=   ";
            row[2] = p.getDireitaConcat();
            
            tbModel.addRow(row);
        }
        tbPrincipal.setModel(tbModel);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnProducao = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        pnSimplificacao = new javax.swing.JPanel();
        cbSimplificacao = new javax.swing.JComboBox<>();
        btnGerar = new javax.swing.JButton();
        spTabela = new javax.swing.JScrollPane();
        tbPrincipal = new javax.swing.JTable();
        pnPosicao = new javax.swing.JPanel();
        btnSubirNivel = new javax.swing.JButton();
        btnDescerNivel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtProducoes = new javax.swing.JTextField();
        lbProducoes = new javax.swing.JLabel();
        lbSimbolos = new javax.swing.JLabel();
        txtSimbolo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GSM");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnProducao.setText("+  Produção");
        btnProducao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProducaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnProducao, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 59, 187, 40));

        btnExcluir.setText("Excluir");
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 59, 151, 40));

        pnSimplificacao.setBorder(javax.swing.BorderFactory.createTitledBorder("Simplificações"));
        pnSimplificacao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbSimplificacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Símbolos Inúteis", "Produções Unitárias", "Produções Vazias", "Combinada" }));
        cbSimplificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSimplificacaoActionPerformed(evt);
            }
        });
        pnSimplificacao.add(cbSimplificacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 22, 177, 37));

        btnGerar.setText("Gerar");
        pnSimplificacao.add(btnGerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 77, 177, 42));

        getContentPane().add(pnSimplificacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 110, 190, 126));

        tbPrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Símbolo", "", "Produção"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPrincipal.getTableHeader().setResizingAllowed(false);
        tbPrincipal.getTableHeader().setReorderingAllowed(false);
        spTabela.setViewportView(tbPrincipal);

        getContentPane().add(spTabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 13, -1, 223));

        pnPosicao.setBorder(javax.swing.BorderFactory.createTitledBorder("Posição"));
        pnPosicao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSubirNivel.setText("Subir um nível");
        pnPosicao.add(btnSubirNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, 139, 42));

        btnDescerNivel.setText("Descer um nível");
        btnDescerNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescerNivelActionPerformed(evt);
            }
        });
        pnPosicao.add(btnDescerNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 76, 139, 43));

        getContentPane().add(pnPosicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 110, -1, -1));

        jLabel1.setText("::=");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 36, -1, -1));
        getContentPane().add(txtProducoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 33, 273, -1));

        lbProducoes.setText("Produções");
        getContentPane().add(lbProducoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 13, -1, -1));

        lbSimbolos.setText("Símbolo");
        getContentPane().add(lbSimbolos, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 13, -1, -1));
        getContentPane().add(txtSimbolo, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 33, 77, -1));

        setSize(new java.awt.Dimension(875, 286));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbSimplificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSimplificacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSimplificacaoActionPerformed

    private void btnProducaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProducaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProducaoActionPerformed

    private void btnDescerNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescerNivelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDescerNivelActionPerformed

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
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGerar;
    private javax.swing.JButton btnProducao;
    private javax.swing.JButton btnSubirNivel;
    private javax.swing.JComboBox<String> cbSimplificacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbProducoes;
    private javax.swing.JLabel lbSimbolos;
    private javax.swing.JPanel pnPosicao;
    private javax.swing.JPanel pnSimplificacao;
    private javax.swing.JScrollPane spTabela;
    private javax.swing.JTable tbPrincipal;
    private javax.swing.JTextField txtProducoes;
    private javax.swing.JTextField txtSimbolo;
    // End of variables declaration//GEN-END:variables


}
