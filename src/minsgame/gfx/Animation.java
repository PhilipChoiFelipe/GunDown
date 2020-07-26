/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author 게임용 PC1
 */
public class Animation {
    private int speed, index; 
    private BufferedImage[] frames;
    private long lastTime, timer;
    
    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed; 
        this.frames = frames;
        index = 0;
        lastTime = System.currentTimeMillis();
        timer = 0;
    }
    
    //Change the index of animation pics everyTime when player(entity) class ticks. 
    public void tick(){
        //adding timer last tick time + this tick time. 
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if(timer > speed){
            index++;
            timer = 0;
            //if the index is longer than number of frames, resets index. 
            if(index >= frames.length){
                index = 0;
            }
        }
    }
    
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

    public int getIndex() {
        return index;
    }
    
}
