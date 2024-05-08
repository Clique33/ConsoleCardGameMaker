/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanicas;

import cores.StringColorida;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 *
 * @author clique
 */
public class PilhaDeCartas {
    private List<Carta> pilhaDeCartas;

    public PilhaDeCartas(){
        pilhaDeCartas = new ArrayList<>();
    }
    
    public PilhaDeCartas(Collection<Carta> cartas){
        this();
        pilhaDeCartas.addAll(cartas);
    }

    public Carta verTopo(){
        if(pilhaDeCartas.isEmpty()) return null;
        return pilhaDeCartas.get(pilhaDeCartas.size()-1);
    }
    
    public int getTamanho(){
        return pilhaDeCartas.size();
    }
    
    public void insere(int pos, Carta carta){
        pilhaDeCartas.add(pos, carta);
    }
    
    public void insereTopo(Carta carta){
        pilhaDeCartas.add(carta);
    }
    
    public void insereFundo(Carta carta){
        insere(0, carta);
    }
    
    public void insereAleatorio(Carta carta){
        insere(new Random().nextInt(pilhaDeCartas.size()),carta);
    }
    
    public Carta compra(){
        return pilhaDeCartas.remove(pilhaDeCartas.size()-1);
    }
    
    public PilhaDeCartas compra(int quantidade){
        PilhaDeCartas res = new PilhaDeCartas();
        for (int i = 0; i < quantidade; i++) {
            if(pilhaDeCartas.isEmpty()) break;
            res.insereFundo(compra());
        }
        return res;
    }
    public PilhaDeCartas compraTudo(){
        PilhaDeCartas res = new PilhaDeCartas();
        for (int i = 0; i < getTamanho(); i++) {
            if(pilhaDeCartas.isEmpty()) break;
            res.insereFundo(compra());
        }
        return res;
    }
    
    public Carta compraQualquer(int pos){
        if(pos < 0 || pos > getTamanho()) return null;
        return pilhaDeCartas.remove(pos);
    }
    
    public Carta busca(StringColorida faceDeCima){
        return busca(new CartaImpl(faceDeCima));
    }
    
    public Carta busca(Carta carta){
        int indice = pilhaDeCartas.indexOf(this);
        return indice == -1 ? null : pilhaDeCartas.get(indice);
    }
    
    public void embaralha(){
        Random sorteador = new Random();
        PilhaDeCartas embaralhada = new PilhaDeCartas();
        while(!pilhaDeCartas.isEmpty())
            embaralhada.insereTopo(compraQualquer(sorteador.nextInt(getTamanho())));
        pilhaDeCartas = embaralhada.pilhaDeCartas;
    }

    private static class CartaImpl extends Carta {

        public CartaImpl(StringColorida faceDeCima) {
            super(faceDeCima);
        }
    }
  
}
