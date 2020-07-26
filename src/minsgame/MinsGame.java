/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame;

/**
 *
 * @author 게임용 PC1
 */
public class MinsGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic her
        Game game = new Game("title", 1200, 800);
        game.start();
        
    }
    
}
