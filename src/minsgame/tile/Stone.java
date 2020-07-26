/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.tile;

import minsgame.gfx.Assets;

/**
 *
 * @author 게임용 PC1
 */
public class Stone extends Tile{
    
    public Stone(int id) {
        super(Assets.stone, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
}
