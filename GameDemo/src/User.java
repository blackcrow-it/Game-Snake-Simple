/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Black Crow
 */
public class User {
    
    private String name;
    private String point;
    
    public User(String name, String point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return point;
    }

    public void setLevel(String point) {
        this.point = point;
    }
    
    public String toString(){
        return name + " " + point;
    }
}
