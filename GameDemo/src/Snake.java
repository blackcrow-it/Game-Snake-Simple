
import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Black Crow
 */
public class Snake {
    int snakeWidth = 1;
    int []x;
    int []y;
    long t1 = 0;
    
    public Snake() {
        x = new int[20];
        y = new int[20];
        
        x[0] = 5;
        y[0] = 4;
    }
    
    public void update() {
        if (System.currentTimeMillis()-t1>1000) {
            x[0]++;  
            t1 = System.currentTimeMillis();
        }

    }
    
    public void paintSnake(Graphics g) {
        g.setColor(Color.red);
        for (int i = 0; i < snakeWidth; i++)
            g.fillRect(x[i]*20+1, y[i]*20+1, 18, 18);
    }
}
