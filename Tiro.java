import greenfoot.*;

public class Tiro extends Actor {
   
    private int Hitbox=30;
    private boolean d;
    private boolean d1=true;
    public Tiro(boolean x){
     if (getImage() != null) {
            getImage().scale(45, 45);   
            d=x;
    }
}
    public void act() {
       
     Actor inimigoTocado = getOneIntersectingObject(inimigo.class);
        if (inimigoTocado != null) {
            
          Mundo mundo = (Mundo) getWorld();
           mundo.KillCout();
            getWorld().removeObject(inimigoTocado);
            
            getWorld().removeObject(this); 
            
            Greenfoot.playSound("death.wav");
            return;
            
        }
        Actor inimigoTocado2 = getOneIntersectingObject(InimigoAtirador.class);
        if (inimigoTocado2 != null) {
            
           Mundo2 mundo2 = (Mundo2) getWorld();
           
            getWorld().removeObject(inimigoTocado2);
            
            getWorld().removeObject(this); 
            
            Greenfoot.playSound("death.wav");
            return;
            
        }
        if(!d)
       {         
        move(-10);    
       }
     
    else{
    move(10);
        if(d1)
    {
     getImage().mirrorHorizontally();
     d1=false;
    }
     if (isAtEdge()) {
            getWorld().removeObject(this);
    }
 }
}
}