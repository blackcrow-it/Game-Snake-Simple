
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
        setVisible(true);
    }
    public static void main(String[] args) {
        FrameScreen f = new FrameScreen();
//        f.setVisible(true);
//        f.setSize(500,500);
    }
}
