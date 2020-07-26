/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.tile;

import java.awt.image.BufferedImage;
import minsgame.gfx.Assets;

/**
 *
 * @author 게임용 PC1
 */
public class Dark extends Tile{
    
    public Dark(int id) {
        super(Assets.dark, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
}
