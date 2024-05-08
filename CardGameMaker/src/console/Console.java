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
 * Versão 0.1 - Inicial
 * Versão 0.2 - Suporte para UTF-8
 * 
 *
 * @author clique
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
    
    public static void saiDoPrograma(){
        System.exit(0);
    }
    
    public static void print(Object obj){
        printer.print(obj);
    }
    
    public static void println(Object obj){
        print(obj+"\n");
    }
    
    public static String input(String msg){  
        for (int i = 0; i < numeroENTERsPressionadas; i++) 
            scanner.nextLine();
        numeroENTERsPressionadas = 0;
        
        System.out.println(msg);   
        String res = scanner.nextLine().substring(numeroTeclasPressionadas);
        numeroTeclasPressionadas = 0;
        return res;
    }
    public static String input(){
        return input("");
    }
    public static void limpaTela(){
        final String os = System.getProperty("os.name");
        try {
            if (os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um erro ao tentar limpar a tela do console");
        }
    }
    
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

