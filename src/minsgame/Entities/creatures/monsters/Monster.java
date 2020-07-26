/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.Entities.creatures.monsters;
import java.awt.Rectangle;
import minsgame.Entities.creatures.bullet.BulletManager;
import minsgame.Entities.EntityManager;
import minsgame.Entities.creatures.Creature;
import minsgame.Handler;

/**
parent class of all types of monster entity classes. 
 */
public abstract class Monster extends Creature{
    //MoveRange = monster's auto move range, SENSERANGE = monster's range to sense player, MUNIMUMRANGE = minimum range for monster to follow player.
    public static final float DEFAULT_MOVERANGE = 200f, DEFAULT_SENSERANGE = 400f, DEFAULT_MINIMUMRANGE = 300f; 
    //Player x,y / Monster x,y
    protected float px, py, my, mx;
    protected EntityManager entityManager;
    
    //Decides wether to follow, attack or move arount automatically
    protected boolean trace, attack, autoMove, angry;
    
    //Sets range for monster's activity 
    protected float autoMoveRange, senseRange, minimumRange;
    
    
    private long lastTime, timer;
    
    //AttackTimer
    protected long lastAttackTimer, attackCooldown = 1500, attackTimer = attackCooldown;
    //Bullet
    protected BulletManager bulletManager;
    
    protected float bulletX, bulletY;
    
    public Monster(Handler handler, Rectangle bound, EntityManager entityManager, float x, float y, int width, int height) {
        super(handler, bound, x, y, width, height);
        this.autoMoveRange = DEFAULT_MOVERANGE;
        this.senseRange = DEFAULT_SENSERANGE;
        this.minimumRange = DEFAULT_MINIMUMRANGE;
        this.entityManager = entityManager;
        lastTime = System.currentTimeMillis();
        bulletManager = new BulletManager();
    }
    
    public void sensePlayer(){
        px = handler.getWorldManager().getPlayer().getX();
        py = handler.getWorldManager().getPlayer().getY();
        mx = x;
        my = y;
        //To get current Distance from monster to player.
        float currentDistance = (float) Math.sqrt(Math.pow((mx - px), 2) + Math.pow((my - py), 2));
        if(currentDistance <= minimumRange){
            trace = false;
            attack = true;
            autoMove = false;
        }else if(currentDistance<= senseRange || angry){
            trace = true;
            autoMove = false;
        }else if(currentDistance >= senseRange){
            trace = false;
            attack = false;
            autoMove = true;
        }
    }
    
    //If trace is true, calculate ratio of one move per tick toward player, and set that value as xMove, yMove.
    public void activateTracing(){
        if(trace){
            float xAway = px - mx;
            float yAway = py - my;
            float norm =  (float) Math.sqrt(xAway * xAway + yAway * yAway);
            xMove =  (xAway * (speed / norm));
            yMove =  (yAway * (speed / norm));
        }
    }
    
    //Sets monster's move 0
    public void stop(){
        xMove = 0;
        yMove = 0;
    }

    //Range of automove of monster.
    public void autoMove(){
        if(autoMove){
            yMove = 0;
            autoMoveRange -= speed;
            if(autoMoveRange <= 0){
                autoMoveRange = DEFAULT_MOVERANGE;
            } else if(autoMoveRange > DEFAULT_MOVERANGE / 2){
                xMove = speed;
            }else if(autoMoveRange < DEFAULT_MOVERANGE / 2){
                xMove = -speed;
            }
        }
    }
    
    //If hurts, its move is stunned for parameter duration of time. 
    public void reactHurt(int duration){
        //If monster is hurt, its yMove is opposite to what it should be. 
        if(Hurt){
            angry = true;
            timer += System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            //every time we tick, monster going to backup opposite from player because it is always going toward player!
            yMove = 0;
            xMove = 0;
            //if duration of backup is done, then Hurt becomes false and resets the timer.
            if(timer > duration){
                Hurt = false;
                timer = 0;
            }
        }
    }
}
