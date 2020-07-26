/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.Entities.statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import minsgame.Handler;
import minsgame.gfx.Assets;
import minsgame.tile.Tile;

/**
 *
 * @author 게임용 PC1
 */
public class Tree extends StaticEntity{

    public Tree(Handler handler, float x, float y) {
        super(handler, new Rectangle(32, 45, 38, 35), x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
    }
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        //this tree stays in that spot because its x and y are stationary. 
        g.drawImage(Assets.tree,(int)(x - handler.getGameCamera().getxOffSet()), (int) (y - handler.getGameCamera().getyOffSet()), width, height, null);
    }

    @Override
    public void die() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    
    
}
