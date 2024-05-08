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
 *
 * @author clique
 */
public enum Cor {
    
    RESET("\u001B[0m"),
    PRETO("\u001B[30m"),
    VERMELHO("\u001B[31m"),
    VERDE("\u001B[32m"),
    AMARELO("\u001B[33m"),
    AZUL("\u001B[34m"),
    ROXO("\u001B[35m"),
    CIANO  ("\u001B[36m"),
    BRANCO ("\u001B[37m"),
    CINZA ("\u001B[90m"),
    ROSA   ("\u001B[91m"),
    VERDE_CLARO ("\u001B[92m"),
    BEGE("\u001B[93m"),
    AZUL_CLARO  ("\u001B[94m"),
    ROSA_CHOQUE("\u001B[95m"),
    CIANO_CLARO  ("\u001B[96m"),
    BRANCO_BRANCO ("\u001B[97m"),
    
    FUNDO_PRETO ("\u001B[40m"),
    FUNDO_VERMELHO   ("\u001B[41m"),
    FUNDO_VERDE ("\u001B[42m"),
    FUNDO_AMARELO("\u001B[43m"),
    FUNDO_AZUL  ("\u001B[44m"),
    FUNDO_ROXO("\u001B[45m"),
    FUNDO_CIANO  ("\u001B[46m"),
    FUNDO_BRANCO ("\u001B[47m"),
    FUNDO_CINZA ("\u001B[100m"),
    FUNDO_ROSA  ("\u001B[101m"),
    FUNDO_VERDE_CLARO ("\u001B[102m"),
    FUNDO_BEGE("\u001B[103m"),
    FUNDO_AZUL_CLARO  ("\u001B[104m"),
    FUNDO_ROSA_CHOQUE("\u001B[105m"),
    FUNDO_CIANO_CLARO  ("\u001B[106m"),
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
    
    public String getPrefixo(){
        return this.prefixo;
    }
    
    public static void testaTodasAsCores(String teste){
        for (Cor cor : Cor.values()) 
            System.out.println(cor + " " + cor.prefixo +teste + RESET.prefixo);
    }
}
