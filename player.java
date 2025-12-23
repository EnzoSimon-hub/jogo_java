import greenfoot.*;

public class player extends Actor
{
    // === TAMANHO FIXO DO PERSONAGEM ===
    private final int l = 70; // Largura definida para 70
    private final int h = 70; // Altura definida para 70

    // === Variáveis de Física ===
    private int vel = 0;
    private final int forcaPulo = -15; 
    private final int velocidadeHorizontal = 4;
    private boolean olhandoParaDireita = true;
    

    // === Pulo Duplo ===
    private final int maxPulos = 1; //pulo a mais
    private int puloscnt = maxPulos;
    private boolean ant = false;
    private int Hitbox = 20;
    
    // === Combate ===
    private boolean recarga = true;
    private int cooldown = 0;
    private boolean atirar = false;

    // === Animação ===
    private GreenfootImage[] andandoDireita;
    private GreenfootImage[] andandoEsquerda;
    private int imagemAtual = 0;
    private int timerAnimacao = 0;
    private int velocidadeAnimacao = 5; 

    public player()
    {
        // Inicializa os arrays para 4 imagens
        andandoDireita = new GreenfootImage[4];
        andandoEsquerda = new GreenfootImage[4];

        // Loop para carregar os arrays direita e esquerda
        for (int i = 0; i < 4; i++) {
            // Define o nome: actor_1.png, actor_2.png...
            int numeroArquivo = i + 1; 
            String nomeArquivo = "personagem/actor_" + numeroArquivo + ".png";
            
            // 1. Carrega a imagem original
            GreenfootImage img = new GreenfootImage(nomeArquivo);
            
            // 2. FORÇA A ESCALA 70x70 AQUI
            // Isso garante que ela nunca fique com outro tamanho
            img.scale(l, h); 
            
            // 3. Salva na lista da direita
            andandoDireita[i] = img;
            
            // 4. Cria a cópia para a esquerda
            andandoEsquerda[i] = new GreenfootImage(andandoDireita[i]);
            
            // Como a cópia veio da direita (que já é 70x70), ela já está no tamanho certo.
            // Só precisamos espelhar:
            andandoEsquerda[i].mirrorHorizontally();
        }

        // Define a imagem inicial já no tamanho correto
        setImage(andandoDireita[0]); 
    }
    
    public void act()
    {
        // Verifica Morte
        if (!getObjectsInRange(Hitbox, inimigo.class).isEmpty() || !getObjectsInRange(Hitbox+20, Rock.class).isEmpty())
        {
            getWorld().removeObject(this);
            Greenfoot.setWorld(new Mundo());
            return;
        }
        //mundo2
        if(!getObjectsInRange(Hitbox, BalaInimigo.class).isEmpty())
        {
            getWorld().removeObject(this);
            Greenfoot.setWorld(new Mundo2());
            return;
        }
       
        //porta da torre
        checarPortal();
       
        verificarTeto();
        //pulos no geral
        handleJumpInput();
        
        // Movimento e Animação
        boolean estaAndando = handleHorizontalMovement(); 
        animar(estaAndando);

        checkFalling();
        cairDoMapa();
        fall();
       
        // Cooldown Arma
        if(cooldown > 0) {
           cooldown--;
           recarga = true;
        }
       
        if(cooldown == 0 && atirar && recarga) {
             Greenfoot.playSound("recarga.wav");
             recarga = false;
        }
        
        // Coletar Moeda
        //ponteiro da moeda tocada
        Actor moedaTocada = getOneIntersectingObject(Moeda.class);
        if (moedaTocada != null) {
            Greenfoot.playSound("som_moeda.wav");
            getWorld().removeObject(moedaTocada);
            Mundo mundo = (Mundo) getWorld();
            mundo.addMoeda();
        }
      
        // Pegar Arma
        Actor bestcol = getOneIntersectingObject(arma.class);
        if (bestcol != null) {
            getWorld().removeObject(bestcol);
            Greenfoot.playSound("som_arma.wav");
            atirar = true;
        }
    
        // Atirar
        if(atirar && Greenfoot.isKeyDown("l") && cooldown == 0) {
            getWorld().addObject(new Tiro(olhandoParaDireita), getX(), getY());
            Greenfoot.playSound("atirar.wav");
            cooldown = 50;
        }
        qualmundo();
    }

    private void qualmundo()
{
  World mundoAtual = getWorld();

    if(getWorld() instanceof Mundo2)
    {
        atirar=true;
    }

    
}
    
    private void verificarTeto()
    {
       
        World mundoAtual = getWorld();
        
        // 2. Verifica se é o Mundo 1
        if (mundoAtual instanceof Mundo && vel<0)
         {
           Actor teto = getOneObjectAtOffset(0, -getImage().getHeight() / 2, bloco.class);
           if (teto != null) {
               vel = 0; 
               int novaAlturaY = teto.getY() + (teto.getImage().getHeight() / 2) + (getImage().getHeight() / 2);
               setLocation(getX(), novaAlturaY);
           }
        }
    }

    private void handleJumpInput()
    {
        boolean spaceAgora = Greenfoot.isKeyDown("space");
        if (spaceAgora && !ant && puloscnt > 0) {
            jump();
        }
        ant = spaceAgora;
    }
    
    private void jump()
    {
        vel = forcaPulo; 
        puloscnt--;      
    }
    
    private boolean handleHorizontalMovement()
    {
        int movimento = 0;
        int direcao = 0;
        boolean moveu = false;
        
        if(Greenfoot.isKeyDown("d")) {
            movimento = velocidadeHorizontal;
            direcao = 1;
            olhandoParaDireita = true;
            moveu = true;
        }
        else if(Greenfoot.isKeyDown("a")) {
            movimento = -velocidadeHorizontal;
            direcao = -1;
            olhandoParaDireita = false;
            moveu = true;
        }
        
        if (direcao != 0) {
            int lookAhead = direcao * velocidadeHorizontal; 
            if (getOneObjectAtOffset(lookAhead, 0, bloco.class) == null) {
                move(movimento);
            }
        }
        return moveu;
    }
    
    private void animar(boolean estaAndando)
    {
        if (estaAndando)
 {
            timerAnimacao++;
            if (timerAnimacao >= velocidadeAnimacao) {
                timerAnimacao = 0;
                imagemAtual = (imagemAtual + 1) % 4; 
                
                if (olhandoParaDireita) {
                    setImage(andandoDireita[imagemAtual]);
                } else {
                    setImage(andandoEsquerda[imagemAtual]);
                }
            }
        } else {
            imagemAtual = 0;
            timerAnimacao = 0;
            if (olhandoParaDireita) {
                setImage(andandoDireita[0]);
            } else {
                setImage(andandoEsquerda[0]);
            }
        }
    }
    
    //funcao gravidade 
    private void fall()
    {
        setLocation(getX(), getY() + vel);
    }
    
    public boolean isOnGround()
    {
        Actor chao = getOneObjectAtOffset(0, (getImage().getHeight() / 2) + 1, bloco.class);
        return chao != null;
    }

    public void checkFalling()
    {
        if (!isOnGround()) {
            vel++;
        } else {
            puloscnt = maxPulos;
            if (vel > 0) { 
                vel = 0;
                Actor blocoAbaixo = getOneObjectAtOffset(0, (getImage().getHeight() / 2), bloco.class);
                if (blocoAbaixo != null) {
                    int novaAlturaY = blocoAbaixo.getY() - (blocoAbaixo.getImage().getHeight() / 2) - (getImage().getHeight() / 2)+5;
                    setLocation(getX(), novaAlturaY);
                }
            }
        }
    }

    private void cairDoMapa()
    {
        if (getY() > getWorld().getHeight() + (getImage().getHeight() / 2)) {
            Greenfoot.setWorld(new Mundo());
            return;
        }
    }
    
    public void checarPortal()
    {
        if (isTouching(Portal.class)) 
        {
            
            if (getWorld() instanceof Mundo2) 
            {
                
                Greenfoot.setWorld(new Mundo3());
            }
            
        }
    }
}