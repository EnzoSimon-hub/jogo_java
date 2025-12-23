import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bloco here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bloco extends Actor
{
        
    public void act()
    {
        
    }
    
    bloco(int l, int a)
    {
        getImage().scale(l,a);
        GreenfootImage imagem = getImage(); 
        imagem.setTransparency(0);
        
    }
    
}
