/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cores;

import com.sun.jna.Function;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;

/**
 * Classe Enum que representa as possíveis cores ANSI, representáveis em
 * consoles conpatíveis, como o CMD e PowerShell do Windows e os terminais
 * Linux. É o responsável por setar a utilização dos códigos ANSI em consoles
 * Windows estaticamente, utilizando as funções da JNA.
 *
 * @author clique
 * @see com.sun.jna
 */
public enum Cor {
    
    /**
     * Representa o reset para as configurações padrões do Console.
     */
    RESET("\u001B[0m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    PRETO("\u001B[30m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    VERMELHO("\u001B[31m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    VERDE("\u001B[32m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    AMARELO("\u001B[33m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    AZUL("\u001B[34m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    ROXO("\u001B[35m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    CIANO  ("\u001B[36m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    BRANCO ("\u001B[37m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    CINZA ("\u001B[90m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    ROSA   ("\u001B[91m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    VERDE_CLARO ("\u001B[92m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    BEGE("\u001B[93m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    AZUL_CLARO  ("\u001B[94m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    ROSA_CHOQUE("\u001B[95m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    CIANO_CLARO  ("\u001B[96m"),
    /**
     * Representa a cor da fonte de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    BRANCO_BRANCO ("\u001B[97m"),
    
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_PRETO ("\u001B[40m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_VERMELHO   ("\u001B[41m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_VERDE ("\u001B[42m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_AMARELO("\u001B[43m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_AZUL  ("\u001B[44m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_ROXO("\u001B[45m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_CIANO  ("\u001B[46m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_BRANCO ("\u001B[47m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_CINZA ("\u001B[100m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_ROSA  ("\u001B[101m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_VERDE_CLARO ("\u001B[102m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_BEGE("\u001B[103m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_AZUL_CLARO  ("\u001B[104m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_ROSA_CHOQUE("\u001B[105m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_CIANO_CLARO  ("\u001B[106m"),
    /**
     * Representa a cor do fundo de acordo com seu nome. Varia em tons
     * dependendo do console utilizado.
     */
    FUNDO_BRANCO_BRANCO ("\u001B[107m");
    
    private final String prefixo;
    
    private Cor(String prefixo){
        this.prefixo = prefixo;
        //retirado de https://stackoverflow.com/questions/52767585/how-can-you-use-vt100-escape-codes-in-java-on-windows
        if(System.getProperty("os.name").startsWith("Windows"))
        {
            // Set output mode to handle virtual terminal sequences
            Function GetStdHandleFunc = Function.getFunction("kernel32", "GetStdHandle");
            WinDef.DWORD STD_OUTPUT_HANDLE = new WinDef.DWORD(-11);
            WinNT.HANDLE hOut = (WinNT.HANDLE)GetStdHandleFunc.invoke(WinNT.HANDLE.class, new Object[]{STD_OUTPUT_HANDLE});

            WinDef.DWORDByReference p_dwMode = new WinDef.DWORDByReference(new WinDef.DWORD(0));
            Function GetConsoleModeFunc = Function.getFunction("kernel32", "GetConsoleMode");
            GetConsoleModeFunc.invoke(WinDef.BOOL.class, new Object[]{hOut, p_dwMode});

            int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 4;
            WinDef.DWORD dwMode = p_dwMode.getValue();
            dwMode.setValue(dwMode.intValue() | ENABLE_VIRTUAL_TERMINAL_PROCESSING);
            Function SetConsoleModeFunc = Function.getFunction("kernel32", "SetConsoleMode");
            SetConsoleModeFunc.invoke(WinDef.BOOL.class, new Object[]{hOut, dwMode});
        }
    }
    
    /**
     * Retorna o prefixo ANSI utilizado para colorir a String de acordo com a 
     * Cor associada.
     * 
     * @return prefixo necessário para colorir a String com this.
     */
    public String getPrefixo(){
        return this.prefixo;
    }
    
    /**
     * Colore a String teste com todas as cores de fontes e fundos disponíveis.
     * 
     * @param teste String a ser colorida
     */
    public static void testaTodasAsCores(String teste){
        for (Cor cor : Cor.values()) 
            System.out.println(cor + " " + cor.prefixo +teste + RESET.prefixo);
    }
}
