/*
Entity = anything that is not a TILE
    -item
    -Enemy
    -Player

Class = Entity
SubClasses = Creature -> Player, Enemy / 
             Item -> Arrow

Entity needs POSITION(X,Y). 
             Tick, Render
Creature needs Health
Player needs Texture, Input(key)
 */
package minsgame.states;

import java.awt.Graphics;
import minsgame.Handler;
import minsgame.ui.UIManager;
import minsgame.worlds.WorldManager;

/**
이 프로그램의 상태이다. 이 상태는 유저가 게임을 플레이할때 실행되는 클래스. 
* Entity class 에서 원하는 클래스를 부르거나, 타일을 여기서 불러 틱, 렌더링할수있다. 
 */
public class GameState extends State{
//    private World world;
    private WorldManager worldManager;
    private UIManager uiManager;
    
    //player 의 처음 리스폰 좌표, 
    public GameState(Handler handler){
        super(handler);
//        world = new World(handler, "res/worlds/world1.txt");
        this.worldManager = new WorldManager(handler);
        handler.setWorldManager(worldManager);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
    }
    
    @Override
    public void tick() {
        worldManager.tick();
    }

    @Override
    public void render(Graphics g) {
        worldManager.render(g);
    }
    
}
