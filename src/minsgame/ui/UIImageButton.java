/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
if it is clicked, it will fire something that we coded in interface. 
*/
public class UIImageButton extends UIObject{
    private BufferedImage[] images;
    private ClickListener clicker;
    
    //Gets the super class' mendatory parameters and images of object, clicker(interface) that will fire when it is clicked.
    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener click) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = click;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        if(hovering){
            g.drawImage(images[1], (int) x, (int) y, width, height, null);
        }else{
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void onClick() {
        //pass in different images, clicklisteners, much efficient. 
        clicker.onClick();
    }
    
}
