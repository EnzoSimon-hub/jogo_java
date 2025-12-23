import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Mundo extends World
{
    //constantes do mundo 
    public static final int L_C = 736;
    public static final int A_C = 736;
    public static int timer=200;
    public static int cout=0;
    private int qntMoedas=0;
    private boolean allCoins=false;
    private int inimigosMortos = 0;
    private int metaDeMortes = 3;
    
    // === MUDANÇA 1: STATIC ===
    // 'static' faz a música pertencer ao Jogo, e não apenas a esta fase.
    // Assim ela sobrevive à troca de mundos.
    private static GreenfootSound musicaFundo;

    public Mundo(){   
        super(L_C, A_C, 1,false);
        
        // === MUDANÇA 2: SÓ CRIA SE NÃO EXISTIR ===
        // Se a música já estiver tocando (vinda de outra fase ou reset),
        // ele não cria de novo.
        if (musicaFundo == null) {
            musicaFundo = new GreenfootSound("fundo.wav"); 
            musicaFundo.setVolume(40);
        }
        
        GreenfootImage cenarioini = new GreenfootImage("pixel.jpeg");
        setBackground(cenarioini);
        
        // Objetos
        bloco bloco=new bloco(350,40);
        addObject(bloco,171,588);
        bloco bloco2=new bloco(350,40);
        addObject(bloco2,144,232);
        bloco bloco3=new bloco(350,40);
        addObject(bloco3,633,589);
        bloco bloco4=new bloco(350,40);
        addObject(bloco4,652,199);
        bloco bloco5=new bloco(350,40);
        addObject(bloco5,620,402);
        player play = new player();
        addObject (play,207,546); 
        bloco wall=new bloco(10,736);
        addObject(wall,2,366);
        bloco wall2=new bloco(10,736);
        addObject(wall2,732,368);
        bloco wall3=new bloco(10,200);
        addObject(wall3,613,313);
        
        prepare();
        started();
    }

    public void started()
    {
        // Só toca se não estiver tocando ainda
        if (!musicaFundo.isPlaying()) {
            musicaFundo.playLoop();
        }
    }

    public void stopped()
    {
       
    }

    public void act()
    {
        spawnar();

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

    private void spawnar()
    {
        cout++;
        if(cout>=timer)
        {
            Rock pedra=new Rock();
            addObject(pedra,389,2);
            Greenfoot.playSound("queda.wav"); 
            cout=0;
        }
    }
    
    private void prepare()
    {
        inimigo inimigo = new inimigo();
        addObject(inimigo,176,138);
        inimigo inimigo2 = new inimigo();
        addObject(inimigo2,617,81);
        inimigo inimigo3 = new inimigo();
        addObject(inimigo3,552,495);
        Moeda moeda = new Moeda();
        addObject(moeda,598,359);
        moeda.setLocation(599,365);
        Moeda moeda2 = new Moeda();
        addObject(moeda2,684,551);
        moeda2.setLocation(684,557);
        Moeda moeda3 = new Moeda();
        addObject(moeda3,706,145);
        moeda3.setLocation(715,162);
        Moeda moeda4 = new Moeda();
        addObject(moeda4,61,206);
        moeda4.setLocation(20,185);
        Moeda moeda5 = new Moeda();
        addObject(moeda5,63,499);
        moeda5.setLocation(38,547);
        moeda5.setLocation(30,558);
    }

    public void KillCout() {
        inimigosMortos++;
                
        if (inimigosMortos == metaDeMortes) {
            
            
            Greenfoot.setWorld(new Mundo2());
        }
    }
}