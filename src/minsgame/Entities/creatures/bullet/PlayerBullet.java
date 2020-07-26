/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.Entities.creatures.bullet;

import java.awt.Color;
import java.awt.Graphics;
import minsgame.Entities.Entity;
import static minsgame.Entities.creatures.bullet.Bullet.DEAFULT_BULLETHEIGHT;
import static minsgame.Entities.creatures.bullet.Bullet.DEFAULT_BULLETWIDTH;
import minsgame.Handler;

/**
 *
 * @author 게임용 PC1
 */
public class PlayerBullet extends Bullet {
    private boolean shoot = true;
    public PlayerBullet(Handler handler, float x, float y) {
        super(handler, x, y);
        speed = 8;
    }
    
    //Check if player actually clicked Attack
    public void checkInput(){
        shoot = false;
        if(handler.getKeyManager().aUp){
            directionY = (int) -1;
        }if(handler.getKeyManager().aDown){
            directionY = (int) 1;

        }if(handler.getKeyManager().aLeft){
            directionX = (int) -1;
        }if(handler.getKeyManager().aRight){
            directionX = (int) 1;
        }else{
            //nothing happens down if no arraow keys clicked.
            return;
        }
    }
    
    //Checks if bullet actually hits entities.
    @Override
    public void checkAttack(){
        for(Entity e : handler.getWorldManager().getCurrentWorld().getEntityManager().getEntities()){
            if(e.equals(handler.getWorldManager().getPlayer())){
                continue;
            }
            if((getCollisionBounds(0, 0)).intersects(e.getCollisionBounds(0, 0))){
                e.hurt(1);
                bulletGone = true;
                return;
            }
        }
    }

    @Override
    public void tick(){
        if(shoot){
            checkInput();
        }
        proceedBullet();
        checkAttack();
        move();
        checkGone();
    }
    
    @Override
    public void render(Graphics g){
        g.setColor(Color.black);
//        System.out.println("x: " + x + " y: " + y);
        g.fillOval((int)(x - handler.getGameCamera().getxOffSet()), (int)(y - handler.getGameCamera().getyOffSet()), 
                DEFAULT_BULLETWIDTH, DEAFULT_BULLETHEIGHT);
    }
}
