/*
Its abstract because we want to make it specific for subclasses. 
 */
package minsgame.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import minsgame.Game;
import minsgame.Handler;

/**
 *
게임에서 모든 움직이는 클래스를 상징. Abstract 클래스로써 상속되는 클래스의 x,y,width,height 를 필수적으로 가지게한다. 
 */
public abstract class Entity {
    public static final int DEFAULT_HEALTH = 5;
    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected int health;
    protected boolean active = true;
    protected Rectangle bound;
    protected boolean Hurt = false;
    public Entity(Handler handler, Rectangle bound, float x, float y, int width, int height){
        this.health = DEFAULT_HEALTH;
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //This means rectangle starts at the leftupper corner of entity. 
        this.bound = bound;
    }
    
    public void hurt(int amt){
//        System.out.println("Hurt " + this + " Entity 36");
        health -= amt;
        Hurt = true;
        if(health <= 0){
            active = false;
            die();
        }
    }
    
    public abstract void die();
    
    //offSet here represents the xMove and yMove of Player.
    //check every static entities' collision whenever player makes a move. 
    public boolean checkEntityCollisions(float xOffSet, float yOffSet){
        for(Entity e : handler.getWorldManager().getCurrentWorld().getEntityManager().getEntities()){
            //if Entity we looping through is itself. 
            if(e.equals(this))
                continue;
            //checking every Entities bounding box, intersects with players bounding box. 
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffSet, yOffSet)))
                return true;
        }
        return false;
    }
    
    //Returns the collisiopn bound of entity. 
    public Rectangle getCollisionBounds(float xOffSet, float yOffSet){
        //x + xBound 
        return new Rectangle((int) (x + bound.x + xOffSet), (int) (y + bound.y + yOffSet), bound.width, bound.height);
    }
    
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public float getWidth(){
        return width;
    }
    
    public float getHeight(){
        return height;
    }

    
    public boolean isActive(){
        return active;
    }
    
    public void increaseHealth(int amt){
        if(health < DEFAULT_HEALTH){
            health+=amt;
        }
    }

    public Rectangle getBound() {
        return bound;
    }
    
    
    
    
    public abstract void tick();
    public abstract void render(Graphics g);
}
