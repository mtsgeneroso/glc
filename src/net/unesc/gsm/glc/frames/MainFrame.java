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
        
        tbPrincipal.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbPrincipal.getColumnModel().getColumn(0).setMinWidth(10);
        tbPrincipal.getColumnModel().getColumn(0).setWidth(10);
        tbPrincipal.getColumnModel().getColumn(0).setMaxWidth(10);
        tbPrincipal.getColumnModel().getColumn(1).setPreferredWidth(5);
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

        btnProducao.setText("+  Produção");
        btnProducao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProducaoActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");

        pnSimplificacao.setBorder(javax.swing.BorderFactory.createTitledBorder("Simplificações"));

        cbSimplificacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Símbolos Inúteis", "Produções Unitárias", "Produções Vazias", "Combinada" }));
        cbSimplificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSimplificacaoActionPerformed(evt);
            }
        });

        btnGerar.setText("Gerar");

        javax.swing.GroupLayout pnSimplificacaoLayout = new javax.swing.GroupLayout(pnSimplificacao);
        pnSimplificacao.setLayout(pnSimplificacaoLayout);
        pnSimplificacaoLayout.setHorizontalGroup(
            pnSimplificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cbSimplificacao, 0, 177, Short.MAX_VALUE)
            .addComponent(btnGerar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnSimplificacaoLayout.setVerticalGroup(
            pnSimplificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSimplificacaoLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(cbSimplificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGerar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        pnPosicao.setBorder(javax.swing.BorderFactory.createTitledBorder("Posição"));

        btnSubirNivel.setText("  🡩   Subir um nível");
        btnSubirNivel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnDescerNivel.setText("  🡫   Descer um nível");
        btnDescerNivel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout pnPosicaoLayout = new javax.swing.GroupLayout(pnPosicao);
        pnPosicao.setLayout(pnPosicaoLayout);
        pnPosicaoLayout.setHorizontalGroup(
            pnPosicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSubirNivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDescerNivel, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
        );
        pnPosicaoLayout.setVerticalGroup(
            pnPosicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPosicaoLayout.createSequentialGroup()
                .addComponent(btnSubirNivel, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnDescerNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setText("::=");

        lbProducoes.setText("Produções");

        lbSimbolos.setText("Símbolos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnSimplificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnPosicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(btnProducao, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbSimbolos)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtSimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(3, 3, 3)
                                    .addComponent(jLabel1)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtProducoes, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbProducoes))
                            .addGap(15, 15, 15))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSimbolos)
                            .addComponent(lbProducoes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtProducoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProducao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnSimplificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnPosicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(spTabela, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(875, 301));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbSimplificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSimplificacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSimplificacaoActionPerformed

    private void btnProducaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProducaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProducaoActionPerformed

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
