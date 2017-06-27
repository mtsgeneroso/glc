package net.unesc.gsm.glc.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import net.unesc.gsm.glc.controllers.Producao;
import net.unesc.gsm.glc.controllers.Simbolo;
import net.unesc.gsm.glc.frames.MainFrame;
import net.unesc.gsm.glc.utils.Eliminacoes;

public class MainActionListener implements ActionListener {

    private MainFrame mainFrame;

    public MainActionListener(MainFrame tela) {

        this.mainFrame = tela;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String ev = e.getActionCommand();
        
        switch (ev) {
            case "Adicionar":
                adicionarProducao();
                break;
            case "Excluir gramática":
                limparGramatica();
                break;
            case "Gerar":
                gerarSimplificacao();
                break;
             
            
            
        }
    }

    private void gerarSimplificacao() {
        ArrayList<Producao> gramatica = new ArrayList<>();
        switch(mainFrame.getIndexOfComboBoxSimplificacao()){
            case 1:
                gramatica = Eliminacoes.removerInuteis(mainFrame.getGramatica());
                break;
            case 2:
                gramatica = Eliminacoes.removerUnitarias(mainFrame.getGramatica());
                break;
            case 3:
                gramatica = Eliminacoes.removerVazias(mainFrame.getGramatica());
                break;
            case 4:
                gramatica = Eliminacoes.removerCombinada(mainFrame.getGramatica());
                break;
        }
        
        
        mainFrame.setGramatica(gramatica);
    }
    
    protected void limparGramatica(){
        this.mainFrame.clearGramatica();
    }
    
    protected void adicionarProducao(){
        String[] prod = this.mainFrame.getProducao();
        if(!checkSimbolo(prod[0])){
            JOptionPane.showMessageDialog(null, "Simbolo inválido", "Erro de validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<String> producoes = parseProducoes(prod[1]);
        ArrayList<Producao> prodCompleta = new ArrayList<>();


        for(String p1 : producoes){

            Producao p = new Producao();

            p.setEsquerda(new Simbolo(prod[0]));

            p.setDireita(parseEsquerdaProducao(p1));

            prodCompleta.add(p);
        }

        this.mainFrame.adicionarNaTabela(prodCompleta);
    }
    
    public boolean checkSimbolo(String simbolo){
        
        if(simbolo.length() == 1){
            return true;
        }
        
        return false;
    }

    private List<String> parseProducoes(String prod) {
        
        List<String> producoes = Arrays.asList(prod.replaceAll("\\s+","").split("\\|"));
        
        for(String p: producoes){
            if(p == null || p.isEmpty())
                producoes.remove(p);
        }
        
        return producoes;
    }

    private ArrayList<Simbolo> parseEsquerdaProducao(String prod) {
        ArrayList<Simbolo> simbolos = new ArrayList<>();
        String[] prods = prod.split("");
        for(String s: prods){
            simbolos.add(new Simbolo(s, isFinal(s)));                   
        }
        
        return simbolos;
    }

    private boolean isFinal(String s) {
        return !Character.isUpperCase(s.charAt(0));
    }
    
}
