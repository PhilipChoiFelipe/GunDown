/*
SubClass of World Class. This class extends
 */
package minsgame.worlds.entrance;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import minsgame.Entities.EntityManager;
import minsgame.Entities.creatures.Player;
import minsgame.Entities.creatures.monsters.Runner;
import minsgame.Entities.creatures.monsters.Zombie;
import minsgame.Entities.statics.streetLamp;
import minsgame.Entities.statics.trashCan;
import minsgame.Handler;
import minsgame.items.ItemManager;
import minsgame.worlds.World;

/**
 *
 * @author 게임용 PC1
 */
public class BokjiEntrance extends World{
    private Rectangle portalBoundOne;
    private Rectangle portalBoundTwo;
    private Rectangle portalBoundThree;
    public BokjiEntrance(Handler handler, String path, Player managerPlayer, int id) {
        
        super(handler, path, managerPlayer, id);
        
        portalBoundOne = new Rectangle(0, 800, 100, 100);
        portalBoundTwo = new Rectangle(0, 100, 100, 100);
        portalBoundThree = new Rectangle(1100, 600, 100, 100);
        entityManager = new EntityManager(handler, player);
        itemManager = new ItemManager(handler);
        
//        entityManager.addEntity(new Tree(handler, 500, 800));
        entityManager.addEntity(new streetLamp(handler, 350, 0));
        entityManager.addEntity(new streetLamp(handler, 350, 300));
        entityManager.addEntity(new streetLamp(handler, 750, 150));
        entityManager.addEntity(new streetLamp(handler, 750, 500));
        entityManager.addEntity(new trashCan(handler, 700, 100));
        entityManager.addEntity(new trashCan(handler, 200, 540));
        entityManager.addEntity(new Zombie(handler, entityManager, 200, 400));
        entityManager.addEntity(new Zombie(handler, entityManager, 800, 100));
        entityManager.addEntity(new Zombie(handler, entityManager, 600, 600));
        entityManager.addEntity(new Zombie(handler, entityManager, 300, 500));
        entityManager.addEntity(new Zombie(handler, entityManager, 500, 300));
        entityManager.addEntity(new Runner(handler, entityManager, 900, 200));
        
        loadWorld(path);
//        handler.getWorldManager().getPlayer().setX(spawnX);
//        handler.getWorldManager().getPlayer().setY(spawnY);
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
        g.fillRect((int) (portalBoundTwo.x - handler.getGameCamera().getxOffSet()), 
                (int)(portalBoundTwo.y - handler.getGameCamera().getyOffSet()), portalBoundTwo.width, portalBoundTwo.height);
        g.fillRect((int) (portalBoundThree.x - handler.getGameCamera().getxOffSet()), 
                (int)(portalBoundThree.y - handler.getGameCamera().getyOffSet()), portalBoundThree.width, portalBoundThree.height);
    }

    @Override
    public int getPortal() {
        if(portalBoundOne.contains(player.getCollisionBounds(0, 0))){
            currentPortal = portalBoundOne;
            player.setX(700);
            player.setY(400);
            return 1;
        } else {
            return 0;
        }
    }
}
