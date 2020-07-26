/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.Entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import minsgame.Entities.creatures.Player;
import minsgame.Handler;

/**
Manage the number and position of manager; 
 */
public class EntityManager {
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight()){
                //if Entity a has lower Y value, it should render before b. 
                return -1;
            }
            return 1;
        }
    };
    
    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }
    
    public void tick(){
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()){
            Entity e = it.next();
            e.tick();
            //If entity's health is 0, we don't tick and render that entity.  (Remove it from ArrayList)
            if(!e.isActive()){
                it.remove();
            }
        }
        entities.sort(renderSorter);
    }
    
    public void render(Graphics g){
        for(Entity e : entities){
            e.render(g);
        }
    }
    
    public void addEntity(Entity e){
        entities.add(e);
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

//    public void setPlayer(Player player) {
//        this.player = player;
//    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public Handler getHandler() {
        return handler;
    }
//
//    public Player getPlayer() {
//        return player;
//    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
