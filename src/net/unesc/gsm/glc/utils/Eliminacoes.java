package net.unesc.gsm.glc.utils;

import java.util.ArrayList;
import net.unesc.gsm.glc.controllers.Producao;
import net.unesc.gsm.glc.controllers.Simbolo;

public class Eliminacoes {
    
    /*  Gramáticas testes
        Simbolos Inúteis
     *  S -> baB|bBcG
     *  A -> baB|a
     *  B -> bFa|aG|&
     *  E -> aB|bEa
     *  F -> aB|bEa
     *  G -> baG|aGb

        Produções Unitárias
     *  J -> aCb|CA
     *  A -> bC|aCc|C
     *  C -> bC|ab|D
     *  D -> aA|bb

        Produções Vazias
     *  J -> aWBbH|bBb|aHa
     *  B -> bWa|bBH|a
     *  H -> bHB|aBB|&
     *  W -> bB|bb|& 
     */
    
   
    public static ArrayList<Producao> removerInuteis(ArrayList<Producao> gramatica){
        return gramatica;
    }
    
    public static ArrayList<Producao> removerUnitarias(ArrayList<Producao> gramatica){
        return gramatica;
    }
    
    public static ArrayList<Producao> removerVazias(ArrayList<Producao> gramatica){
        
        ArrayList<Producao> gramaticaSemVazias = (ArrayList<Producao>) gramatica.clone();
        ArrayList<Producao> novasProducoes = new ArrayList<>();
        
        /* Filtra as produções vazias da gramática */
        ArrayList<Producao> producoesVazias = hasVazia(gramatica);
        
        /* Verifica se o filtro retornou alguma produção vazia, caso não exista retorna a gramatica original */
        if(producoesVazias.size() == 0) return gramatica;
        
        
        for(Producao pvazia : producoesVazias){
            gramaticaSemVazias.remove(pvazia);
        }
        
        producoesVazias = new ArrayList<Producao>(producoesVazias) ;
        
        for(Producao p: gramaticaSemVazias){
            ArrayList<Simbolo> direita = new ArrayList<Simbolo>(p.getDireita());

            // Ocorrências de produções que possuam o símbolo vazio
            Integer ocorrenciasDeSimbolos = getNumOcorrenciasSimbolo(producoesVazias, direita);
            
            if(ocorrenciasDeSimbolos == 0) continue;
            
            ArrayList<ArrayList<Boolean>> possibilidades = gerarPossibilidades(ocorrenciasDeSimbolos);
            
            for(ArrayList<Boolean> possibilidade: possibilidades){
                ArrayList<Simbolo> simbolos = cloneSimbolos(p.getDireita());
                
                
                for(int j = 0; j < possibilidade.size(); j++){
                    
                    if(possibilidade.get(j)){
                        int posOcorrencia = getPosOcorrencia(producoesVazias, j + 1, simbolos);
                        
                        simbolos.get(posOcorrencia).setCaracter("");
                       // System.out.println(posOcorrencia + " : " + simbolos);
                    }
                
                }
                
                Producao novaProducao = new Producao();
                novaProducao.setEsquerda(new Simbolo(p.getEsquerda().getCaracter()));
                novaProducao.setDireita(simbolos);
                
                novasProducoes.add(novaProducao);
                ///System.out.println(possibilidade);
                System.out.println(novaProducao.getDireitaConcat());
                System.out.println("- - - -");
                //System.out.println(novaProducao.getEsquerda().getCaracter() + "::=" + novaProducao.getDireitaConcat());
                //System.out.println(possibilidade);
            
            }
            
            /*
            Producao novaProducao = p;
                ArrayList<Simbolo> simbolos = (ArrayList<Simbolo>) novaProducao.getDireita().clone();

                for(ArrayList<Boolean> ocorrencias: ){
                    for(int i = 0; i < ocorrencias.size(); i++){
                        if(ocorrencias.get(i)){
                            //int posOcorrencia = getPosOcorrencia(pvazia.getEsquerda().getCaracter(), i + 1, simbolos);

                            //simbolos.remove(posOcorrencia);
                        }
                    }

                    p.setDireita(simbolos);
                    novasProducoes.add(p);
                    System.out.println(p.getEsquerda().getCaracter() + "::=" + p.getDireitaConcat());
                }
*/
            
        }
        
        
        
        //System.out.println("Sem vazias: " + gramaticaSemVazias.size() + " Novas: " + novasProducoes.size() + " Produções Vazias: " + producoesVazias.size());
        
        gramaticaSemVazias = mergeGramaticas(gramaticaSemVazias, novasProducoes);
        
        return gramaticaSemVazias;
    }

    private static ArrayList<Producao> mergeGramaticas(ArrayList<Producao> gramaticaSemVazias, ArrayList<Producao> novasProducoes) {
        
        for(Producao pnova: novasProducoes)
            if(!checkDuplicated(pnova, gramaticaSemVazias))
                gramaticaSemVazias.add(pnova);
        
        return gramaticaSemVazias;
    }
    
    public static boolean checkDuplicated(Producao p, ArrayList<Producao> gramatica){
        
        for(Producao p1: gramatica)
            if(p1.equals(p))
                return true;
        
        return false;
    }

    private static ArrayList<Simbolo> cloneSimbolos(ArrayList<Simbolo> direita) {
        ArrayList<Simbolo> clone = new ArrayList<>();
        
        for(Simbolo s : direita){
            clone.add(new Simbolo(s.getCaracter(), s.isFinal()));
        }
        
        return clone;
    }

    private static Integer getPosOcorrencia(ArrayList<Producao> producoesVazias, int ocorrencia, ArrayList<Simbolo> direita) {
        int i = 1;
        
        
        for(int j = 0; j < direita.size(); j++){
            if(isSimbolWithProducaoVazia(direita.get(j), producoesVazias) || direita.get(j).getCaracter().isEmpty() ){
                //System.out.println(direita.get(j).getCaracter() + " Ocorrencia: " + i + ":" + ocorrencia + " -> " + (i == ocorrencia));
                if(i == ocorrencia){
                    return j;
                }
                i++;
            }
            
            
        }
        
        return -1;
        
    }
    
    
    private static ArrayList<ArrayList<Boolean>> gerarPossibilidades(int ocorrencias){
        
        ArrayList<ArrayList<Boolean>> out = new ArrayList<>();
        
        int n = 1<<ocorrencias;
        
        for(int i = 0 ; i < n; i++)
            out.add(calculatePossibilite(i, ocorrencias));
        
        
        return out;
    }
    
    protected static ArrayList<Boolean> calculatePossibilite(int n, int ocorrencias){
        ArrayList<Boolean> out = new ArrayList<>();
        
        
        Integer bit = 1<< ocorrencias - 1;
        
        while (bit != 0) {
            out.add((n & bit) > 0);
            bit >>= 1;
        }
        
        return out;
        
    }

    private static Integer getNumOcorrenciasSimbolo(ArrayList<Producao> producoesVazias, ArrayList<Simbolo> direita) {
        int num = 0;
        
        for(Producao p: producoesVazias)
            for(Simbolo s: direita)
                if(s.getCaracter().equals(p.getEsquerda().getCaracter()))
                    num++;
        
        return num;
    }

    private static boolean isSimbolWithProducaoVazia(Simbolo s, ArrayList<Producao> producoesVazias) {
        
        for(Producao p: producoesVazias){
            if(p.getEsquerda().getCaracter().equals(s.getCaracter())){
                return true;
            }
        }
        return false;
    }
    
    protected static ArrayList<Integer> hasItself(Producao p) {
        
        ArrayList<Integer> pos = new ArrayList<>();
        
        for(Simbolo s : p.getDireita()) {
            if(s.getCaracter().equals(p.getEsquerda().getCaracter()))
                pos.add(p.getDireita().indexOf(s));
        }
        
        return pos;
    }
    
    public static ArrayList<Producao> removerCombinada(ArrayList<Producao> gramatica){
        return removerInuteis(removerUnitarias(removerVazias(gramatica)));
    }
    
    public static ArrayList<Producao> hasVazia(ArrayList<Producao> gramatica){
        ArrayList<Producao> producoesComVazio = new ArrayList<>();
        
        for(Producao p : gramatica)
            if(p.getDireitaConcat().equals("&"))
                producoesComVazio.add(p);
        
        return producoesComVazio;
    }
}
