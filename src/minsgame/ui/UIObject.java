/*
UIObject! what will player need for the smooth gameplay. 
 */
package minsgame.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
Base Entity Class. 
* Core components we need in game. EX: button, etc. 
* Making this abstract because UI Object can be really broad. 
 */

//Gets x, y, width, height of ui object on the screen. 
public abstract class UIObject {
    protected float x, y;
    protected int width, height;
    public Rectangle bound;
    protected boolean hovering = false;
    public UIObject(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //Needs bound to recognize the mouseMovement on it. 
        bound = new Rectangle((int) x, (int) y, width, height);
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    //Every UIObject will be activated when it is "clicked"
    public abstract void onClick();
    
    //Check if users mouse in hovering the UI object. 
    public void onMouseMove(MouseEvent e){
        if(bound.contains(e.getX(), e.getY())){
            hovering = true;
        }else{
            hovering = false;
        }
    }
    
    public void onMouseRelease(MouseEvent e){
        if(hovering){
            //Implemented by whatever UIObject implemeted this abstract class. 
            onClick();
        }
    }
    
    //Getters and Setters 
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }
    
}
