/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.Entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import minsgame.Entities.creatures.bullet.BulletManager;
import minsgame.Entities.Entity;
import minsgame.Game;
import minsgame.Handler;
import minsgame.tile.Tile;

/**
엔티티 클래스에 상속되는 Creature 클래스. 이 클래스에 상속되는 클래스의 x,y 값 조정, health, speed를 관리한다. 
 */
public abstract class Creature extends Entity{
    public static final float DEFAULT_SPEED = 5.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 120, 
                            DEFAULT_CREATURE_HEIGHT = 120;
    protected float speed;
    protected float xMove, yMove;
    protected boolean bulletReached = false;
    
    public Creature(Handler handler, Rectangle bound, float x, float y, int width, int height) {
        super(handler,bound, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }
    
    //이 클래스에서 상속되는 클래스에서 xMove, yMove를 바꾼다. 또한 그 클래스에서 tick 으로 부른다. 
    public void move(){
        if(!checkEntityCollisions(xMove, 0f))
        moveX();
        if(!checkEntityCollisions(0f, yMove))
        moveY();
    }
    
    public void moveX(){
        //right side
        if(xMove > 0){
            int tx = (int) (x + xMove + bound.x + bound.width) / Tile.TILEWIDTH;
            //Upper Side of box
            if(!collisionWithTile(tx, (int) (y + bound.y) / Tile.TILEHEIGHT) && 
                    //bottom side of box
                    !collisionWithTile(tx, (int) (y + bound.y + bound.height)/ Tile.TILEHEIGHT)){
                x += xMove;
            }else{
                x = tx * Tile.TILEWIDTH - bound.x - bound.width - 1;
                bulletReached = true;
            }
        //left Side
        }else if(xMove < 0){
            int tx = (int) (x + xMove + bound.x) / Tile.TILEWIDTH;
            //upper Side of box
            if(!collisionWithTile(tx, (int) (y + bound.y) / Tile.TILEHEIGHT) && 
                    //bottom side of box
                    !collisionWithTile(tx, (int) (y + bound.y + bound.height)/ Tile.TILEHEIGHT)){
                x+= xMove;
            }else{
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bound.x;
                bulletReached = true;
            }
        }
    }
    
    public void moveY(){
        //Upper Side
        if(yMove < 0){
            int ty = (int) (y + yMove + bound.y) / Tile.TILEHEIGHT;
            //left side Of box
            if(!collisionWithTile( (int) (x + bound.x) / Tile.TILEWIDTH, ty) && 
                    //right side of box
                    !collisionWithTile( (int) (x + bound.x + bound.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILEWIDTH + Tile.TILEHEIGHT - bound.y;
                bulletReached = true;
            }
        }else if(yMove > 0){
            //bottom side
            int ty = (int) (y + yMove + bound.y + bound.height) / Tile.TILEHEIGHT;
            //left side of box
            if(!collisionWithTile( (int) (x + bound.x) / Tile.TILEWIDTH, ty) && 
                    //right side of box
                    !collisionWithTile( (int) (x + bound.x + bound.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILEHEIGHT - bound.y - bound.height - 1;
                bulletReached = true;
            }
        }
    }
    
    public boolean collisionWithTile(int x, int y){
        return handler.getWorldManager().getCurrentWorld().getTile(x, y).isSolid(); 
    }
    

    
    //Getter and Setter
    public float getxMove() {
        return xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health){
        health = health;
    }
    

    
    public float getSpeed() {
        return speed;
    }
    
}
