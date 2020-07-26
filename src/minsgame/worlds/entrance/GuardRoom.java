/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.worlds.entrance;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import minsgame.Entities.EntityManager;
import minsgame.Entities.creatures.Player;
import minsgame.Entities.creatures.monsters.Runner;
import minsgame.Entities.creatures.monsters.Zombie;
import minsgame.Entities.statics.ComputerSet;
import minsgame.Entities.statics.WeaponCloset;
import minsgame.Handler;
import static minsgame.gfx.Assets.weaponCloset;
import minsgame.items.ItemManager;
import minsgame.worlds.World;

/**
 *
 * @author 게임용 PC1
 */
public class GuardRoom extends World{
    private Rectangle portalBoundOne;
    public GuardRoom(Handler handler, String path, Player managerPlayer, int id) {
        super(handler, path, managerPlayer, id);
        //Entities
        entityManager = new EntityManager(handler, player);
//        entityManager.addEntity(new Runner(handler, entityManager, 400, 300));
        entityManager.addEntity(new ComputerSet(handler, 500, 150));
        entityManager.addEntity(new WeaponCloset(handler, 400, 400));
        
        //Portal
        portalBoundOne = new Rectangle(800, 200, 100, 100);
        
        //Item
        itemManager = new ItemManager(handler);
        
        loadWorld(path);
        
        //Setting Player
        player.setX(spawnX);
        player.setY(spawnY);
    }

    @Override
    public void tick() {
        entityManager.tick();
        itemManager.tick();
    }

    @Override
    public void render(Graphics g) {
        renderTile(g);
        entityManager.render(g);
        itemManager.render(g);
        g.setColor(Color.cyan);
        g.fillRect((int) (portalBoundOne.x - handler.getGameCamera().getxOffSet()), 
                (int)(portalBoundOne.y - handler.getGameCamera().getyOffSet()), portalBoundOne.width, portalBoundOne.height);
        
    }

    @Override
    public int getPortal() {
        if(portalBoundOne.contains(player.getCollisionBounds(0, 0))){
            currentPortal = portalBoundOne;
            player.setX(200);
            player.setY(800);
            return 0;
        } else {
            return 1;
        }
    }
}
