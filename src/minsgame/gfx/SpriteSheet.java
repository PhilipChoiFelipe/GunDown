/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.gfx;

import java.awt.image.BufferedImage;
//sheet = 300 x 300  6 x 6
/**
Sheet image 를 받은후, 이 클래스에 저장하면, Crop function 으로 짜를수있다!
 */
public class SpriteSheet {
    private BufferedImage sheet;
    
    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }
    
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }
    
    
}
