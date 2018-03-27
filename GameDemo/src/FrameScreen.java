
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public static ArrayList<User> users;
    
    //Khung màn hình cửa sổ JAVA
    public FrameScreen() {
        setSize(750,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        
        users = new ArrayList<User>();
        readData();
        
        gameScreen = new GameScreen();
        add(gameScreen);

        this.addKeyListener(new handler());
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                updateData();
            }
            
        });
        
        setVisible(true);

    }
    
    //Khung màn hình của game
    public static void main(String[] args) {
        FrameScreen f = new FrameScreen();
    }
    
    //Thêm thư viện sử dụng bàn phím
    private class handler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                GameScreen.isPlaying =! GameScreen.isPlaying;
                if (GameScreen.isGameOver) {
                    GameScreen.isGameOver = false;
                    gameScreen.snake.resetGame();
                }
            }
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
    
    public static void updateData() {
        BufferedWriter bw = null;
        
        try {
            FileWriter fw = new FileWriter("data/data.txt");
            bw = new BufferedWriter(fw);
            
            for(User u: users){
                bw.write(u.getName() + " " + u.getLevel());
                bw.newLine();
            }
            
        } catch (IOException ex) {}
        
        finally{
            try {
                bw.close();
            } catch (IOException ex) {}
        }
        
    }
    
    public static void readData() {
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader("data/data.txt");
            br = new BufferedReader(fr);
            
            String line = null;
            while ((line = br.readLine()) != null) {                
                String[] str = line.split(" ");
                users.add(new User(str[0], str[1]));
            }
            
            br.close();
        } catch (IOException ex) {}
        finally{
            try {
                br.close();
            } catch (IOException ex) {}
        }
    }
        
}
