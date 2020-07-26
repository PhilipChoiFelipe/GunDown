/*
abstract Class
Every State is going to have tick and render method.
 */
package minsgame.states;

import java.awt.Graphics;
import minsgame.Game;
import minsgame.Handler;

/**
 *
 * @author 게임용 PC1
 */
public abstract class State {
    
    private static State currentState = null;
    
    public static void setState(State state){
       currentState = state;
    }
    
    public static State getState(){
        return currentState;
    }
    
    //Class
    protected Handler handler;
    public State(Handler handler){
        this.handler = handler;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
    
}