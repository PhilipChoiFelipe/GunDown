/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import minsgame.gfx.Assets;
import minsgame.gfx.GameCamera;
import minsgame.gfx.ImageLoader;
import minsgame.gfx.SpriteSheet;
import minsgame.input.KeyManager;
import minsgame.input.MouseManager;
import minsgame.states.GameState;
import minsgame.states.MenuState;
import minsgame.states.State;

/**
 *
 * @author 게임용 PC1
 */
//ALL THE BASE OF THE GAME
public class Game implements Runnable{
    
    private display display;
    private int width, height;
    private Thread thread;
    private boolean running = false;
    public String title;
    
    private BufferStrategy bs;
    private Graphics g;
    
    //State
    public State gameState;
    public State menuState;
    
    
    //Input Manager
    private KeyManager keyManager;
    private MouseManager mouseManager;

//    private ImageLoader imageLoader = new ImageLoader();
    
    //Camera 
    private GameCamera gameCamera;
    
    //Handler
    private Handler handler;
    
    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }
    
    /*call initial display setting from display class. 
    Also sets current state of program. 
    */
    private void init(){
        display = new display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        
        //Which ever one is focused(frame, canvas), mouse manager will be fired. 
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
        
        //set GameCamera's offset as 0! 
         handler = new Handler(this);
        gameCamera = new GameCamera(this.handler, 0, 0);
        //because GameState extends State Class. 
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(menuState);
        //testImage = ImageLoader.loadImage("/textures/SpriteSheet.png");
    }
    
    //update game
    /*
    ticks KeyManager to update the reaction of keyPress from user 
    Also, ticks the currentState of program. 
    */
    private void tick(){
        keyManager.tick();
        if(State.getState() != null){
            State.getState().tick();
        }
    }
    
    //render pics
    private void render(){
        //bufferStrategy = tells how computer to draw something on screen. uses Buffer
        //Buffer = hidden Computer Screen. 
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        //Graphics = brush!!
        g = bs.getDrawGraphics();
        //Draw Here
        g.clearRect(0, 0, width, height);

        //End Draw
        if(State.getState() != null){
            State.getState().render(g);
        }
        bs.show();
        g.dispose();
    }
    
    @Override
    public void run() {
        init();
        //how many times tick and render to be called in second. so 60 times in second. 
        
        int fps = 60;
        
        //max time amount of time allowed to have 60 tick and render to be called.
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long timer = 0;
        int ticks = 0;
                
        //returns amount of time at nano seconds.
        long lastTime = System.nanoTime();
        while(running){
            now = System.nanoTime();
            timer += now - lastTime;
//            lastTime = now;
            //amount of time passed / maximum amount of time to have to call tick and render method. 
            //when and when not to call tick and render method == tested by delta's value. 
            delta += (now - lastTime) / timePerTick;
            //its 60ticks per second so we diviede 1sec / 60 to get time for per tick. 
            lastTime = now;
            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000){
//                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        
        stop();
       
    }
    
    //Whenever working with thread directly
    public synchronized void start(){
        //run GAME class
       
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        if(!running){
            return;
        }
        running = false;
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }
    
    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }
    

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
}
