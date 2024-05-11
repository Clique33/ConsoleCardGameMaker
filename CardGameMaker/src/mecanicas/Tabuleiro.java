/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanicas;


/**
 * Classe que representa o tabuleiro onde os jogos de cartas são jogados.
 * Trata-se de uma matriz de Cartas que identifica o fundo e outra matriz de
 * mesma dimensão de PilhaDeCartas, onde de fato ficam as cartas dos jogos.
 *
 * @author clique
 * @see Carta
 * @see PilhaDeCartas
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
    
    /**
     * Cria um Tabuleiro básico a partir de um número de linhas, de colunas e 
     * cópias de uma mesma Carta que irão compor o fundo. As Pilhas são vazias
     * inicialmente.
     *
     * @param numLinhas número de linhas do Tabuleiro.
     * @param numColunas número de colunas do Tabuleiro.
     * @param fundo Carta cujas cópias compõem o fundo do Tabuleiro.
     * @see Carta
     * @see PilhaDeCartas
     */
    public Tabuleiro(int numLinhas, int numColunas, Carta fundo){
        this(numLinhas,numColunas);
        for (int i = 0; i < numLinhas; i++)
            for (int j = 0; j < numColunas; j++) 
                this.fundo[i][j] = fundo.copia();
    }
    
    /**
     * Cria um Tabuleiro a partir de outro já existente com o mesmo fundo, porém
     * com PilhasDeCartas vazias
     *
     * @param fundo Tabuleiro cujo fundo será copiado.
     * @see PilhaDeCartas
     */
    public Tabuleiro(Tabuleiro fundo){
        this(fundo.getTotalLinhas(),fundo.getTotalColunas());
        this.fundo = fundo.fundo;
    }

    /**
     * Retorna a quantidade de linhas deste Tabuleiro.
     *
     * @return a quantidade de linhas deste Tabuleiro.
     */
    public int getTotalLinhas(){
        return tabuleiro.length;
    }
    
    /**
     * Retorna a quantidade de colunas deste Tabuleiro.
     *
     * @return a quantidade de colunas deste Tabuleiro.
     */
    public int getTotalColunas(){
        return tabuleiro[0].length;
    }
    
    /**
     * Retorna a referência da PilhaDeCartas que está na posição passada neste
     * Tabuleiro.
     *
     * @param linha linha da posição
     * @param coluna coluna da posição
     * @return a referência da PilhaDeCartas que está na posição passada neste
     * Tabuleiro.
     * @see PilhaDeCartas
     */
    public PilhaDeCartas getPilha(int linha, int coluna){
        return tabuleiro[linha][coluna];
    }
    
    /**
     * Retorna a referência da Carta que está no fundo da posição passada neste
     * Tabuleiro.
     *
     * @param linha linha da posição
     * @param coluna coluna da posição
     * @return a referência da Carta que está no fundo da posição passada neste
     * Tabuleiro.
     * @see Carta
     */
    public Carta getFundo(int linha, int coluna){
        return fundo[linha][coluna];
    }
    
    /**
     * Altera a Carta que está no fundo da posição passada neste Tabuleiro.
     *
     * @param linha linha da posição
     * @param coluna coluna da posição
     * @param carta carta a ser inserida
     * @see Carta
     */
    public void setFundo(int linha, int coluna, Carta carta){
        fundo[linha][coluna] = carta;
    }
    
    /**
     * Vira a Carta que está no topo da PilhaDeCartas da posição passada.
     *
     * @param linha linha da posição
     * @param coluna coluna da posição
     * @see Carta
     * @see PilhaDeCartas
     */
    public void viraCarta(int linha, int coluna){
        tabuleiro[linha][coluna].verTopo().vira();
    }
    
    /**
     * Coloca a Carta passada no topo da PilhaDeCartas que está na posição
     * passada.
     *
     * @param linha linha da posição
     * @param coluna coluna da posição
     * @param carta Carta a ser colocada no topo.
     * @see Carta
     * @see PilhaDeCartas
     */
    public void colocaCarta(int linha, int coluna, Carta carta){
        tabuleiro[linha][coluna].insereTopo(carta);
    }
    
    /**
     * Compra a Carta que está no topo da PilhaDeCartas que está na posição
     * passada.
     *
     * @param linha linha da posição
     * @param coluna coluna da posição
     * @return a Carta que está no topo da PilhaDeCartas que está na posição
     * passada.
     * @see Carta
     * @see PilhaDeCartas
     */
    public Carta pegaCarta(int linha, int coluna){
        return tabuleiro[linha][coluna].compra();
    }
    
    /**
     * Retira a PilhaDeCartas que está na posição passada e a retorna.
     *
     * @param linha linha da posição
     * @param coluna coluna da posição
     * @return a PilhaDeCartas que está na posição passada.
     * @see PilhaDeCartas
     */
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
