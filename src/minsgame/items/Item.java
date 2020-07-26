/*
class of items that player, user can collect and store in this world. 
 */
package minsgame.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import minsgame.Handler;
import minsgame.gfx.Assets;

/**
 *
 * @author 게임용 PC1
 */
public class Item {
    //Handler
    //How we going to store each item in Item array.
    public static Item[] items = new Item[256];
    public static Item heartItem = new HeartItem("Heart", 0, new PickedUp(){
        @Override
        public void pickedUp(Handler handler) {
            handler.getWorldManager().getPlayer().increaseHealth(1);
        }
    });
    
    //Class
    public static final int ITEMWIDTH = 50, ITEMHEIGHT = 50;
    private boolean PICKED_UP;
    protected Handler handler;
    protected BufferedImage texture;
    protected int x, y, count;
    protected int id;
    protected String name;
    protected PickedUp pickedUp;
    
    //Item bound
    protected Rectangle bound;
    
    public Item(BufferedImage texture, String name, int id, PickedUp pickedUp){
        //Interface
        this.pickedUp = pickedUp;
        
        this.texture = texture;
        //name of item
        this.name = name;
        //identification of item
        this.id = id;
        //how many items
        count = 1;
        
        //Store this created item in the array with specific id number.
        items[id] = this;
        
        //Bound of item
        bound = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
    }
    

    public void tick(){
        if(handler.getWorldManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bound)){
            this.PICKED_UP = true;
            //Adding to the player's inventory.
            handler.getWorldManager().getPlayer().getInventory().addItem(this);
            if(PICKED_UP){
                pickedUp.pickedUp(handler);
            }
        }
    }

    //To draw in the Inventory section.
    public void render(Graphics g, int x, int y){
        if(handler == null){
            return;
        }
        g.drawImage(texture, x, y, null);
    }
    
    //To draw in the world relative to the player's movement. 
    public void render(Graphics g){
        render(g,(int) (x - handler.getGameCamera().getxOffSet()), (int) (y - handler.getGameCamera().getyOffSet()));
    }

    //To create new item that will be located in the world with specific position.
    public Item createNew(int x, int y){
        Item i = new Item(texture, name, id, pickedUp);
        i.setPosition(x, y);
        return i;
    }
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bound.x = x;
        bound.y = y;
    }
    
    //Getters and Setters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPICKED_UP() {
        return PICKED_UP;
    }
}
