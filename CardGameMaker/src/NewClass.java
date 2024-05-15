
import console.Console;
import console.Tecla;
import cores.Cor;
import cores.StringColorida;
import java.io.IOException;
import java.util.Arrays;
import mecanicas.Carta;
import mecanicas.Tabuleiro;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ´Gabriel
 */
class NewClass {
    public static void main(String[] args) throws IOException, InterruptedException {
        demoCores();
        demoCartas();
        demoTabuleiro();
        Console.saiDoPrograma();
    }
    
    public static void demoTabuleiro(){
        Tabuleiro tab = new Tabuleiro(2, 3, new Carta(copas) {}) {};
        tab.setFundo(0, 0, new Carta(paus) {});
        tab.setFundo(0, 2, new Carta(espadas) {});
        tab.setFundo(1, 0, new Carta(paus) {});
        tab.setFundo(1, 1, new Carta(copas) {});
        tab.setFundo(1, 2, new Carta(espadas) {});
        
        int linha = 0, coluna = 0;
        Tecla atual;
        
        while(true){
            Console.limpaTela();
            Console.println(tab);
            Console.println("Atualmente está olhando para a carta ("+linha+","+coluna+").");
            atual = Console.getTecla();
            
            if(atual == Tecla.ESC) break;
            if(atual == Tecla.RIGHT) coluna = (coluna + 1) %tab.getTotalColunas();
            if(atual == Tecla.LEFT){
                coluna = (coluna - 1);
                if(coluna < 0) coluna = tab.getTotalColunas() - 1;
            }
            if(atual == Tecla.DOWN) linha = (linha + 1) %tab.getTotalLinhas();
            if(atual == Tecla.UP){
                linha = (linha - 1);
                if(linha < 0) linha = tab.getTotalLinhas()- 1;
            }
            if(atual == Tecla.ENTER)
                tab.getFundo(linha, coluna).vira();
        }
    }
    
    public static void demoCartas(){
        
        String naipe;
        Carta as = null;
        
        while(as == null){
            naipe = Console.input("Escreva o naipe do as.");
            switch(naipe.toUpperCase()){
                case "COPAS":
                    as = new Carta(copas,copas.versoDesta("VERMELHO")) {};
                    break;
                case "OUROS":
                    as = new Carta(ouros,ouros.versoDesta("ROXO")) {};
                    break;
                case "PAUS":
                    as = new Carta(paus,paus.versoDesta("AMARELO")) {};
                    break;
                case "ESPADAS":
                    as = new Carta(espadas,espadas.versoDesta("CINZA")) {};
                    break;
                default:
                    Console.println("Apenas são aceitos COPAS/PAUS/OUROS/ESPADAS");
            }
        }
         
        Tecla atual;
        
        
        while(true){
            Console.limpaTela();
            Console.println(as);
            Console.println(Arrays.toString(as.getFaceParaCima().getDimensao()));
            
            atual = Console.getTecla();
            
            if(atual == Tecla.ESC) break;
            if(atual == Tecla.RIGHT || atual == Tecla.LEFT) as.vira();
        }
    }
    
        
    static StringColorida copas = new StringColorida("A  \n"
                                                    +" ♥ \n"
                                                    +"  A\n", "VERMELHO", "BRANCO");
    static StringColorida ouros = new StringColorida("A  \n"
                                                    +" ♦ \n"
                                                    +"  A\n", "VERMELHO", "BRANCO");
    static StringColorida paus = new StringColorida("A  \n"
                                                   +" ♣ \n"
                                                   +"  A\n", "PRETO", "BRANCO");
    static StringColorida espadas = new StringColorida("A  \n"
                                                      +" ♠ \n"
                                                      +"  A\n", "PRETO", "BRANCO");

    private static void demoCores() {
        Console.limpaTela();
        String teste = Console.input(
                        "Digite o texto a ser colorido com todas as cores 🁎");
        Console.limpaTela();
        Cor.testaTodasAsCores(teste);
        
    }
}
