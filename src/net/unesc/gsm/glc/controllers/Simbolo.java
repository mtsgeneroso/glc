package net.unesc.gsm.glc.controllers;

public class Simbolo {
    private String caracter;
    private boolean fim;

    public Simbolo(){
        this.fim = false;
    }
    
    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public boolean isFinal() {
        return fim;
    }

    public void setFinal(boolean fim) {
        this.fim = fim;
    }
    
    
}
