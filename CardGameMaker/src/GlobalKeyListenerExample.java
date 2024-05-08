

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;



class GlobalKeyListenerExample implements NativeKeyListener {
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException ex) {
                Logger.getLogger(GlobalKeyListenerExample.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

    public static void main1(String[] args) throws InterruptedException {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new GlobalKeyListenerExample());
        
        while(true){
            sleep(1000);
            //NATIVE_KEY_PRESSED,keyCode=38,keyText=L,keyChar=Undefined,modifiers=Num Lock,keyLocation=KEY_LOCATION_STANDARD,rawCode=108
//            GlobalScreen.postNativeEvent(new NativeKeyEvent(NativeKeyEvent.NATIVE_KEY_PRESSED,
//                                                            NativeKeyEvent.NUM_LOCK_MASK, 
//                                                            108, 
//                                                            38,
//                                                            NativeKeyEvent.CHAR_UNDEFINED));
        }
    }
}