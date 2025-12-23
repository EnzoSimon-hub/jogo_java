import greenfoot.*;

public class BalaInimigo extends Actor
{
    private int velocidade = 8; 
    private int HitBox=30;
    public BalaInimigo()
    {
        // Ajuste o tamanho (se precisar)
        if(getImage() != null) {
            getImage().scale(45, 45); 
        }
        
        // ====================================================================
        // ÁREA DE CORREÇÃO DE IMAGEM INVERTIDA
        // ====================================================================
        // O Greenfoot acha que a frente é a DIREITA.
        
        // TENTATIVA 1: Se sua flecha está voando de ré (a ponta está para trás).
        // TIRE AS DUAS BARRAS (//) DA LINHA ABAIXO PARA TESTAR:
        
         getImage().mirrorHorizontally(); 

        
        // TENTATIVA 2: Se a TENTATIVA 1 não funcionou e ela agora voa de lado (ponta pra cima ou baixo).
        // COLOQUE AS BARRAS DE VOLTA NA LINHA DE CIMA E TIRE AS BARRAS DA LINHA ABAIXO:
        
        // getImage().rotate(90);
        
        // ====================================================================
    }
    
    // O resto da classe continua igual...
    public void addedToWorld(World world)
    {
        mirarNoPlayer();
    }

    public void act()
    {
        move(velocidade); 
        verificarColisoes();
    }
    
    private void mirarNoPlayer()
    {
        if (!getWorld().getObjects(player.class).isEmpty()) {
             Actor alvo = (Actor) getWorld().getObjects(player.class).get(0);
             turnTowards(alvo.getX(), alvo.getY());
        }
    }
    
    public void verificarColisoes()
    {
        if (isAtEdge()) 
        {
            getWorld().removeObject(this);
            return; 
        }

        // 2. Se bater no Player
        if (!getObjectsInRange(HitBox, inimigo.class).isEmpty()) 
        {
            // Remove o player do mundo
            getWorld().removeObject(this);
            Greenfoot.setWorld(new Mundo());
            return;
            
            // Remove a flecha também
        }
    }
}