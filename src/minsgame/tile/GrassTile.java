/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.tile;

import minsgame.gfx.Assets;

/**
 * Tile 의 lower class. 부를때 ID 만 받는다면, super class 로 전달. 
 */
public class GrassTile extends Tile{
    public GrassTile(int id){
        //is this walkalbe??
        super(Assets.grass, id);
    }
}
