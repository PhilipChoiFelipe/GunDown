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
public class WeaponCloset extends StaticEntity{

    public WeaponCloset(Handler handler, float x, float y) {
        super(handler, new Rectangle(10, 20, 80, 120), x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);
    }

    @Override
    public void tick() {
    
    }

    @Override
    public void render(Graphics g) {
                g.drawImage(Assets.weaponCloset ,(int)(x - handler.getGameCamera().getxOffSet()), (int) (y - handler.getGameCamera().getyOffSet()), width, height, null);    
//            g.setColor(Color.red);
//            g.fillRect((int)(x + bound.x - handler.getGameCamera().getxOffSet()), (int) (y + bound.y - handler.getGameCamera().getyOffSet()), 
//                    bound.width, bound.height);
    }
    
}
