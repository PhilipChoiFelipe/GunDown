/*
This class, Inventory stores item that player had collected. Only player class will makee instance of inventory! 
 */
package minsgame.inventory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import minsgame.Handler;
import minsgame.items.Item;

/**
 *
 * @author 게임용 PC1
 */
public class Inventory {
    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;
    
    public Inventory(Handler handler){
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();
    }
    
    public void tick(){
        //Activates Inventory mode when key "I" is pressed. 
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)){
            active = !active;
        }
        if(!active){
//            System.out.println("Inventory stopped");
            return;
        }
        System.out.println("Inventories");
        for(Item i : inventoryItems){
            System.out.println(i.getName() + "  " + i.getCount());
        }
//        System.out.println("Inventory working");
    }
    
    public void render(Graphics g){
    
    }
    
    //add items to player's inventory. 
    public void addItem(Item item){
        for(Item i : inventoryItems){
            if(i.getId() == item.getId()){
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
        
    }
    
    //Getters and Setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Item> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(ArrayList<Item> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }
    
    
    
}
