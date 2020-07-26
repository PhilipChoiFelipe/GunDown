/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.gfx;

import minsgame.Entities.Entity;
import minsgame.Game;
import minsgame.Handler;
import minsgame.tile.Tile;

/**
OffSet + Amt는 플레이어 카메라가 움직일때 얼마나 멀리 다시 그려야하는지 나타냄. 
* 10 pixel 을 오른쪽으로 움직였다면, 타일들을 전부 -10pixel 로 그려야한다.   
 */
public class GameCamera {
    private float xOffSet, yOffSet;
    private Handler handler;
    //offset = if xoffset = 10, every tiles will be -10 Xposition. 
    //numbers that represents how far it should draw from original position. 
    public GameCamera(Handler handler, float xOffSet, float yOffSet){
        this.handler = handler;
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;
    
    }
    
    public void checkBlankSpace(){
        if(xOffSet < 0){
            xOffSet = 0;
            //if Entity Position is larger than World's Width - window width.. 
        }else if(xOffSet > handler.getWorldManager().getCurrentWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()){
            xOffSet = handler.getWorldManager().getCurrentWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
        }
        if(yOffSet < 0){
            yOffSet = 0;
        }else if(yOffSet > handler.getWorldManager().getCurrentWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()){
            yOffSet =  handler.getWorldManager().getCurrentWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
        }
    }
    
    
    //to make sure the tiles get render based on player's position. 
    /* 
    get entity's position, subtract it with game width. That is how GameCamera gets OffSet, most lowest X, Y value you can get from 
    player's position to render tiles efficiently. 
    */
    public void centerOnEntity(Entity e){
        xOffSet = e.getX() - handler.getWidth() / 2 + e.getWidth()/2;
        yOffSet = e.getY() - handler.getWidth() / 2 + e.getHeight()/2 + 200;
        checkBlankSpace();
    }
    
    
    //adding to offset 그래가지고 타일들을 Amt 값만큼 옮겨서 rendering 한다. 
    public void move(float xAmt, float yAmt){
        xOffSet += xAmt;
        yOffSet += yAmt;
        checkBlankSpace();
    }

    public void setxOffSet(float xOffSet) {
        this.xOffSet = xOffSet;
    }

    public void setyOffSet(float yOffSet) {
        this.yOffSet = yOffSet;
    }

    public float getxOffSet() {
        return xOffSet;
    }

    public float getyOffSet() {
        return yOffSet;
    }
}
