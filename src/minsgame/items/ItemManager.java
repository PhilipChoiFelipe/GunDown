/*
Manages the item in this class. This stores all type of Items and tick, render. 
 */
package minsgame.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import minsgame.Handler;

/**
 *
 * @author 게임용 PC1
 */
public class ItemManager {
    private Handler handler;
    private ArrayList<Item> items;
    
    public ItemManager(Handler handler){
        this.handler = handler;
        items = new ArrayList<Item>();
    }
    
    public void tick(){
        Iterator<Item> it = items.iterator();
        while(it.hasNext()){
            Item i = it.next();
            i.tick();
            //If item is picked up by player, remove it from items array.
            if(i.isPICKED_UP()){
                it.remove();
          
//                handler.getWorldManager().getPlayer().increaseHealth(1);
            }
        }
    }
    
    public void render(Graphics g){
        for(Item i : items){
            i.render(g);
        }
    }
    
    public void addItem(Item i){
        //To make sure item's handler is not null because ItemManager will have instance of game's handler. 
        i.setHandler(handler);
        items.add(i);
    }
    
    //Getters and Setters

    public Handler getHandler() {
        return handler;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    
    
}
