import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class menu extends World
{
public static final int L_C = 736;
    public static final int A_C = 736;
    public menu()
    {    
        super(L_C, A_C, 1); 
        start start=new start();
        addObject(start,364,192);

    }
}
