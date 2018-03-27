
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    static int [][] bg = new int [20][20];
    
    static int padding = 10;
    static int WIDTH = 400;
    static int HEIGHT = 400;
    
    static boolean isPlaying = false;
    
    static boolean enableTextStart = true;
    
    Snake snake;

    Thread thread;
    
    static int currentLevel = 1;
    static int currentPoint = 0;
    
    static boolean isGameOver = false;
    
    public GameScreen() {
        snake = new Snake();
        Data.loadImage();
        Data.loadAllAnimation();
       
        bg[10][10] = 2;
        
        thread = new Thread(this);
        thread.start();
    }
    
    
    public void run() {
        long t = 0;
        long t3 = 0;
        while(true) {
            
            if (System.currentTimeMillis() - t3 > 300) {
                enableTextStart =! enableTextStart;
                t3 = System.currentTimeMillis();
            }
            
            if (isPlaying) {
                    if (System.currentTimeMillis() - t > 200) {
                        Data.food.update();
                        t = System.currentTimeMillis();
                    }            
                snake.update();
            }

            repaint();
            try {
                thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }
    
    //Vẽ nền lưới
    public void paintBg(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH + padding*2+300, HEIGHT + padding*2);
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                //g.fillRect(i*20+1, j*20+1, 18, 18);
                
                //Vẽ thức ăn
                if (bg[i][j]==2) {
//                    g.setColor(Color.red);
//                    g.fillRect(i*20+1, j*20+1, 18, 18);
//                    g.setColor(Color.gray);
                    
                    g.drawImage(Data.food.getCurrentImage(), i*20-7+padding, j*20-7+padding, null);
                }
            }
    }
    
    //Hiển thị rắn ra màn hình
    public void paint(Graphics g) {
        paintBg(g);
        snake.paintSnake(g);
        paintPadding(g);
        
        if (!isPlaying) {
            if (enableTextStart) {
                g.setFont(g.getFont().deriveFont(18.0f));
                g.drawString("PRESS SPACE TO PLAY GAME", 80, 300);
            }      
        }
        
        if (isGameOver) {
            g.setColor(Color.red);
            g.setFont(g.getFont().deriveFont(38.0f));
            g.drawString("GAME OVER", 90, 150);    
        }
        
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(28.0f));
        g.drawString("LEVEL "+currentLevel, 450, 50);
        
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(28.0f));
        g.drawString("Đã ăn "+currentPoint+" cục cứt", 450, 100);
        
        for (int i = 0; i < FrameScreen.users.size(); i++) {
            g.setFont(g.getFont().deriveFont(23.0f));
            g.drawString(FrameScreen.users.get(i).toString() + " cục cứt", 450, i*30+170);
        }
    }
    
    private void paintPadding(Graphics g) {
        g.setColor(Color.gray);
        
        g.drawRect(0, 0, WIDTH+padding*2, HEIGHT+padding*2);
        g.drawRect(1, 1, WIDTH+padding*2-2, HEIGHT+padding*2-2);
        g.drawRect(2, 2, WIDTH+padding*2-4, HEIGHT+padding*2-4);
        
        g.drawRect(0, 0, WIDTH+padding*2+300, HEIGHT+padding*2);
        g.drawRect(1, 1, WIDTH+padding*2-2+300, HEIGHT+padding*2-2);
        g.drawRect(2, 2, WIDTH+padding*2-4+300, HEIGHT+padding*2-4);
    }
    
}
