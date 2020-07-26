/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;



/**
다른곳에서 이미지를 쉽게 삽입하게 만들어놓은 클래스. 
 */
public class ImageLoader {
    public BufferedImage loadImage(String path){
       try{
           //returns bufferedImage!
           return ImageIO.read(getClass().getResourceAsStream(path));
       } catch(IOException e){
           e.printStackTrace();
           System.exit(1);
       }
       return null;
    }
}
    
