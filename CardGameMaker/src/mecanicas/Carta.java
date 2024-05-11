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
 * Classe abstrata que representa Cartas. Todos os elementos de jogos nesta
 * engine devem ser cartas colocadas sobre um Tabuleiro. Assim, todo jogo deve
 * possuir uma ou mais classes que herdam desta, acrescentando o que for
 * necessário.
 *
 * @author clique
 * @see StringColorida
 * @see Tabuleiro
 */
public abstract class Carta {
    private StringColorida frente;
    private StringColorida verso;
    private boolean viradaParaCima;
    private int graus;

    /**
     * Cria uma Carta com a frente dada pela StringColorida passada, cujo verso
     * possui a mesma quantidade de linhas e cor base Cor.BRANCO_BRANCO. A carta
     * está virada com a frente para cima.
     * 
     * @param frente StringColorida que representa a frente da Carta.
     * @see StringColorida
     */
    public Carta(StringColorida frente) {
        this.frente = frente;
        this.verso = frente.versoDesta("BRANCO_BRANCO");
        graus = 0;
        viradaParaCima = true;
    }
    
    /**
     * Cria uma Carta com a frente dada pela StringColorida 'frente', e o verso
     * dado pela StringColorida 'verso'. A carta está virada com a frente para
     * cima.
     * 
     * @param frente StringColorida que representa a frente da Carta.
     * @param verso StringColorida que representa o verso da Carta.
     * 
     * @throws RuntimeException gera um erro caso a frente e o verso não possuam
     * as mesmas dimensões.
     * @see StringColorida
     */
    public Carta(StringColorida frente, StringColorida verso) {
        this(frente);
        if(!Arrays.equals(verso.getDimensao(),frente.getDimensao()))
            throw new RuntimeException("Frente e verso devem possuir as mesmas dimensões.");
        this.verso = verso;
    }

    /**
     * Cria uma Carta com a frente dada pela StringColorida 'frente',  o verso
     * dado pela StringColorida 'verso' e frente ou verso virada para cima, de 
     * acordo com o valor passado.
     * 
     * @param frente StringColorida que representa a frente da Carta.
     * @param verso StringColorida que representa o verso da Carta.
     * @param viradaParaCima frente fica para cima, caso true, e verso fica para
     * cima, caso false.
     * 
     * @throws RuntimeException gera um erro caso a frente e o verso não possuam
     * as mesmas dimensões.
     * 
     * @see StringColorida
     */
    public Carta(StringColorida frente, StringColorida verso, boolean viradaParaCima) {
        this(frente,verso);
        this.viradaParaCima = viradaParaCima;
    }

    /**
     * Retorna uma cópia da StringColorida da frente desta Carta.
     * @return uma cópia da StringColorida da frente desta Carta.
     * 
     * @see StringColorida
     */
    public StringColorida getFrente() {
        return frente.copia();
    }

    /**
     * Retorna uma cópia da StringColorida do verso desta Carta.
     * @return uma cópia da StringColorida do verso desta Carta.
     * 
     * @see StringColorida
     */
    public StringColorida getVerso() {
        return frente.copia();
    }
    
    /**
     * Retorna uma cópia da StringColorida da face que está para cima desta
     * Carta.
     * @return uma cópia da StringColorida da face que está para cima desta
     * Carta.
     * 
     * @see StringColorida
     */
    public StringColorida getFaceParaCima(){
        if(viradaParaCima)
            return getFrente();
        return getVerso();
    }

    /**
     * Retorna verdadeiro se a frente desta carta está virada para cima e falso,
     * caso contrário.
     * @return verdadeiro se a frente desta carta está virada para cima e falso,
     * caso contrário.
     */
    public boolean estaViradaParaCima(){
        return viradaParaCima;
    }
    
    /**
     * Inverte a face desta Carta que está virada para cima.
     */
    public void vira(){
        viradaParaCima = !viradaParaCima;
    }

    /**
     * Cria uma cópia desta Carta e a retorna.
     * 
     * @return uma cópia desta Carta.
     */
    public Carta copia() {
        return new Carta(getFrente(), getVerso(), estaViradaParaCima()) {};
    }
    
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
