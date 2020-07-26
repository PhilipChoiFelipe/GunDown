/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.Entities.creatures.monsters;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import minsgame.Entities.Entity;
import minsgame.Entities.EntityManager;
import minsgame.Handler;
import minsgame.gfx.Animation;
import minsgame.gfx.Assets;
import minsgame.tile.Tile;

/**
 *
 * @author 게임용 PC1
 */
public class Runner extends Monster{
    public static int DEFAULT_RUNNER_HEALTH = 5;
    public static int DEFAULT_RUNNER_SPEED = 2, DEFAULT_RUNNER_ANGRYSPEED = 6;
    public static int DEFAULT_RUNNER_WIDTH = 130;
    public static float DEFAULT_RUNNER_SENSERANGE = 200f, DEFAULT_RUNNER_MINIMUMRANGE = 150f;
//    private boolean run = false;
    private Animation runnerNormal, runnerAngry;
    public Runner(Handler handler, EntityManager entityManager, float x, float y) {
        super(handler, new Rectangle(40, 30, 50 ,50), entityManager, x, y, DEFAULT_RUNNER_WIDTH, DEFAULT_CREATURE_HEIGHT);
        speed = DEFAULT_RUNNER_SPEED;
        senseRange = DEFAULT_RUNNER_SENSERANGE;
        minimumRange = DEFAULT_RUNNER_MINIMUMRANGE;
        //Animation
        runnerNormal = new Animation(300, Assets.runner_normal);
        runnerAngry = new Animation(300, Assets.runner_angry);
    }
        
    
    @Override
    public void die() {
        
    }
    
    public void runToPlayer(){
    if(attack){
            attackTimer += System.currentTimeMillis() - lastAttackTimer;
            lastAttackTimer = System.currentTimeMillis();
        //if time attackCooldown is larger than time flowed from last attack. 
            if(attackTimer < attackCooldown){
                return;
            }
            angry = true;
            speed = DEFAULT_RUNNER_ANGRYSPEED;
            if(getCollisionBounds(xMove, yMove).intersects(handler.getWorldManager().getPlayer().getCollisionBounds(0, 0))){
                System.out.println("HIT!!");
                handler.getWorldManager().getPlayer().hurt(1);
                attackTimer = 0;
            }
            
        
        }else{
            angry = false;
//            run = false;
            speed = DEFAULT_RUNNER_SPEED;
        }

    }
    
    public BufferedImage getCurrentAnimationFrame(){
        if(!angry){
            return runnerNormal.getCurrentFrame();
        }else{
            return runnerAngry.getCurrentFrame();
        }
    }
    
    @Override
    public void tick() {
        //Animation
        runnerNormal.tick();
        runnerAngry.tick();
        
        sensePlayer();
        activateTracing();
        autoMove();
        runToPlayer();
        reactHurt(500);
        move();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffSet()), 
                (int)(y - handler.getGameCamera().getyOffSet()), width, height, null);
        
//        g.fillRect((int)(x + bound.x - handler.getGameCamera().getxOffSet()), 
//                (int)(y + bound.y - handler.getGameCamera().getyOffSet()), bound.width, bound.height);

    }
    
}
