/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.worlds;

import java.awt.Graphics;
import java.awt.Rectangle;
import minsgame.Entities.EntityManager;
import minsgame.Entities.creatures.Player;
import minsgame.Entities.creatures.monsters.Zombie;
import minsgame.Entities.statics.streetLamp;
import minsgame.Entities.statics.trashCan;
import minsgame.Handler;
import minsgame.items.ItemManager;
import minsgame.tile.Tile;
import minsgame.utils.Utils;
import static minsgame.worlds.WorldManager.worlds;

/**
Get the files that have information about the world (tiles) and load
 */
public abstract class World {
    protected Handler handler;
    private int width, height; 
    protected int spawnX, spawnY;
    private int[][] worldTiles;
    protected String path;
    //Entities
    protected EntityManager entityManager;
    //Item
    protected ItemManager itemManager;
    
    protected Player player;
    
    protected int id;
    
    protected Rectangle currentPortal;
    
    public World(Handler handler, String path, Player managerPlayer, int id){
        this.handler = handler;
        this.path = path;
        this.player = managerPlayer;
        this.id = id;
        worlds[id] = this;
        
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract int getPortal();
    
    //calls the function getTile to get the tile of specific x and y and render it!!
    //width and height represents the "NUMBER OF THE TILES!!"
    //EX: width = 5 => width has 5 tiles! 
    public void renderTile(Graphics g){
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffSet() / Tile.TILEWIDTH );//Divide tile width to get array num.
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffSet()+handler.getWidth())/Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffSet() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffSet()+handler.getHeight())/Tile.TILEWIDTH + 1);
        //For rendering efficiency.
        //We used Max to get only positive number
        //Min to get alway lower than width
        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                //GameCamera의 offset + Amt 해줌으로써 타일을 그값으로 렌더링한다. 
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffSet()), 
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffSet()));
            }
        }
    }
    
    //find the ID of the tile at x, y 
    //look up which id that tile has at that position. 
    public Tile getTile(int x, int y){
        //get the index of WorldTiles and find it in the tile set from Tile Class. 
        //WorldTile 은 ID넘버로 구성된 array이이다.
        //To protect from getting glitch if player is outside of the boundary. 
        if(x < 0 || y < 0 || x >= width || y >= height){
            return Tile.grassTile;
        }
        
        Tile t = Tile.tiles[worldTiles[x][y]];
        if(t == null){
            return Tile.Dirt;
        }
        return t;
    }
    
    
    //creates file that is a String builder appended with lines! 
    protected void loadWorld(String path){
        Utils utils = new Utils();
        String file = utils.loadFileAsString(path);
        
        //splits the space of lines and puts each of them into the token array 
        String[] tokens = file.split("\\s+");
        //number of tiles 
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        
        //where player should spawn
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        
        
        //take cares of the tiles part! 
        worldTiles = new int[width][height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                worldTiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    
    
    
}
