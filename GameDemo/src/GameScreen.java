
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Black Crow
 */
public class GameScreen extends JPanel implements Runnable{
    int [][] bg = new int [20][20];
    
    Snake snake;

    Thread thread;
    public GameScreen() {
        snake = new Snake();
        
        thread = new Thread(this);
        thread.start();
    }
    
    public void run() {
        while(true) {
            
            snake.update();
            repaint();
            try {
                thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }
    
    public void paintBg(Graphics g) {
        g.setColor(Color.gray);
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                g.fillRect(i*20+1, j*20+1, 18, 18);
            }
    }
    
    public void paint(Graphics g) {
        paintBg(g);
        snake.paintSnake(g);
    }
    
}
