/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
Manages which key is pressed so in Entity Class, we can access it to check which key user pressed. 
 */
public class KeyManager implements KeyListener{
    private boolean[] keys, justPressed, cantPress;
    public boolean up, down, left, right;
     public boolean aUp, aDown, aLeft, aRight;
    public KeyManager(){
        //when ever the key is pressed, key's index gonna be true in array. 
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }
    
    public void tick(){
        for(int i = 0; i < keys.length; i++){
            //if cantPress is true and that key is not pressed yet, cantpress = false
            if(cantPress[i] && !keys[i]){
                cantPress[i] = false;
            //if that key is just pressed, justPressed = false, cantPress = true
            }else if(justPressed[i]){
                cantPress[i] = true;
                justPressed[i] = false;
            }
            //if CantPress is false and key is true = key has been pressed. 
            if(!cantPress[i] && keys[i]){
                justPressed[i] = true;
            }
        }
        
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        
        aUp = keys[KeyEvent.VK_UP];
        aDown = keys[KeyEvent.VK_DOWN];
        aLeft = keys[KeyEvent.VK_LEFT];
        aRight = keys[KeyEvent.VK_RIGHT];
    }

    public boolean keyJustPressed(int keyCode){
        if(keyCode < 0 || keyCode >= keys.length){
            return false;
        }
        return justPressed[keyCode];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    //when user presses key
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
//        System.out.println("pressed");
    }

    @Override
    //when user letgo of key
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length){
            return;
        }
        keys[e.getKeyCode()] = false;
    }
    
}
