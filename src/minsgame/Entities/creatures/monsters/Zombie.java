/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.Entities.creatures.monsters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import minsgame.Entities.EntityManager;
import minsgame.Entities.creatures.bullet.Bullet;
import minsgame.Entities.creatures.bullet.MonsterBullet;
import minsgame.Handler;
import minsgame.gfx.Animation;
import minsgame.gfx.Assets;
import minsgame.items.Item;
import static minsgame.items.Item.heartItem;
import minsgame.tile.Tile;

/**
Monster Zombie class
 */
public class Zombie extends Monster{
    public final static int DEFAULT_ZOMBIE_HEALTH = 3, DEFAULT_ZOMBIE_SPEED = 2;
    public final static int DEFAULT_ZOMBIE_WIDTH = 80, DEFAULT_ZOMBIE_HEIGHT = 80;
    private Animation slime_hurt;
    public Zombie(Handler handler, EntityManager entityManager, float x, float y) {
        super(handler, new Rectangle(10, 20, 50, 35), entityManager, x, y, DEFAULT_ZOMBIE_WIDTH, DEFAULT_ZOMBIE_HEIGHT);
        speed = DEFAULT_ZOMBIE_SPEED;
        health = DEFAULT_ZOMBIE_HEALTH;
        slime_hurt = new Animation(500, Assets.slime_hurt);
        
    }

    @Override
    //When ever slime dies, heart item will be dropped in the same position where monster died. 
    public void die() {
        handler.getWorldManager().getCurrentWorld().getItemManager().addItem(Item.heartItem.createNew((int) x, (int) y));
    }

    @Override
    public void tick() {
        //Animation
        
        //Monster Movement
        sensePlayer();
        activateTracing();
        autoMove();
        reactHurt(500);
        shootBullet();
        bulletManager.tick();
        move();
    }
    
    //It shoots bullet to player after cool time is over. Creates bullet class for zombie and shoots toward player. 
        public void shootBullet(){
        if(attack){
            attackTimer += System.currentTimeMillis() - lastAttackTimer;
            lastAttackTimer = System.currentTimeMillis();
        //if time attackCooldown is larger than time flowed from last attack. 
            if(attackTimer < attackCooldown){
                return;
            }
            float xAway = px - mx;
            float yAway = py - my;
            float norm =  (float) Math.sqrt(xAway * xAway + yAway * yAway);
            bulletX = xAway * (speed / norm);
            bulletY = yAway * (speed / norm);
                attackTimer = 0;
                Bullet bullet = new MonsterBullet(handler, x + width / 2, y + height / 2, (bulletX),  (bulletY));
                stop();
                bulletManager.addBullet(bullet);
        }
    }
    
    public BufferedImage getCurrentFrame(){
        if(Hurt){
              return Assets.slime_hurt[0];
        }else{
            return Assets.slime[0];
        }
    }
    
    @Override
    public void render(Graphics g) {
        bulletManager.render(g);
        g.drawImage(getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffSet()), 
                (int) (y - handler.getGameCamera().getyOffSet()), width, height, null);
        
        //Health Bar
        g.setColor(Color.black);
        g.clearRect((int) (x - handler.getGameCamera().getxOffSet()), 
                (int) (y - handler.getGameCamera().getyOffSet()), width, 5);
        g.setColor(Color.red);
        g.fillRect((int) (x - handler.getGameCamera().getxOffSet()), 
                (int) (y - handler.getGameCamera().getyOffSet()), health * (width / DEFAULT_ZOMBIE_HEALTH), 5);
        
//        g.fillRect((int) (x + bound.x - handler.getGameCamera().getxOffSet()), (int) (y + bound.y - handler.getGameCamera().getyOffSet()), bound.width, bound.height);
    }
}
