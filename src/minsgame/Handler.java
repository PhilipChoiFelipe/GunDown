/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame;

import minsgame.gfx.GameCamera;
import minsgame.input.KeyManager;
import minsgame.input.MouseManager;
import minsgame.worlds.World;
import minsgame.worlds.WorldManager;

/**
Control important variables
* replaces the role of Game class in other classes so it runs smoothly and clean. 
 */

public class Handler {
    private Game game;
//    private World world;
    private WorldManager worldManager;
    
    public Handler(Game game){
        this.game = game;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public void setWorldManager(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    
    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }
    
    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }
    
    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }
    
    public int getWidth(){
        return game.getWidth();
    }
    
    public int getHeight(){
        return game.getHeight();
    }
}