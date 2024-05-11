/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

/**
 * Classe Enum utilizada para representar os códigos das teclas reconhecíveis
 * pela classe Console.
 *
 * @author clique
 * @see console.Console
 */
public enum Tecla {
    /**
     * Representa a seta para cima no teclado.
     */
    UP(NativeKeyEvent.VC_UP),
    /**
     * Representa a seta para baixo no teclado.
     */
    DOWN(NativeKeyEvent.VC_DOWN),
    /**
     * Representa a seta para direita no teclado.
     */
    RIGHT(NativeKeyEvent.VC_RIGHT),
    /**
     * Representa a seta para esquerda no teclado.
     */
    LEFT(NativeKeyEvent.VC_LEFT),
    
    /**
     * Representa a tecla ENTER.
     */
    ENTER(NativeKeyEvent.VC_ENTER),
    
    /**
     * Representa a tecla ESC.
     */
    ESC(NativeKeyEvent.VC_ESCAPE),
    
    /**
     * Representa a tecla TAB.
     */
    TAB(NativeKeyEvent.VC_TAB),
    /**
     * Representa a tecla CTRL.
     */
    CTRL(NativeKeyEvent.VC_CONTROL),
    /**
     * Representa a tecla ALT.
     */
    ALT(NativeKeyEvent.VC_ALT),
    /**
     * Representa a tecla SHIFT da esquerda.
     */
    SHIFT(NativeKeyEvent.VC_SHIFT),
    /**
     * Representa a tecla ESPAÇO.
     */
    ESPACO(NativeKeyEvent.VC_SPACE),
    
    
    /**
     * Representa a tecla da letra correspondente.
     */
    A(NativeKeyEvent.VC_A),
    /**
     * Representa a tecla da letra correspondente.
     */
    B(NativeKeyEvent.VC_B),
    /**
     * Representa a tecla da letra correspondente.
     */
    C(NativeKeyEvent.VC_C),
    /**
     * Representa a tecla da letra correspondente.
     */
    D(NativeKeyEvent.VC_D),
    /**
     * Representa a tecla da letra correspondente.
     */
    E(NativeKeyEvent.VC_E),
    /**
     * Representa a tecla da letra correspondente.
     */
    F(NativeKeyEvent.VC_F),
    /**
     * Representa a tecla da letra correspondente.
     */
    G(NativeKeyEvent.VC_G),
    /**
     * Representa a tecla da letra correspondente.
     */
    H(NativeKeyEvent.VC_H),
    /**
     * Representa a tecla da letra correspondente.
     */
    I(NativeKeyEvent.VC_I),
    /**
     * Representa a tecla da letra correspondente.
     */
    J(NativeKeyEvent.VC_J),
    /**
     * Representa a tecla da letra correspondente.
     */
    K(NativeKeyEvent.VC_K),
    /**
     * Representa a tecla da letra correspondente.
     */
    L(NativeKeyEvent.VC_L),
    /**
     * Representa a tecla da letra correspondente.
     */
    M(NativeKeyEvent.VC_M),
    /**
     * Representa a tecla da letra correspondente.
     */
    N(NativeKeyEvent.VC_N),
    /**
     * Representa a tecla da letra correspondente.
     */
    O(NativeKeyEvent.VC_O),
    /**
     * Representa a tecla da letra correspondente.
     */
    P(NativeKeyEvent.VC_P),
    /**
     * Representa a tecla da letra correspondente.
     */
    Q(NativeKeyEvent.VC_Q),
    /**
     * Representa a tecla da letra correspondente.
     */
    R(NativeKeyEvent.VC_R),
    /**
     * Representa a tecla da letra correspondente.
     */
    S(NativeKeyEvent.VC_S),
    /**
     * Representa a tecla da letra correspondente.
     */
    T(NativeKeyEvent.VC_T),
    /**
     * Representa a tecla da letra correspondente.
     */
    U(NativeKeyEvent.VC_U),
    /**
     * Representa a tecla da letra correspondente.
     */
    V(NativeKeyEvent.VC_V),
    /**
     * Representa a tecla da letra correspondente.
     */
    W(NativeKeyEvent.VC_W),
    /**
     * Representa a tecla da letra correspondente.
     */
    X(NativeKeyEvent.VC_X),
    /**
     * Representa a tecla da letra correspondente.
     */
    Y(NativeKeyEvent.VC_Y),
    /**
     * Representa a tecla da letra correspondente.
     */
    Z(NativeKeyEvent.VC_Z),
    /**
     * Representa a tecla da letra correspondente.
     */
    CEDILHA(NativeKeyEvent.VC_SEMICOLON),
    
    /**
     * Representa a tecla do número correspondente.
     */
    NUM_0(NativeKeyEvent.VC_0),
    /**
     * Representa a tecla do número correspondente.
     */
    NUM_1(NativeKeyEvent.VC_1),
    /**
     * Representa a tecla do número correspondente.
     */
    NUM_2(NativeKeyEvent.VC_2),
    /**
     * Representa a tecla do número correspondente.
     */
    NUM_3(NativeKeyEvent.VC_3),
    /**
     * Representa a tecla do número correspondente.
     */
    NUM_4(NativeKeyEvent.VC_4),
    /**
     * Representa a tecla do número correspondente.
     */
    NUM_5(NativeKeyEvent.VC_5),
    /**
     * Representa a tecla do número correspondente.
     */
    NUM_6(NativeKeyEvent.VC_6),
    /**
     * Representa a tecla do número correspondente.
     */
    NUM_7(NativeKeyEvent.VC_7),
    /**
     * Representa a tecla do número correspondente.
     */
    NUM_8(NativeKeyEvent.VC_8),
    /**
     * Representa a tecla do número correspondente.
     */
    NUM_9(NativeKeyEvent.VC_9);
    
    private final int codigoTecla;
    
    private Tecla(int codigoTecla){
        this.codigoTecla = codigoTecla;
    }
    
    /**
     * Método que retorna a Tecla associada ao código passado.
     * 
     * @param codigoTecla código da tecla, proveniente de NativeKeyEvent.
     * @return a Tecla com o código passado
     * 
     * @see com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
     */
    public static Tecla getTecla(int codigoTecla){
        for (Tecla tecla : Tecla.values()) {
            if(codigoTecla == tecla.codigoTecla)
                return tecla;
        }
        throw new RuntimeException("Não há tecla associada a este código");
    }
}
