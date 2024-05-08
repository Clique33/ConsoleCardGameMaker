/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

/**
 *
 * @author clique
 */
public enum Tecla {
    UP(NativeKeyEvent.VC_UP),
    DOWN(NativeKeyEvent.VC_DOWN),
    RIGHT(NativeKeyEvent.VC_RIGHT),
    LEFT(NativeKeyEvent.VC_LEFT),
    
    ENTER(NativeKeyEvent.VC_ENTER),
    ESC(NativeKeyEvent.VC_ESCAPE),
    TAB(NativeKeyEvent.VC_TAB),
    CTRL(NativeKeyEvent.VC_CONTROL),
    ALT(NativeKeyEvent.VC_ALT),
    SHIFT(NativeKeyEvent.VC_SHIFT),
    ESPACO(NativeKeyEvent.VC_SPACE),
    
    A(NativeKeyEvent.VC_A),
    B(NativeKeyEvent.VC_B),
    C(NativeKeyEvent.VC_C),
    D(NativeKeyEvent.VC_D),
    E(NativeKeyEvent.VC_E),
    F(NativeKeyEvent.VC_F),
    G(NativeKeyEvent.VC_G),
    H(NativeKeyEvent.VC_H),
    I(NativeKeyEvent.VC_I),
    J(NativeKeyEvent.VC_J),
    K(NativeKeyEvent.VC_K),
    L(NativeKeyEvent.VC_L),
    M(NativeKeyEvent.VC_M),
    N(NativeKeyEvent.VC_N),
    O(NativeKeyEvent.VC_O),
    P(NativeKeyEvent.VC_P),
    Q(NativeKeyEvent.VC_Q),
    R(NativeKeyEvent.VC_R),
    S(NativeKeyEvent.VC_S),
    T(NativeKeyEvent.VC_T),
    U(NativeKeyEvent.VC_U),
    V(NativeKeyEvent.VC_V),
    W(NativeKeyEvent.VC_W),
    X(NativeKeyEvent.VC_X),
    Y(NativeKeyEvent.VC_Y),
    Z(NativeKeyEvent.VC_Z),
    CEDILHA(NativeKeyEvent.VC_SEMICOLON),
    
    NUM_0(NativeKeyEvent.VC_0),
    NUM_1(NativeKeyEvent.VC_1),
    NUM_2(NativeKeyEvent.VC_2),
    NUM_3(NativeKeyEvent.VC_3),
    NUM_4(NativeKeyEvent.VC_4),
    NUM_5(NativeKeyEvent.VC_5),
    NUM_6(NativeKeyEvent.VC_6),
    NUM_7(NativeKeyEvent.VC_7),
    NUM_8(NativeKeyEvent.VC_8),
    NUM_9(NativeKeyEvent.VC_9);
    
    private final int codigoTecla;
    
    private Tecla(int codigoTecla){
        this.codigoTecla = codigoTecla;
    }
    
    public static Tecla getTecla(int codigoTecla){
        for (Tecla tecla : Tecla.values()) {
            if(codigoTecla == tecla.codigoTecla)
                return tecla;
        }
        throw new RuntimeException("Não há tecla associada a este código");
    }
}
