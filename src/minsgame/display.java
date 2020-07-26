/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame;

import java.awt.*;
import javax.swing.*;


/**
 *
 * @author 게임용 PC1
 */
public class display {
    
    private JFrame frame;
    private String TITLE;
    private int WIDTH, HEIGHT;
    private Canvas canvas;
    
    public display(String TITLE, int WIDTH, int HEIGHT){
        this.TITLE = TITLE;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        createDisplay();
    }
    
    private void createDisplay(){
        frame = new JFrame(TITLE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        canvas = new Canvas();
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(false);
        frame.add(canvas);
        frame.pack();
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
    
    public JFrame getFrame(){
        return frame;
    }
}