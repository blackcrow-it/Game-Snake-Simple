
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.JOptionPane;

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
    int snakeWidth = 3;
    int []x;
    int []y;
    
    //Kí hiệu hướng di chuyển của rắn
    public static int GO_UP = 1;
    public static int GO_DOWN = -1;
    public static int GO_LEFT = 2;
    public static int GO_RIGHT = -2;
    
    //Lúc đầu rắn sẽ đi hướng bên phải
    int vector = Snake.GO_DOWN;
    
    long t1 = 0;
    long t2 = 0;
    
    int speed = 200;
    
    int upSpeed = 8;
    
    boolean afterChangeVt = true;
    
    //Tạo hình rắn
    public Snake() {
        x = new int[400];
        y = new int[400];
        
        x[0] = 5;
        y[0] = 4;
        
        x[1] = 5;
        y[1] = 3;
        
        x[2] = 5;
        y[2] = 2;
        
 
    }
    
    public void resetGame() {
        x = new int[400];
        y = new int[400];
        
        x[0] = 5;
        y[0] = 4;
        
        x[1] = 5;
        y[1] = 3;
        
        x[2] = 5;
        y[2] = 2;
        
        snakeWidth = 3;
        vector = Snake.GO_DOWN;

        speed = 200;
        
        upSpeed = 8;
    }
    
    //Xét rắn không được đi ngược chiều
    public void setVector(int v){
        if (vector != -v && afterChangeVt) {
            vector = v;
            afterChangeVt = false;
        }
    }
    
    //Xét mồi có nằm trong rắn hay không
    public boolean coordinatesFoodInSnake(int x1, int y1) {
        for (int i = 0; i < snakeWidth; i++)
            if (x[i] == x1 && y[i] == y1) return true;
        return false;   
    }
    
    //Tạo mồi xuất hiện ngẫu nhiên
    public Point getFoodCoordinates() {
        Random r = new Random();
        int x;
        int y;
        do {
            x = r.nextInt(19);
            y = r.nextInt(19);
        }
        while(coordinatesFoodInSnake(x, y));
        
        return new Point(x,y);
    }
    
    public int getCurrentSpeed() {
        int speed = 200;
        for (int i = 0; i < GameScreen.currentLevel; i++) {
            speed *= 0.8;
        }
        return speed;
    }
    
    
    //Cập nhật sự chuyển động của rắn
    public void update() {
        
        if (snakeWidth == upSpeed) {
//            GameScreen.isPlaying = false;
            //resetGame();
            GameScreen.currentLevel++;
            speed = getCurrentSpeed();
            upSpeed += 5;
        }
        
        for (int i = 1; i < snakeWidth; i++) {
            if (x[0] == x[i] && y[0] == y[i]) {
                
                String name = JOptionPane.showInputDialog("Enter your name: ");
                FrameScreen.users.add(new User(name,String.valueOf(GameScreen.currentPoint)));
                
                GameScreen.isPlaying = false;
                GameScreen.isGameOver =true;
                        
                GameScreen.currentLevel = 1;
                GameScreen.currentPoint = 0;
            }
        }
        
        if (System.currentTimeMillis()-t2 > (speed*0.9)) {
            
            afterChangeVt = true;
            
            Data.headGoUp.update();
            Data.headGoDown.update();
            Data.headGoLeft.update();
            Data.headGoRight.update();
            
            t2 = System.currentTimeMillis();
        }
        
        if (System.currentTimeMillis()-t1 > speed) {
            
            
            //Khi rắn ăn mồi sẽ dài thêm
            if (GameScreen.bg[x[0]][y[0]] == 2) {
                snakeWidth++;
                GameScreen.bg[x[0]][y[0]] = 0;
                GameScreen.bg[getFoodCoordinates().x][getFoodCoordinates().y] = 2;
                
                GameScreen.currentPoint += 1;
            }
            
            //Phần đuôi di chuyển theo đầu
            for (int i = snakeWidth-1; i > 0; i--) {
                x[i] = x[i-1];
                y[i] = y[i-1];
            }
            
            
            if (vector == Snake.GO_UP) y[0]--;
            if (vector == Snake.GO_DOWN) y[0]++;
            if (vector == Snake.GO_LEFT) x[0]--;
            if (vector == Snake.GO_RIGHT) x[0]++;
            
            //Khi chạy quá cạnh màn hình thì rắn sẽ xuất hiện cạnh đối diện
            if (x[0]<0) x[0] = 19;
            if (x[0]>19) x[0] = 0;
            if (y[0]<0) y[0] = 19;
            if (y[0]>19) y[0] = 0;
            
            t1 = System.currentTimeMillis();
        }

    }
    
    //Vẽ hình rắn
    public void paintSnake(Graphics g) {
        g.setColor(Color.red);
        for (int i = 1; i < snakeWidth; i++)
            //g.fillRect(x[i]*20+1, y[i]*20+1, 18, 18);
            g.drawImage(Data.imageBody, x[i]*20+GameScreen.padding, y[i]*20+GameScreen.padding, null);
        if (vector == Snake.GO_UP) {
            g.drawImage(Data.headGoUp.getCurrentImage(), x[0]*20-5+GameScreen.padding, y[0]*20-5+GameScreen.padding, null);
        }
        else if (vector == Snake.GO_DOWN) {
            g.drawImage(Data.headGoDown.getCurrentImage(), x[0]*20-5+GameScreen.padding, y[0]*20-5+GameScreen.padding, null);
        }
        else if (vector == Snake.GO_LEFT) {
            g.drawImage(Data.headGoLeft.getCurrentImage(), x[0]*20-5+GameScreen.padding, y[0]*20-5+GameScreen.padding, null);
        }
        else {
            g.drawImage(Data.headGoRight.getCurrentImage(), x[0]*20-5+GameScreen.padding, y[0]*20-5+GameScreen.padding, null);
        }
        
    }
}
