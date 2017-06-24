package net.unesc.gsm.glc.controllers;

public class Simbolo {
    private String caracter;
    private boolean fim;
    
    public Simbolo(){
        this.fim = false;
    }

    public Simbolo(String caracter) {
        this.caracter = caracter;
    }
    
    public String getCaracter() {
        return caracter;
    }

    public Simbolo(String caracter, boolean fim) {
        this.caracter = caracter;
        this.fim = fim;
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

    @Override
    public String toString() {
        return "Simbolo{" + "caracter=" + caracter + ", fim=" + fim + '}';
    }
    
    
    
}
