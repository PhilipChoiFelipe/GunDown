/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.Entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import minsgame.Entities.creatures.bullet.Bullet;
import minsgame.Entities.creatures.bullet.BulletManager;
import minsgame.Entities.Entity;
import minsgame.Entities.creatures.bullet.PlayerBullet;
import minsgame.Game;
import minsgame.Handler;
import minsgame.gfx.Animation;
import minsgame.gfx.Assets;
import minsgame.inventory.Inventory;

/**
엔티티 클래스에서 상속되는 플레이어 클래스.
 */
public class Player extends Creature{
    //Animation for Player
    private Animation aniDown, aniUp, aniLeft, aniRight, batDown, batUp, batLeft, batRight;
    
    //AttackTimer
    private long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;
    
    //Inventory
    private Inventory inventory;
    
    //Bullet
    private BulletManager bulletManager;
    
    public Player(Handler handler, float x, float y) {
        super(handler, new Rectangle(35, 50, 50, 50), x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        //Animation
        //Moving
        aniDown = new Animation(400, Assets.player_down);
        aniUp = new Animation(400, Assets.player_up);
        aniLeft = new Animation(400, Assets.player_left);
        aniRight = new Animation(400, Assets.player_right);
        
        //Batting
        batDown = new Animation(100, Assets.batting_down);
        batUp = new Animation(100, Assets.batting_up);
        batLeft = new Animation(100, Assets.batting_left);
        batRight = new Animation(100, Assets.batting_right);
        
        //Inventory
        inventory = new Inventory(handler);
        
        //BulletManager
        bulletManager = new BulletManager();
        
    }


    //Player 클래스 로직 실행. 
    @Override
    public void tick() {
        //Animaions
        
        //Move
        aniDown.tick();
        aniUp.tick();
        aniLeft.tick();
        aniRight.tick();
        
        //Attack
        batDown.tick();
        batUp.tick();
        batLeft.tick();
        batRight.tick();
        
        //Movement
        getInput();
        move();
        //passing this Entity Class to manage the offset of tile rendering.
        //This will UPDATE the offSets of GameCamera class. 
        handler.getGameCamera().centerOnEntity(this);
        
        //Attack
//        checkAttacks();
        shootBullet();
        bulletManager.tick();
        //inventory
        inventory.tick();
    }
    
    public void shootBullet(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        //if time attackCooldown is larger than time flowed from last attack. 
        if(attackTimer < attackCooldown){
            return;
        }
        if(handler.getKeyManager().aUp || handler.getKeyManager().aDown || handler.getKeyManager().aLeft || handler.getKeyManager().aRight){
            attackTimer = 0;
            Bullet bullet = new PlayerBullet(handler, x + getWidth()/2, y + getHeight()/2);
//            System.out.println(bullet.isBulletGone());
            bulletManager.addBullet(bullet);
        }
    }
    
//    public void checkAttacks(){
//        attackTimer += System.currentTimeMillis() - lastAttackTimer;
//        lastAttackTimer = System.currentTimeMillis();
//        //if time attackCooldown is larger than time flowed from last attack. 
//        if(attackTimer < attackCooldown){
//            return;
//        }
//        //Collision boundary of player's entity.
//        Rectangle cb = getCollisionBounds(0, 0);
//        //Attack Range
//        this.ar = new Rectangle();
//        int arSize = 40;
//        ar.width = arSize;
//        ar.height = arSize;
//        
//        //To get the proper attack Range in every side of player
//        //This makes sense because it will exactly get the boundary of player's x and y position + ar.width,height. 
//        if(handler.getKeyManager().aUp){
//            ar.x = cb.x + cb.width / 2 - arSize / 2;
//            ar.y = cb.y - arSize;
//        }else if(handler.getKeyManager().aDown){
//            ar.x = cb.x + cb.width / 2 - arSize / 2;
//            ar.y = cb.y + cb.height;
//        }else if(handler.getKeyManager().aLeft){
//            ar.x = cb.x - arSize;
//            ar.y = cb.y + cb.height / 2 - arSize / 2;
//        }else if(handler.getKeyManager().aRight){
//            ar.x = cb.x + cb.width;
//            ar.y = cb.y + cb.height / 2 - arSize / 2;
//        }else{
//            return;
//        }
//        attackTimer = 0;
//        for(Entity e : handler.getWorldManager().getCurrentWorld().getEntityManager().getEntities()){
//            if(e.equals(this)){
//                continue;
//            }
//            if(e.getCollisionBounds(0, 0).intersects(ar)){
//                e.hurt(1);
//                return;
//            }
//        }
//    }
    
    //KeyManager 클래스에서 받아, 키에 반응되는 field 를 test 해 speed 조정. 
    private void getInput(){
        xMove = 0;
        yMove = 0;
        
        if(handler.getKeyManager().up){
            yMove = -speed;
        }if(handler.getKeyManager().down){
            yMove = speed;
        }if(handler.getKeyManager().left){
            xMove = -speed;
        }if(handler.getKeyManager().right){
            xMove = speed;
        }
    }


    //Player 클래스를 이미지로 렌더링. 
    @Override
    public void render(Graphics g) {
        //width and height is from Creature class(super class), Entity.
        //You subtract xOffSet from x to make sure it draws image in the center of the screen. 
                //As player's position moves, you need to subtract the offset(how far from original position) to make it in center. 
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffSet()), 
                (int)(y - handler.getGameCamera().getyOffSet()), width, height, null);
        //Test Boundary of player
//        g.fillRect((int)(x + bound.x - handler.getGameCamera().getxOffSet()), (int)(y + bound.y - handler.getGameCamera().getyOffSet()), bound.width, bound.height);
        //Boundary of attack. 
//        g.fillRect((int)(ar.x - handler.getGameCamera().getxOffSet()),(int) (ar.y - handler.getGameCamera().getyOffSet()), ar.width, ar.height);
        inventory.render(g);
        bulletManager.render(g);
        g.setColor(Color.black);
        g.clearRect(10, 
                10, width * 5, 25);
        g.setColor(Color.red);
        g.fillRect(10, 10, health * (width * 5 / DEFAULT_HEALTH), 25);
    }
    
    private BufferedImage getCurrentAnimationFrame(){
        //Batting
        if(handler.getKeyManager().aDown){
            return batDown.getCurrentFrame();
        }else if(handler.getKeyManager().aUp){
            return batUp.getCurrentFrame();
        }else if(handler.getKeyManager().aLeft){
            return batLeft.getCurrentFrame();
        }else if(handler.getKeyManager().aRight){
            return batRight.getCurrentFrame();
        }
        if(xMove < 0){
            return aniLeft.getCurrentFrame();
        }else if(xMove > 0){
            return aniRight.getCurrentFrame();
        }else if(yMove < 0){
            return aniUp.getCurrentFrame();
        }else if(yMove >  0){
            return aniDown.getCurrentFrame();
        }
        return Assets.player_standing;
    }

    @Override
    public void die() {
        System.out.println("you die");
    }
    
    //getters and setters

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
}
