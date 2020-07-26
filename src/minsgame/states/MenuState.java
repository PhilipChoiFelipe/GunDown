/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.states;

import java.awt.Graphics;
import minsgame.Handler;
import minsgame.gfx.Assets;
import minsgame.ui.ClickListener;
import minsgame.ui.UIImageButton;
import minsgame.ui.UIManager;

/**
 *
 * @author 게임용 PC1
 */
public class MenuState extends State{
    private UIManager uiManager;
    
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.addObject(new UIImageButton(300, 250, 200, 100, Assets.btn_start, new ClickListener(){
            @Override
            public void onClick() {
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {
//        System.out.println(handler.getMouseManager().getMouseX() + "   " + handler.getMouseManager().getMouseY());
//        if(handler.getMouseManager().isLeftPressed()){
//            State.setState(handler.getGame().gameState);
//            System.out.println("pressed");
//        }
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
//        g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
        uiManager.render(g);
    }

}
