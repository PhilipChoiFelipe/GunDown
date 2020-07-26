/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.Entities.statics;

import java.awt.Rectangle;
import minsgame.Entities.Entity;
import minsgame.Handler;

/**
Entity that doesn't move around
 */
public abstract class StaticEntity extends Entity{
    
    public StaticEntity(Handler handler, Rectangle bound, float x, float y, int width, int height) {
        super(handler, bound, x, y, width, height);
    }
    
    @Override
    public void die(){
    
    }
    
}
