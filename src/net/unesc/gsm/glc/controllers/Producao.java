package net.unesc.gsm.glc.controllers;

import java.util.ArrayList;

public class Producao {
    private Simbolo esquerda;
    private ArrayList<Simbolo> direita;

    public Simbolo getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Simbolo esquerda) {
        this.esquerda = esquerda;
    }

    public ArrayList<Simbolo> getDireita() {
        return (ArrayList<Simbolo>) direita.clone();
    }

    public void setDireita(ArrayList<Simbolo> direita) {
        this.direita = direita;
    }
    
    public void addDireita(Simbolo simbolo){
        this.direita.add(simbolo);
    }

    @Override
    public String toString() {
        return "Producao{" + "esquerda=" + esquerda.toString() + ", direita=" + direita.toString() + '}';
    }

    public String getDireitaConcat() {
        String out = "";
        
        for(Simbolo s : this.direita)
            out += s.getCaracter();
        
        return out;
        
    }
    
    public boolean equals(Producao p){
        return p.getEsquerda().getCaracter().equals(this.esquerda.getCaracter()) && p.getDireitaConcat().equals(this.getDireitaConcat());
    }
    
    
    
}
