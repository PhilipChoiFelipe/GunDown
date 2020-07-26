/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.Entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import minsgame.Handler;
import minsgame.gfx.Assets;
import minsgame.tile.Tile;

/**
 *
 * @author 게임용 PC1
 */
public class streetLamp extends StaticEntity{
//    private float boundX = 15, boundY = 10;
//    private Rectangle boundTest;
    public streetLamp(Handler handler, float x, float y) {
        super(handler, new Rectangle(40, 90, 15, 100), x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);
//        boundTest = new Rectangle(0, 0, 0, 0);
//        this.boundTest.x=32;
//        this.boundTest.y=45;
//        this.boundTest.height = 38;
//        this.boundTest.width = 35;
    }

    @Override
    public void tick() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
            g.drawImage(Assets.streetLamp,(int)(x - handler.getGameCamera().getxOffSet()), (int) (y - handler.getGameCamera().getyOffSet()), width, height, null);    
            g.setColor(Color.red);
//            g.fillRect((int)(x + bound.x - handler.getGameCamera().getxOffSet()), (int) (y + bound.y - handler.getGameCamera().getyOffSet()), 
//                    bound.width, bound.height);
//        }
    }

    @Override
    public void die() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    

