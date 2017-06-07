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
        return direita;
    }

    public void setRight(ArrayList<Simbolo> direita) {
        this.direita = direita;
    }
    
    public void addRight(Simbolo simbolo){
        this.direita.add(simbolo);
    }
    
}
