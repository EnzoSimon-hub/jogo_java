import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mundo2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mundo2 extends World
{
   
    public static int timer=200;
    public static int cout=0;
   private int qntMoedas=0;
   private boolean allCoins=false;
   private int inimigosMortos = 0;
   private int metaDeMortes = 3;
    public static final int L_C = 736;
    public static final int A_C = 736;

    /**
     * Constructor for objects of class Mundo2.
     * 
     */
    public Mundo2()
    {    
          super(L_C, A_C, 1); 
        
         bloco bloco=new bloco(320,20);
         addObject(bloco,142,663);
       
        player play = new player();
        addObject (play,21,562);
        
        bloco bloco2=new bloco(160,10);
        addObject(bloco2,69,603);
       
        bloco bloco3=new bloco(360,20);
        addObject(bloco3,446,521);
       
      bloco bloco4=new bloco(350,20);
      addObject(bloco4,493,663);
       
    bloco bloco5=new bloco(260,20);
      addObject(bloco5,490,226);
        
    bloco bloco6=new bloco(90,20);
      addObject(bloco6,724,227); 
      
       bloco bloco7=new bloco(370,20);
      addObject(bloco7,519,140);
      
      bloco bloco8=new bloco(90,20);
      addObject(bloco8,723,366); 
      
       bloco bloco9=new bloco(90,20);
      addObject(bloco9,722,609);
      
      Portal portal=new Portal();
      addObject(portal,561,92);
      
      InimigoAtirador ini=new InimigoAtirador();
      addObject(ini,314,477);

       InimigoAtirador ini2=new InimigoAtirador();
      addObject(ini2,704,324);
      
      InimigoAtirador ini3=new InimigoAtirador();
      addObject(ini3,406,181);
      

     /* inimigo ini4=new inimigo();
      addObject(ini4,400,465); */

    }
    public void act()
{
  
}

  public void addMoeda()
  {
      qntMoedas++;
      int total=5;
      if(qntMoedas==total)
      {
          arma besta = new arma();
        addObject(besta,551,326);
        allCoins = true;

      }
  }
  
   public void KillCout() {
        inimigosMortos++;
        
        System.out.println("Inimigos mortos: " + inimigosMortos + "/" + metaDeMortes);
        
        if (inimigosMortos == metaDeMortes) {
            
            Greenfoot.setWorld(new Mundo2());
        }
 }
}
