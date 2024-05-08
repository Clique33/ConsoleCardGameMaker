/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanicas;

import cores.StringColorida;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author clique
 */
public abstract class Carta {
    private StringColorida frente;
    private StringColorida verso;
    private boolean viradaParaCima;
    private int graus;

    public Carta(StringColorida frente) {
        this.frente = frente;
        this.verso = frente.versoDesta("AZUL_CLARO");
        graus = 0;
        viradaParaCima = true;
    }
    

    public Carta(StringColorida frente, StringColorida verso) {
        this(frente);
        if(!Arrays.equals(verso.getDimensao(),frente.getDimensao()))
            throw new RuntimeException("Frente e verso devem possuir as mesmas dimensões.");
        this.verso = verso;
    }

    public Carta(StringColorida frente, StringColorida verso, boolean viradaParaCima) {
        this(frente,verso);
        this.viradaParaCima = viradaParaCima;
    }

    public StringColorida getFrente() {
        return frente;
    }

    public StringColorida getVerso() {
        return verso;
    }
    
    public StringColorida getFaceParaCima(){
        if(viradaParaCima)
            return getFrente();
        return getVerso();
    }

    public boolean estaViradaParaCima(){
        return viradaParaCima;
    }
    
    public void vira(){
        viradaParaCima = !viradaParaCima;
    }

    public Carta copia() {
        return new Carta(frente, verso, viradaParaCima) {};
    }
    
    public void giraCartaParaDireita(){}
    public void giraCartaParaEsquerda(){}

    @Override
    public String toString() {
        if(viradaParaCima)
            return frente.toString();
        return verso.toString();
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.frente);
        hash = 97 * hash + Objects.hashCode(this.verso);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        if (!Objects.equals(this.frente, other.frente)) {
            return false;
        }
        return true;
    }

}
