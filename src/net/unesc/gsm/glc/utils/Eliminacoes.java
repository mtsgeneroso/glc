package net.unesc.gsm.glc.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
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
        
        ArrayList<Producao> gramaticaSemInuteis = new ArrayList<Producao>(gramatica);
        
        Simbolo sInicial = gramaticaSemInuteis.get(0).getEsquerda();
        
        ArrayList<Simbolo> terminais = new ArrayList<>();
 
        /*
        ** 1° Simbolos que possuem produções terminais direta e indiretamente
        */
        
        
        /* Verifica quais produções possuem terminais diretos */
        for(Producao p: gramaticaSemInuteis){
            if(isOnlyTerminais(p.getDireita()))
                if(!existInList(p.getEsquerda(), terminais))
                    terminais.add(p.getEsquerda());
        }
        
        
        /* Verifica quais produções possuem terminais indiretos */
        for(Producao p: gramaticaSemInuteis){
            for(Simbolo s: p.getDireita()){
                if(!s.isFinal()){
                    if(checkHasOnlyTerminais(s, gramaticaSemInuteis)){
                        if(!existInList(p.getEsquerda(), terminais))
                            terminais.add(p.getEsquerda());
                    }
                }
            }
        }
        
        //System.out.println("Terminais diretos/indiretos: ");
        //System.out.println(terminais);
        
        /*
        ** Remover produções que possuem simbolos não-terminais mas que não estão na lista de terminais (diretos/indiretos)
        */
        
        Iterator<Producao> i = gramaticaSemInuteis.iterator();
        while (i.hasNext()) {
            Producao p = i.next();
            
           for(Simbolo s: p.getDireita()){
               if(!s.isFinal()){
                    //System.out.println(p.getEsquerda().getCaracter() + "::=" + p.getDireitaConcat());
                    if(!existInList(s, terminais))
                        i.remove();
               }
           }
        }
        
        /*
        ** 2° Simbolos alcançados pelo simbolo inicial
        */
        
        ArrayList<Simbolo> acessiveis = new ArrayList<>();
        acessiveis.add(sInicial);
        
        for(Producao p: gramaticaSemInuteis){
            if(p.getEsquerda().getCaracter().equals(sInicial.getCaracter())){
                for(Simbolo s: p.getDireita()){
                    if(!s.isFinal()){
                        acessiveis.add(s);
                        acessiveis = getSimbolosOf(s, gramaticaSemInuteis, acessiveis);
                    }
                }
            }
        }/*
        ** Remover simbolos que não são alcançados por outras produções
        */
        
        i = gramaticaSemInuteis.iterator();
        while (i.hasNext()) {
            Producao p = i.next();
            
           if(!existInList(p.getEsquerda(), acessiveis))
                i.remove();
        }
        
        
        //System.out.println(acessiveis);
        
        //System.out.println("- - -");
        
        //for(Producao p: gramaticaSemInuteis)
            //System.out.println(p.getEsquerda().getCaracter() + "::=" + p.getDireitaConcat());
        
        return gramaticaSemInuteis;
    }
    
    protected static ArrayList<Simbolo> getSimbolosOf(Simbolo simbolo, ArrayList<Producao> gramatica, ArrayList<Simbolo> acessiveis) {
                
        for(Producao p: gramatica){
            if(p.getEsquerda().getCaracter().equals(simbolo.getCaracter())){
                for(Simbolo s: p.getDireita()){
                    if(!s.isFinal() && !existInList(s, acessiveis)){
                        acessiveis.add(s);
                        acessiveis = getSimbolosOf(s, gramatica, acessiveis);
                    }
                }
            }
        }
        
        return acessiveis;
    }
    
    protected static ArrayList<Simbolo> insertComboSimbolos(ArrayList<Simbolo> to, ArrayList<Simbolo> from) {
        
        for(Simbolo s: from){
            to.add(s);
        }
        
        return to;
    }
    
    protected static boolean existInList(Simbolo esquerda, ArrayList<Simbolo> terminais) {
        
        for(Simbolo s: terminais){
            if(s.getCaracter().equals(esquerda.getCaracter()))
                return true;
        }
        return false;
    }  
    
    protected static boolean isOnlyTerminais(ArrayList<Simbolo> direita) {
        for(Simbolo s: direita)
            if(!s.isFinal())
                return false;
        return true;
    }
    
    protected static boolean checkHasOnlyTerminais(Simbolo s, ArrayList<Producao> gramatica) {
        
        for(Producao p: gramatica){
            if(p.getEsquerda().getCaracter().equals(s.getCaracter())){
                if(isOnlyTerminais(p.getDireita()))
                    return true;
            }
        }
        
        return false;
    }
    
    public static ArrayList<Producao> removerUnitarias(ArrayList<Producao> gramatica){
        
        ArrayList<Producao> gramaticaSemUnitarias = removerVazias((ArrayList<Producao>) gramatica.clone());
        ArrayList<Producao> novasProducoes = new ArrayList<>();
        
        
        ArrayList<Producao> unitarias = hasUnitarias(gramaticaSemUnitarias);
        for(Producao p: unitarias){
            ArrayList<Producao> nProducoes = getProducoesOf(p.getDireita().get(0), gramaticaSemUnitarias);
            
            
            for(Producao p1: nProducoes){
                Producao pnova = new Producao();
                pnova.setEsquerda(p.getEsquerda());
                pnova.setDireita(p1.getDireita());
                novasProducoes.add(pnova);
            }
        }
        
        for(Producao pvazia : unitarias){
            gramaticaSemUnitarias.remove(pvazia);
        }
        
        gramaticaSemUnitarias = mergeGramaticas(gramaticaSemUnitarias, novasProducoes);
        
        return gramaticaSemUnitarias;
    }
   
    protected static ArrayList<Producao> getProducoesOf(Simbolo unitaria, ArrayList<Producao> gramatica) {
        ArrayList<Producao> producoes = new ArrayList<>();
        
        for(Producao p: gramatica){
            if(p.getEsquerda().getCaracter().equals(unitaria.getCaracter())){
                if(p.getDireita().size() == 1 && !p.getDireita().get(0).isFinal()){
                    producoes = insertCombo(producoes, getProducoesOf(p.getDireita().get(0), gramatica));
                } else {
                    producoes.add(p);
                }
            }
        }
        return producoes;
    }
    
    protected static ArrayList<Producao> insertCombo(ArrayList<Producao> producoes, ArrayList<Producao> producoesOf) {
        
        for(Producao p: producoesOf)
            producoes.add(p);
        
        return producoes;
    }
    
    protected static ArrayList<Producao> hasUnitarias(ArrayList<Producao> gramatica){
        ArrayList<Producao> producoesComUnitarias = new ArrayList<>();
        
        for(Producao p : gramatica)
            if(p.getDireita().size() == 1 && !p.getDireita().get(0).isFinal())
                producoesComUnitarias.add(p);
        
        return producoesComUnitarias;
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
                //System.out.println(novaProducao.getDireitaConcat());
                //System.out.println("- - - -");
                //System.out.println(novaProducao.getEsquerda().getCaracter() + "::=" + novaProducao.getDireitaConcat());
                //System.out.println(possibilidade);
            
            }
            
        }
        
        //System.out.println("Sem vazias: " + gramaticaSemVazias.size() + " Novas: " + novasProducoes.size() + " Produções Vazias: " + producoesVazias.size());
        
        novasProducoes = clearSimbolosEmpty(novasProducoes);
        
        gramaticaSemVazias = mergeGramaticas(gramaticaSemVazias, novasProducoes);
        
        return gramaticaSemVazias;
    }

    protected static ArrayList<Producao> clearSimbolosEmpty(ArrayList<Producao> novasProducoes) {
        
        
        for(Producao p: novasProducoes){
            
            ArrayList<Simbolo> simbolos = p.getDireita();
            
            Iterator<Simbolo> i = simbolos.iterator();
            while (i.hasNext()) {
               if(i.next().getCaracter().isEmpty()){
                    i.remove();
               }
            }
            p.setDireita(simbolos);
        }
        
        
        return novasProducoes;
    }
    
    protected static ArrayList<Producao> mergeGramaticas(ArrayList<Producao> gramatica, ArrayList<Producao> novasProducoes) {
        
        for(Producao pnova: novasProducoes)
            if(!checkDuplicated(pnova, gramatica))
                gramatica.add(pnova);
        
        return gramatica;
    }
    
    protected static boolean checkDuplicated(Producao p, ArrayList<Producao> gramatica){
        
        for(Producao p1: gramatica)
            if(p1.equals(p))
                return true;
        
        return false;
    }

    protected static ArrayList<Simbolo> cloneSimbolos(ArrayList<Simbolo> direita) {
        ArrayList<Simbolo> clone = new ArrayList<>();
        
        for(Simbolo s : direita){
            clone.add(new Simbolo(s.getCaracter(), s.isFinal()));
        }
        
        return clone;
    }

    protected static Integer getPosOcorrencia(ArrayList<Producao> producoesVazias, int ocorrencia, ArrayList<Simbolo> direita) {
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
        
    protected static ArrayList<ArrayList<Boolean>> gerarPossibilidades(int ocorrencias){
        
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

    protected static Integer getNumOcorrenciasSimbolo(ArrayList<Producao> producoesVazias, ArrayList<Simbolo> direita) {
        int num = 0;
        
        for(Producao p: producoesVazias)
            for(Simbolo s: direita)
                if(s.getCaracter().equals(p.getEsquerda().getCaracter()))
                    num++;
        
        return num;
    }

    protected static boolean isSimbolWithProducaoVazia(Simbolo s, ArrayList<Producao> producoesVazias) {
        
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
    
    protected static ArrayList<Producao> hasVazia(ArrayList<Producao> gramatica){
        ArrayList<Producao> producoesComVazio = new ArrayList<>();
        
        for(Producao p : gramatica)
            if(p.getDireitaConcat().equals("&"))
                producoesComVazio.add(p);
        
        return producoesComVazio;
    }

}
