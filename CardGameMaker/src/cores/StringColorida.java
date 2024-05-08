/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author clique
 */
public class StringColorida {
    private String string;
    private List<RangesColoridas> posicoesColoridas = new LinkedList<>();
    private int largura;
    private int altura;

    public StringColorida(String string){
        posicoesColoridas.add(new RangesColoridas(new Range(0,string.length()), Cor.BRANCO, Cor.FUNDO_PRETO));
        this.string = string;
        largura = -1;
        altura = -1;
    }    
    
    public StringColorida(String string,Cor corFonte,Cor corFundo){
        this(string);
        String prefixoFundo = "FUNDO_";
        
        if(!corFundo.name().startsWith(prefixoFundo))
            corFundo = Cor.valueOf(prefixoFundo + corFundo.name());
        if(corFonte.name().startsWith(prefixoFundo))
            corFonte = Cor.valueOf(corFonte.name().substring(prefixoFundo.length()));
            
        posicoesColoridas.get(0).corFonte = corFonte;
        posicoesColoridas.get(0).corFundo = corFundo;
    }
    
    public StringColorida(String string,Cor cor, boolean fundo){
        this(string);
        String prefixoFundo = "FUNDO_";
        
        if(fundo){
            if(!cor.name().startsWith(prefixoFundo))
                cor = Cor.valueOf(prefixoFundo + cor.name());
        
            posicoesColoridas.get(0).corFundo = cor;
        }
        else{
            if(cor.name().startsWith(prefixoFundo))
                cor = Cor.valueOf(cor.name().substring(prefixoFundo.length()));
            posicoesColoridas.get(0).corFonte = cor;
        }
        
    }
    
    public StringColorida(String string,Cor corFonte){
        this(string,corFonte,false);
    }
    
    public StringColorida(String string,String corFonte){
        this(string,Cor.valueOf(corFonte.toUpperCase()));
    }
    
    public StringColorida(String string,String corFonte,String corFundo){
        this(string,Cor.valueOf(corFonte.toUpperCase()),Cor.valueOf(corFundo.toUpperCase()));
    }
    
    public StringColorida(String string,String cor, boolean fundo){
        this(string,Cor.valueOf(cor.toUpperCase()),fundo);
    }
    
    public StringColorida copia(){
        StringColorida res = new StringColorida(string);
        Collections.copy(res.posicoesColoridas, posicoesColoridas);
        return res;
    }
    
    public String getString(){
        return string;
    }
    
    private int getIndicesDasCores(int i){
        for (int j = 0; j < posicoesColoridas.size(); j++) 
            if (posicoesColoridas.get(j).inRangesColoridas(i))
                return j;
        return -1;
    }    
    
    public int[] getDimensao(){
        if(largura == -1){
            String []stringQuebrada = string.split("\n");
            altura = stringQuebrada.length;
            largura = stringQuebrada[0].length();
            for (int i = 1; i < stringQuebrada.length; i++) 
                if (largura < stringQuebrada[i].length()) 
                    largura = stringQuebrada[i].length();
        }
        return new int[]{altura,largura};
    }
    
    
    private int linhaColunaParaReal(int linha, int coluna){
        return (linha)*(largura+1) + coluna;
    }
    
    public StringColorida getCharColorido(int linha, int coluna){
        
        int indice = getIndicesDasCores(linhaColunaParaReal(linha, coluna));
        return new StringColorida(string.charAt(linhaColunaParaReal(linha, coluna))+"",
                                  posicoesColoridas.get(indice).corFonte,
                                  posicoesColoridas.get(indice).corFundo);
    }
    
    public StringColorida versoDesta(String cor){
        int dimensao[] = getDimensao();
        String verso = "";
        for (int i = 0; i < dimensao[0]; i++){
            for (int j = 0; j < dimensao[1]; j++) 
                verso +=' ';
            verso += '\n';
        }
        return new StringColorida(verso,cor,true);
    }
    
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < string.length(); i++) {
            if(string.charAt(i) == '\n'){
                res += '\n';
            }else{
                int indice = getIndicesDasCores(i);            
                res += posicoesColoridas.get(indice).corFonte.getPrefixo();
                res += posicoesColoridas.get(indice).corFundo.getPrefixo();
                res += string.charAt(i) + Cor.RESET.getPrefixo();
            }
        }
        return res;
    }
    
    public String toStringAllColors(){
        String res = string+"\n";
        for (RangesColoridas posicoesColorida : posicoesColoridas) 
            res += posicoesColoridas;
        
        return res;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final StringColorida other = (StringColorida) obj;
        if (!Objects.equals(this.string, other.string)) {
            return false;
        }
        if (!Objects.equals(this.posicoesColoridas, other.posicoesColoridas)) {
            return false;
        }
        return true;
    }
    
    
    private class Range{
        private int ini;
        private int fim;
        
        Range(int ini, int fim){
            this.ini = ini;
            this.fim = fim;
        }
        
        Range(int fim){
            this.ini = 0;
            this.fim = fim;
        }
        
        int getInicio(){
            return ini; 
        } 
        int getFim(){
            return fim; 
        } 
        
        boolean inRange(int i){
            return i >= ini && i < fim;
        }
        
        boolean inRange(Range range){
            return range.ini >= this.ini && range.fim <= this.fim;
        }
        
        boolean haIntersecao(Range range){
            return range.ini >= this.ini && range.ini <= this.fim ||
                   range.fim >= this.ini && range.fim <= this.fim;
        }
        
        boolean estendeRange(Range range){
            if(inRange(range)) return false;
            
            if(this.ini == range.fim)
                this.ini = range.ini;
            else if(this.fim == range.ini)
                this.fim = range.fim;
            else if(haIntersecao(range))
                if(range.ini >= this.ini)
                    this.fim = range.fim;
                else
                    this.ini = range.ini;
            else 
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "range = ["+ini +','+ fim + ']';
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 59 * hash + this.ini;
            hash = 59 * hash + this.fim;
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
            final Range other = (Range) obj;
            if (this.ini != other.ini) {
                return false;
            }
            if (this.fim != other.fim) {
                return false;
            }
            return true;
        }
        
        
        
    }
    
    private class RangesColoridas{
        private List<Range> ranges;
        private Cor corFonte;
        private Cor corFundo;

        RangesColoridas(Range range, Cor corFonte, Cor corFundo) {
            ranges = new ArrayList<>();
            ranges.add(range);
            this.corFonte = corFonte;
            this.corFundo = corFundo;
        }
        
        void addRange(Range range){
            boolean estendeu = false;
            for (int i = 0; i < ranges.size(); i++){
                if(ranges.get(i).estendeRange(range)){
                    estendeu = true;
                    break;
                }
            }
            if(!estendeu) ranges.add(range);
        }
        
        boolean inRangesColoridas(int i){
            for (Range range : ranges) 
                if(range.inRange(i))
                    return true;
            return false;
        }

        @Override
        public String toString() {
            return "RangesColoridas{" + "ranges=" + Arrays.toString(ranges.toArray()) + ", corFonte=" + corFonte + ", corFundo=" + corFundo + '}';
        }  

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 37 * hash + Objects.hashCode(this.ranges);
            hash = 37 * hash + Objects.hashCode(this.corFonte);
            hash = 37 * hash + Objects.hashCode(this.corFundo);
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
            final RangesColoridas other = (RangesColoridas) obj;
            if (!Objects.equals(this.ranges, other.ranges)) {
                return false;
            }
            if (this.corFonte != other.corFonte) {
                return false;
            }
            if (this.corFundo != other.corFundo) {
                return false;
            }
            return true;
        }
        
    }

}
