import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Um objeto simples que apenas cai devido à gravidade.
 * Pode ser um obstáculo, projétil que cai, etc.
 */
public class Rock extends Actor
{
    private int velocidadeQueda = 0; // A velocidade vertical do objeto
    private final int gravidade = 1; // A força da gravidade
    private final int velMax = 11;
    private GreenfootImage[] frames;
    
    // 2. Controladores da animação
    private int frameAtual = 0;
    private int contadorTimer = 0;
    
    // 3. Velocidade da animação 
    private int velocidadeAnimacao = 5; // Limite para não atravessar o chão

    public Rock()
    {
        if (getImage() != null) {
            getImage().scale(90, 90); }
            
        int totalFrames = 12; 
        frames = new GreenfootImage[totalFrames];
        
        // 2. Defina o nome da sua pasta
        String nomeDaPasta = "pedra"; 
        
        // 3. Defina o prefixo do nome dos seus arquivos
        String prefixoArquivo = "Pedra_";
        
        // 4. Carrega todas as imagens
        for (int i = 0; i < frames.length; i++) {
            // Isso vai montar o nome do arquivo, ex: "moedas/download_1.png"
            frames[i] = new GreenfootImage(nomeDaPasta + "/" + prefixoArquivo + (i + 1) + ".png");
            
            frames[i].scale(90, 90); 
    
        }
        
        setImage(frames[0]);
    }

    

    public void act()
    {
        contadorTimer++;
        
        if (contadorTimer >= velocidadeAnimacao) {
            contadorTimer = 0;
            animar();
        }
        
        aplicarGravidade();
        moverObjeto();
        caiuDoMapa(); 
    }

    
    private void aplicarGravidade()
    {
        velocidadeQueda += gravidade;
        // Limita a velocidade de queda para não ser muito rápida e atravessar objetos
        if (velocidadeQueda > velMax) {
            velocidadeQueda = velMax;
        }
    }

    
    private void moverObjeto()
    {
        setLocation(getX(), getY() + velocidadeQueda);
    }

    private void gera()
    {
        
    }
    
    
    private void caiuDoMapa()
    {
        // Se a parte de baixo do objeto estiver abaixo da borda inferior do mundo
        if (getY() > getWorld().getHeight() + (getImage().getHeight() / 2))
        {
            getWorld().removeObject(this);
        }
    }
    private void animar() {
        frameAtual++;

        if (frameAtual >= frames.length) {
            frameAtual = 0;
        }
        
        setImage(frames[frameAtual]);
    }
}