/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanicas;


/**
 *
 * @author clique
 */
public abstract class Tabuleiro {
    PilhaDeCartas [][]tabuleiro;
    Carta[][] fundo;

    private Tabuleiro(int numLinhas, int numColunas){
        tabuleiro = new PilhaDeCartas[numLinhas][numColunas];
        this.fundo = new Carta[numLinhas][numColunas];
        for (int i = 0; i < numLinhas; i++) {
            for (int j = 0; j < numColunas; j++) {
                this.tabuleiro[i][j] = new PilhaDeCartas();
            }
        }
    }
    
    public Tabuleiro(int numLinhas, int numColunas, Carta fundo){
        this(numLinhas,numColunas);
        for (int i = 0; i < numLinhas; i++)
            for (int j = 0; j < numColunas; j++) 
                this.fundo[i][j] = fundo.copia();
    }
    
    public Tabuleiro(Tabuleiro fundo){
        this(fundo.getTotalLinhas(),fundo.getTotalColunas());
        this.fundo = fundo.fundo;
    }

    public int getTotalLinhas(){
        return tabuleiro.length;
    }
    
    public int getTotalColunas(){
        return tabuleiro[0].length;
    }
    
    public PilhaDeCartas getPilha(int linha, int coluna){
        return tabuleiro[linha][coluna];
    }
    
    public Carta getFundo(int linha, int coluna){
        return fundo[linha][coluna];
    }
    
    public void setFundo(int linha, int coluna, Carta carta){
        fundo[linha][coluna] = carta;
    }
    
    public void viraCarta(int linha, int coluna){
        tabuleiro[linha][coluna].verTopo().vira();
    }
    
    public void colocaCarta(int linha, int coluna, Carta carta){
        tabuleiro[linha][coluna].insereTopo(carta);
    }
    
    public Carta pegaCarta(int linha, int coluna){
        return tabuleiro[linha][coluna].compra();
    }
    
    public PilhaDeCartas pegaPilha(int linha, int coluna){
        PilhaDeCartas res = tabuleiro[linha][coluna].compraTudo();
        return tabuleiro[linha][coluna];
    }
    
    private int[] maiorAlturaELargura(){
        int []dimensaoAtual;
        int maiorLargura = 0,maiorAltura = 0;
        
        for (int i = 0; i < getTotalLinhas(); i++) {
            for (int j = 0; j < getTotalColunas(); j++) {
                if(tabuleiro[i][j].verTopo() != null)
                    dimensaoAtual = tabuleiro[i][j].verTopo().getFrente().getDimensao();
                else
                    dimensaoAtual = fundo[i][j].getFrente().getDimensao();
                if(dimensaoAtual[0] > maiorAltura)
                    maiorAltura = dimensaoAtual[0];
                if(dimensaoAtual[1] > maiorLargura)
                    maiorLargura = dimensaoAtual[1];
            }
        }
        return new int[]{maiorAltura,maiorLargura};
    }

    @Override
    public String toString() {
        int []maiores = maiorAlturaELargura(); 
        int alturaMaiorCarta = maiores[0];
        int larguraMaiorCarta = maiores[1];
        int l;
        Carta atual;        
        String res = "";
        
        for (int i = 0; i < getTotalLinhas(); i++) {
            for (int j = 0; j < alturaMaiorCarta; j++) {
                for (int k = 0; k < getTotalColunas(); k++) {
                    if(tabuleiro[i][k].verTopo() != null) atual = tabuleiro[i][k].verTopo();
                    else atual = fundo[i][k];
                    
                    l = 0;
                    while(l < atual.getFaceParaCima().getDimensao()[1])
                        res += atual.getFaceParaCima().getCharColorido(j, l++);
                    while(l < larguraMaiorCarta)
                        res += fundo[i][k].getFaceParaCima().getCharColorido(j, l++);
                }
                res += '\n';
            }
        }
        
        return res;
    }
    
}
