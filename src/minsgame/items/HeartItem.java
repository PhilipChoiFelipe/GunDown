/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.items;

import java.awt.image.BufferedImage;
import minsgame.Handler;
import minsgame.gfx.Assets;

/**
 *
 * @author 게임용 PC1
 */
public class HeartItem extends Item{
    
    public HeartItem(String name, int id, PickedUp pickedUp) {
        super(Assets.heart, name, id, pickedUp);
    }

//    @Override
//    public void pickedUp(){
//        System.out.println(handler.getWorldManager().getPlayer().getHealth());
//        handler.getWorldManager().getPlayer().increaseHealth(5);
//    }
}
