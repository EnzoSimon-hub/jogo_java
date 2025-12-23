import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class inimigo extends Actor
{
    // === VARIÁVEIS ORIGINAIS ===
    private int vel = 6;
    private int queda = 0;
    private int Hitbox = 30;
    
    // === VARIÁVEIS DE ANIMAÇÃO (NOVAS) ===
    private GreenfootImage[] animacaoDireita;
    private GreenfootImage[] animacaoEsquerda;
    private int imagemAtual = 1; // Começa no 1 (corrida)
    private int timerAnimacao = 0;
    private int velocidadeAnimacao = 5; // Ajuste a velocidade das pernas aqui

    public inimigo()
    {
        // Inicializa os arrays
        animacaoDireita = new GreenfootImage[6];
        animacaoEsquerda = new GreenfootImage[6];

        // Carrega as imagens ini_1.png até ini_6.png
        for (int i = 0; i < 6; i++) {
            int numeroArquivo = i + 1; 
            String nomeArquivo = "inimigo/ini_" + numeroArquivo + ".png";
            
            // Carrega e define o tamanho 70x70 IMEDIATAMENTE
            GreenfootImage img = new GreenfootImage(nomeArquivo);
            img.scale(70, 70); 
            
            // Salva na lista da direita
            animacaoDireita[i] = img;
            
            // Cria a cópia para a esquerda e espelha
            animacaoEsquerda[i] = new GreenfootImage(animacaoDireita[i]);
            animacaoEsquerda[i].mirrorHorizontally();
        }
        
        setImage(animacaoDireita[1]); // Define imagem inicial
    }
    
    public void act()
    {
        mov();
        verParedes();
        checkFalling();
        fall();
        jump();
        animar(); // Chama a animação a cada ciclo
    }
    
    public void mov()
    {
       setLocation(getX() + vel, getY()); 
    }
    
    public void verParedes()
    {
        // Se bater na direita
        if (getX() >= getWorld().getWidth() - 5 && vel > 0)
        {
            vel = -6;
            // NÃO precisa mais do mirrorHorizontally aqui, o animar() resolve
        }

        // Se bater na esquerda
        if (getX() <= 5 && vel < 0)
        {
            vel = 6;
            // NÃO precisa mais do mirrorHorizontally aqui
        }
    }
    
    // === NOVO MÉTODO DE ANIMAÇÃO ===
    public void animar()
    {
        timerAnimacao++;
        if (timerAnimacao >= velocidadeAnimacao) {
            timerAnimacao = 0;
            
            // Cicla entre as imagens 1 e 5 (Correndo)
            imagemAtual++;
            if (imagemAtual > 5) {
                imagemAtual = 1; // Volta para o inicio da corrida (pula o 0 que é parado)
            }
            
            // Verifica a direção pela velocidade (vel)
            if (vel > 0) {
                setImage(animacaoDireita[imagemAtual]);
            } else {
                setImage(animacaoEsquerda[imagemAtual]);
            }
        }
    }
    
    public void jump()
    {
       int x = getX();
       if(x >= 295 && x <= 316 && vel > 0)
       {
           queda = -14;
       }
       
       if(x >= 505 && x <= 518 && vel < 0)
       {
           queda = -15;
       }
    }
    
    public boolean isOnGround()
    {
        Actor chao = getOneObjectAtOffset(0, (getImage().getHeight() / 2) + 1, bloco.class);
        return chao != null;
    }
    
    public void checkFalling()
    {
        if (!isOnGround())
        {
            queda++;
        }
        else
        {
            if (queda > 0) { 
                queda = 0; 
            
                Actor blocoAbaixo = getOneObjectAtOffset(0, (getImage().getHeight() / 2), bloco.class);
                if (blocoAbaixo != null) {
                   int novaAlturaY = blocoAbaixo.getY() - (blocoAbaixo.getImage().getHeight() / 2) - (getImage().getHeight() / 2) + 5;
                   setLocation(getX(), novaAlturaY);
                }
            } 
        }
    }

    private void fall()
    {
        setLocation(getX(), getY() + queda);
    }
}