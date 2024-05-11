/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanicas;

import cores.StringColorida;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Classe que representa uma pilha de cartas, contendo métodos úteis para
 * utilizá-las no tabuleiro.
 *
 * @author clique
 * @see Carta
 * @see Tabuleiro
 */
public class PilhaDeCartas {
    private List<Carta> pilhaDeCartas;

    /**
     * Cria uma pilha vazia de Cartas.
     * 
     * @see Carta
     */
    public PilhaDeCartas(){
        pilhaDeCartas = new ArrayList<>();
    }
    
    /**
     * Cria uma pilha de Cartas baseada em alguma coleção já existente delas.
     * 
     * @param cartas coleção de cartas já existente.
     * @see Carta
     */
    public PilhaDeCartas(Collection<Carta> cartas){
        this();
        pilhaDeCartas.addAll(cartas);
    }

    /**
     * Retorna a referência da Carta que está no topo da PilhaDeCartas.
     * 
     * @return a referência da Carta que está no topo da PilhaDeCartas.
     * @see Carta
     */
    public Carta verTopo(){
        if(pilhaDeCartas.isEmpty()) return null;
        return pilhaDeCartas.get(pilhaDeCartas.size()-1);
    }
    
    /**
     * Retorna a quantidade de cartas nesta PilhaDeCartas.
     * 
     * @return a quantidade de cartas nesta PilhaDeCartas.
     */
    public int getTamanho(){
        return pilhaDeCartas.size();
    }
    
    /**
     * Insere a Carta passada na posição especificada desta PilhaDeCartas.
     * 
     * @param pos posição onde a Carta deve ser inserida.
     * @param carta Carta a ser inserida
     * @see Carta
     */
    public void insere(int pos, Carta carta){
        pilhaDeCartas.add(pos, carta);
    }
    
    /**
     * Insere a carta passada no topo desta PilhaDeCartas.
     * 
     * @param carta Carta a ser inserida
     * @see Carta
     */
    public void insereTopo(Carta carta){
        pilhaDeCartas.add(carta);
    }
    
    /**
     * Insere a carta passada na base desta PilhaDeCartas.
     * 
     * @param carta Carta a ser inserida
     * @see Carta
     */
    public void insereFundo(Carta carta){
        insere(0, carta);
    }
    
    /**
     * Insere a carta passada em alguma posição aleatória desta PilhaDeCartas.
     * 
     * @param carta Carta a ser inserida
     * @see Carta
     */
    public void insereAleatorio(Carta carta){
        insere(new Random().nextInt(pilhaDeCartas.size()),carta);
    }
    
    /**
     * Remove e retorna a carta no topo desta PilhaDeCartas.
     * 
     * @return a carta no topo desta PilhaDeCartas.
     * @see Carta 
     */
    public Carta compra(){
        return pilhaDeCartas.remove(pilhaDeCartas.size()-1);
    }
    
    /**
     * Remove e retorna as Cartas do topo desta PilhaDeCartas, de acordo com a
     * quantidade passada.
     * 
     * @param quantidade quantidade de cartas a serem compradas da PilhaDeCartas.
     * @return Uma nova PilhaDeCartas contendo todas as cartas compradas.
     * @see Carta
     */
    public PilhaDeCartas compra(int quantidade){
        PilhaDeCartas res = new PilhaDeCartas();
        for (int i = 0; i < quantidade; i++) {
            if(pilhaDeCartas.isEmpty()) break;
            res.insereFundo(compra());
        }
        return res;
    }
    
    /**
     * Retorna uma nova PilhaDeCartas composta de todas as Cartas desta
     * removedo-as desta.
     * 
     * @return uma nova PilhaDeCartas contendo todas as cartas compradas.
     * @see Carta
     */
    public PilhaDeCartas compraTudo(){
//        PilhaDeCartas res = new PilhaDeCartas();
//        for (int i = 0; i < getTamanho(); i++) {
//            if(pilhaDeCartas.isEmpty()) break;
//            res.insereFundo(compra());
//        }
//        return res;
        PilhaDeCartas res = new PilhaDeCartas(this.pilhaDeCartas);
        this.pilhaDeCartas = new ArrayList<>();
        return res;
    }
    
    /**
     * Remove e retorna a Carta que está na posição passada desta PilhaDeCartas.
     * 
     * @param pos posição da carta a ser comprada.
     * @return a Carta na posição passada.
     * @see Carta
     */
    public Carta compraQualquer(int pos){
        if(pos < 0 || pos > getTamanho()) return null;
        return pilhaDeCartas.remove(pos);
    }
    
    /**
     * Retorna o índice da Carta cuja face buscada é igual à passada dentro desta
     * PilhaDeCartas. Caso não pertença à ela, retorna -1.
     * 
     * @param faceDeCima Face da carta buscada
     * @return o índice da Carta cuja frente é igual a passada dentro desta
     * PilhaDeCartas. Caso não pertença à ela, retorna -1
     * @see Carta
     */
    public Carta busca(StringColorida faceDeCima){
        return busca(new Carta(faceDeCima) {});
    }
    
    /**
     * Retorna o índice da Carta passada dentro desta PilhaDeCartas. Caso não
     * pertença à ela, retorna -1.
     * 
     * @param carta Carta a ser buscada.
     * @return o índice da Carta passada dentro desta PilhaDeCartas. Caso não
     * pertença à ela, retorna -1.
     * @see Carta
     */
    public Carta busca(Carta carta){
        int indice = pilhaDeCartas.indexOf(this);
        return indice == -1 ? null : pilhaDeCartas.get(indice);
    }
    
    /**
     * Embaralha esta PilhaDeCartas.
     *
     */
    public void embaralha(){
        Collections.shuffle(pilhaDeCartas);
    }
}
