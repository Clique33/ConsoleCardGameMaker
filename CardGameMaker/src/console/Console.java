/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import static java.lang.Thread.sleep;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta classe estática contém métodos para interação com o usuário através do
 * console de forma amigável ao uso de UTF-8 e possibilitando a captura de teclas
 * do usuário.
 * 
 *
 * @author Gabriel de Carvalho
 */
public class Console {
    static boolean acessoLiberadoAoTecladoDoSistema = false;
    static Scanner scanner = new Scanner(System.in);
    static PrintStream printer;
    static{
        final String os = System.getProperty("os.name");
        try {
            if (os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "chcp 65001").inheritIO().start().waitFor();
            else
                System.out.print("\033\143");
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException("Ocorreu um erro ao tentar limpar a tela do console");
        }
        limpaTela();
        try {
            printer = new PrintStream(System.out, true, UTF_8.name());
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Codificação invalida: " + UTF_8);
        }
    }
    static Tecla ultimaTeclaPressionada = null;
    static boolean querTecla = false;
    static int numeroTeclasPressionadas = 0;
    static int numeroENTERsPressionadas = 0;
    
    
    /**
     * Força a saída do programa. Caso o método getTecla()
     * tenha sido usado ao longo de sua execução, o programa não será fechado
     * automaticamente ao chegar no final da main, pois há uma segunda thread em
     * execução para capturar teclas. Portanto, este método garante a saída
     * correta do programa.
     * 
     * @see console.Console#getTecla()
     */
    public static void saiDoPrograma(){
        System.exit(0);
    }
    
    /**
     * Imprime no console qualquer objeto. Este método utiliza a codificação 
     * UTF-8 para tal. Necessita que o console alvo possua uma fonte que entenda
     * UTF-8. 
     * 
     * @param obj objeto a ser printado na tela. É feita uma chamada ao método
     * toString() de obj.
     */
    public static void print(Object obj){
        printer.print(obj);
    }
    
    
    /**
     * Imprime no console qualquer objeto, pulando uma linha ao final. Este 
     * método utiliza a codificação UTF-8 para tal. Necessita que o console alvo
     * possua uma fonte que entenda UTF-8. 
     * 
     * @param obj objeto a ser printado na tela. É feita uma chamada ao método
     * toString() de obj.
     */
    public static void println(Object obj){
        print(obj+"\n");
    }
    
    
    /**
     * Lê do console a última frase escrita pelo usuário após pressionar ENTER.
     * Em alguns casos, podem aparecer os caracteres pressionados anteriormente 
     * pelo usuário, caso tenha sido usado o método getTecla() em algum momento.
     * Para garantir que não pareçam, obrigue o usuário a pressionar ENTER antes
     * da utilização deste método.
     * 
     * @see console.Console#getTecla()
     * 
     * @param msg Mensagem a ser printada na tela antesda aguardar pela entrada.
     * @return String composta por tudo o que foi digitado até a tecla ENTER ser
     * pressionada.
     */
    public static String input(String msg){  
        for (int i = 0; i < numeroENTERsPressionadas; i++) 
            scanner.nextLine();
        numeroENTERsPressionadas = 0;
        
        println(msg);   
        String res = scanner.nextLine().substring(numeroTeclasPressionadas);
        numeroTeclasPressionadas = 0;
        return res;
    }
    
    /**
     * Lê do console a última frase escrita pelo usuário após pressionar ENTER.
     * Em alguns casos, podem aparecer os caracteres pressionados anteriormente 
     * pelo usuário, caso tenha sido usado o método getTecla() em algum momento.
     * Para garantir que não pareçam, obrigue o usuário a pressionar ENTER antes
     * da utilização deste método.
     * 
     * @see console.Console#getTecla()
     * 
     * @return String composta por tudo o que foi digitado até a tecla ENTER ser
     * pressionada.
     */
    public static String input(){
        return input("");
    }
    
    /**
     * Limpa a tela do console, eliminando quaisquer caracteres que estavam 
     * visíveis até então.
     * 
     */
    public static void limpaTela(){
        final String os = System.getProperty("os.name");
        try {
            if (os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033\143");
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um erro ao tentar limpar a tela do console");
        }
    }
    
    /**
     * Lê e retorna o p
                Runtime.getRuntime().exec("clear");róximo caractere que foi pressionado. A utilização deste 
     * método a qualquer momento no programa implica na obrigatoriedade de
     * fechá-lo manualmente ao final de sua execução. O desenvolvedor pode
     * garantir este fechamento ao acrescentar uma chamada ao método
     * saiDoPrograma() na última linha de sua main.
     * 
     * @return a última tecla a ser pressionada. Todas as teclas disponíveis se
     * encontram no Enum Tecla.
     * 
     * @see console.Console#saiDoPrograma()
     * @see console.Tecla
     */
    public static Tecla getTecla(){
        liberaAcessoAoTeclado();
        querTecla = true;
        
        while(querTecla){
            try {
                sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return ultimaTeclaPressionada;
    }
    
    private static synchronized boolean getEstadoAcessoAoTeclado(){
        return acessoLiberadoAoTecladoDoSistema;
    }
    
    private static synchronized void setEstadoAcessoAoTeclado(boolean b){
        acessoLiberadoAoTecladoDoSistema = b;
    }
    
    private static void liberaAcessoAoTeclado(){
        if(!getEstadoAcessoAoTeclado()){
            try {
                GlobalScreen.registerNativeHook();
            }catch (NativeHookException ex) {
                System.err.println("Não foi possível acessar o teclado.");
                System.err.println(ex.getMessage());
            }
            GlobalScreen.addNativeKeyListener(new AcessoAoTeclado());
            setEstadoAcessoAoTeclado(true);
        }
    }
    
    private static void removeAcessoAoTeclado(){
        if(getEstadoAcessoAoTeclado()){
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException ex) {
                System.err.println("Erro ao remover acesso ao teclado");
            }
            setEstadoAcessoAoTeclado(false);
        }
    }
    
    
    private static class AcessoAoTeclado implements NativeKeyListener{

        
        //retirado de https://stackoverflow.com/questions/1864076/equivalent-function-to-cs-getch-in-java
        //mudar rtetorno para tecla
        @Override
        public void nativeKeyPressed(NativeKeyEvent e) {
            if(querTecla){
//                System.out.println(e.getID());
//                System.out.println(e.getModifiers());
//                System.out.println(e.getRawCode());
//                System.out.println(e.getKeyCode());
//                System.out.println(e.getKeyChar());
//                System.out.println(e.getKeyLocation());
//                System.out.println(e.paramString());
                ultimaTeclaPressionada = Tecla.getTecla(e.getKeyCode());
                querTecla = false;
                numeroTeclasPressionadas +=1;
                if(ultimaTeclaPressionada.equals(Tecla.ENTER)){
                    numeroTeclasPressionadas = 0;
                    numeroENTERsPressionadas += 1;
                }
                
            }
        }
    }
    
}

