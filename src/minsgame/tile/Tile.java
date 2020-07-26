/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author 게임용 PC1
 */
public class Tile {
    //Static Stuff HERE
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile Stone = new Stone(1);
    public static Tile Dirt = new Dirt(2);
    public static Tile Dirk = new Dark(3);
    public static Tile NightSky = new NightSky(4);
    public static Tile guardRoom = new guardRoomFloor(5);
    
    //Class
    
    //every single tile needs image
    protected BufferedImage texture;
    //every id needs to be unique to each tile. 
    protected final int id;
    public static final int TILEWIDTH = 100, TILEHEIGHT = 100;
    
    //get the id and type of texture from lower class. 
    //그 ID 로 타일의 Array 순서로 삽입. 
    //Tile 하나는, BufferedImage로 불러온 타일 이미지와, 그 Tile을 Identity 해주는 ID 넘버가있다. 
    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
        
        tiles[id] = this;
    }
    
    public void tick(){
    
    }
    
    //캐릭터가 이 타일을 지나갈수있나 없나. 
    public boolean isSolid(){
        return false;
    }
    
    //Texture 는 lowerclass 에서 받은 타일의 type. (IMAGE) 
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }
    
    public int getId(){
        return id;
    }
}
