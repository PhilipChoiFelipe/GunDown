/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.Entities.creatures.bullet;

/**
 *
 * @author 게임용 PC1
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import minsgame.Entities.Entity;
import minsgame.Entities.creatures.Creature;
import minsgame.Handler;

/**
 *
 * @author 게임용 PC1
 */
public abstract class Bullet extends Creature{
    public static final int DEFAULT_BULLETWIDTH = 15, DEAFULT_BULLETHEIGHT = 15;
    public static final float DEFAULT_BULLETRANGE= 500f;
    protected float bulletRange;
    protected Handler handler;
    protected float directionX, directionY, distanceWent;
    protected boolean bulletGone = false;

    public Bullet(Handler handler, float x, float y) {
        super(handler, new Rectangle(0, 0, DEFAULT_BULLETWIDTH, DEAFULT_BULLETHEIGHT), x, y, DEFAULT_BULLETWIDTH, DEAFULT_BULLETHEIGHT);
        this.handler = handler;        
        this.bulletRange = DEFAULT_BULLETRANGE;
    }

    public abstract void checkAttack();
    
    //Uses super class' field to use it in move class. 
    public void proceedBullet(){
        xMove = directionX * speed;
        yMove = directionY * speed;
        distanceWent += Math.abs(xMove + yMove);
    }
    
    //If bullet proceeds 40, its gone.
    public void checkGone(){
        if(distanceWent > bulletRange || bulletReached){
            this.bulletGone = true;
        }
    }
    
    @Override
    public void move(){
        moveX();
        moveY();
    }

    @Override
    public void die() {

    }

    public boolean isBulletGone() {
        return bulletGone;
    }   
    
    
}