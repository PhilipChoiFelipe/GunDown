/*
this is like a 탄창 of bullets. This gathers all the added bullets called in player class. 
 */
package minsgame.Entities.creatures.bullet;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import minsgame.Entities.creatures.Player;
import minsgame.Handler;

/**
 *
 * @author 게임용 PC1
 */
public class BulletManager {
    private Handler handler;
    private ArrayList<Bullet> bullets;

    public BulletManager(){
        bullets = new ArrayList<Bullet>();
    }
    
    public void addBullet(Bullet b){
        bullets.add(b);
    }
    
    //If bullet's distance is over, it is removed from array.
    public void tick(){
        Iterator<Bullet> it = bullets.iterator();
        while(it.hasNext()){
            Bullet b = it.next();
            b.tick();
            if(b.isBulletGone()){
                it.remove();
//                System.out.println(bullets + "bulletGone 36");
            }
        }
    }
    
    public void render(Graphics g){
        for(Bullet b: bullets){
            b.render(g);
        }
    }
}
