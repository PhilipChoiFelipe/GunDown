/*
Manages World. IT should control which world will appear based on player's position. 
 */
package minsgame.worlds;

import java.awt.Graphics;
import java.awt.Rectangle;
import minsgame.Entities.EntityManager;
import minsgame.Entities.creatures.Player;
import minsgame.Handler;
import minsgame.items.ItemManager;
import minsgame.worlds.entrance.BokjiEntrance;
import minsgame.worlds.entrance.GuardRoom;

/**
 *
 * @author 게임용 PC1
 */
public class WorldManager {
    public static final int DEFAULT_SPAWNX = 0, DEFAULT_SPAWNY = 0;
    public static World[] worlds = new World[256];
    public static World bokjiEntrance, guardRoom;
    private World currentWorld;
    private Handler handler;
    private Player player;
    
    public WorldManager(Handler handler){
        this.handler = handler;
        player = new Player(handler, DEFAULT_SPAWNX, DEFAULT_SPAWNY);
        
        //Creates set of map!!
        bokjiEntrance = new BokjiEntrance(handler, "/worlds/world1.txt", player, 0);
        guardRoom = new GuardRoom(handler, "/worlds/guardRoom.txt", player, 1);
        setCurrentWorld(worlds[1]);
    }
    
    //If player's bound intersects with map's entrance bound, world loads different world!
    public World getCurrentWorld(){
        return currentWorld;
    }
    
    public void setCurrentWorld(World world){
        this.currentWorld = world;
    }
    
    //Checks if player gets into certain portalBound!
    public void checkWorld(){
        this.currentWorld = worlds[currentWorld.getPortal()];
    }
    
    public void tick(){
        checkWorld();
        getCurrentWorld().tick();
    }
    
    public void render(Graphics g){
        getCurrentWorld().render(g);
    }

    public Player getPlayer() {
        return player;
    }
}
