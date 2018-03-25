
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Black Crow
 */
public class FrameScreen extends JFrame{
    
    GameScreen gameScreen;
    
    public FrameScreen() {
        setSize(500,500);        
        gameScreen = new GameScreen();
        add(gameScreen);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new handler());
        
        setVisible(true);

    }
    
    public static void main(String[] args) {
        FrameScreen f = new FrameScreen();
    }
    
    private class handler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_UP){
                gameScreen.snake.setVector(Snake.GO_UP);
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN){
                gameScreen.snake.setVector(Snake.GO_DOWN);
            }
            if(e.getKeyCode()==KeyEvent.VK_LEFT){
                gameScreen.snake.setVector(Snake.GO_LEFT);
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                gameScreen.snake.setVector(Snake.GO_RIGHT);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
        
    }
}
