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
public class MonsterBullet extends Bullet{
    
    public MonsterBullet(Handler handler, float x, float y, float directionX, float directionY) {
        super(handler, x, y);
        super.directionX = directionX;
        super.directionY = directionY;
        speed = 2.5f;
        bulletRange = 400f;
    }
        //Check every Entities in entityManager to check if bullet intersected with bullet's bound.
    
    @Override
    public void checkAttack(){
//        for(Entity e : handler.getWorldManager().getCurrentWorld().getEntityManager().getEntities()){
//            if(e.equals(handler.getWorldManager().getPlayer())){
//                if((getCollisionBounds(0, 0)).intersects(e.getCollisionBounds(0, 0))){
//                    e.hurt(1);
//                    bulletGone = true;
//                    return;
//                }
//            }else{
//                continue;
//            }
//        }
        if((getCollisionBounds(0, 0)).intersects(handler.getWorldManager().getPlayer().getCollisionBounds(0, 0))){
            System.out.println("HIT!!");
            handler.getWorldManager().getPlayer().hurt(1);
            bulletGone = true;
//            return;
        }
    }
    
    @Override
    public void tick(){
//        System.out.println(xMove + " " + yMove);
        proceedBullet();
        checkAttack();
        move();
        checkGone();
    }
    
    @Override
    public void render(Graphics g){
        g.setColor(Color.red);
//        System.out.println("x: " + x + " y: " + y);
        g.fillOval((int)(x - handler.getGameCamera().getxOffSet()), (int)(y - handler.getGameCamera().getyOffSet()), 
                DEFAULT_BULLETWIDTH, DEAFULT_BULLETHEIGHT);
//        g.fillRect((int)(x), (int)(y), 
//                DEFAULT_BULLETWIDTH, DEAFULT_BULLETHEIGHT);
//        g.setColor(Color.blue);
//        g.fillRect((int)(x + bound.x - handler.getGameCamera().getxOffSet()), (int)(y + bound.y - handler.getGameCamera().getyOffSet()), 
//                DEFAULT_BULLETWIDTH, DEAFULT_BULLETHEIGHT);
    }
}
