
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Black Crow
 */
public class Data {
    public static BufferedImage sprite;
    
    public static Image imageHead;
    public static Image imageHeadGoLeft;
    public static Image imageHeadGoRight;
    public static Image imageHeadGoUp;
    public static Image imageHeadGoDown;
    
    public static Image imageFoodSmall;
    public static Image imageFood;
    public static Image imageFoodBig;  
    
    public static Image imageBody;

    
    public static Animation headGoUp;
    public static Animation headGoDown;
    public static Animation headGoLeft;
    public static Animation headGoRight;
    
    public static Animation food;
    
    public static void loadImage() {
        try {
            sprite = ImageIO.read(new File("res/sprite1.png"));
            
            imageHead = sprite.getSubimage(2, 3, 30, 30);
            imageHeadGoLeft = sprite.getSubimage(75, 3, 30, 30);
            imageHeadGoRight = sprite.getSubimage(110, 3, 30, 30);
            imageHeadGoUp = sprite.getSubimage(145, 3, 30, 30);
            imageHeadGoDown = sprite.getSubimage(39, 3, 30, 30);
            
            imageBody = sprite.getSubimage(6, 79, 20, 20);
//            imageBody = ImageIO.read(new File("res/shit.png"));
            
//            imageFoodSmall = sprite.getSubimage(63, 40, 30, 30);
//            imageFood = sprite.getSubimage(32, 40, 30, 30);
//            imageFoodBig = sprite.getSubimage(2, 40, 30, 30);
            imageFoodSmall = ImageIO.read(new File("res/shit1.png"));
            imageFood = ImageIO.read(new File("res/shit2.png"));
            imageFoodBig = ImageIO.read(new File("res/shit3.png"));
            
        } catch(Exception e){}
    }
    
    public static void loadAllAnimation() {
        headGoUp = new Animation();
        headGoUp.addImage(imageHead);
        headGoUp.addImage(imageHeadGoUp);
        
        headGoDown = new Animation();
        headGoDown.addImage(imageHead);
        headGoDown.addImage(imageHeadGoDown);
        
        headGoLeft = new Animation();
        headGoLeft.addImage(imageHead);
        headGoLeft.addImage(imageHeadGoLeft);
        
        headGoRight = new Animation();
        headGoRight.addImage(imageHead);
        headGoRight.addImage(imageHeadGoRight);
        
        food = new Animation();
        food.addImage(imageFoodSmall);
        food.addImage(imageFood);
        food.addImage(imageFoodBig);
        food.addImage(imageFood);
    }
}
