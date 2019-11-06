/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejdbc;

/**
 *
 * @author pedago
 */
public class Discount_Code_Entity {
    
    // nom du discount
    private String name;
    
    // valeur du discount
    private int rate;
    
    
    
    
    
    public Discount_Code_Entity(String nameD, int rateD){
        this.name = nameD;
        this.rate = rateD;
    }
    
    
    
    public int getRate(){
        return this.rate;
    }
    
    
    public String getName(){
        return this.name;
    }
    
}
