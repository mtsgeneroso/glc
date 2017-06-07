package net.unesc.gsm.glc.controllers;

import java.util.ArrayList;

public class Producao {
    private String esquerda;
    private ArrayList<String> direita;

    public String getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(String esquerda) {
        this.esquerda = esquerda;
    }

    public ArrayList<String> getDireita() {
        return direita;
    }

    public void setRight(ArrayList<String> direita) {
        this.direita = direita;
    }
    
    public void addRight(String simbolo){
        this.direita.add(simbolo);
    }
    
}
