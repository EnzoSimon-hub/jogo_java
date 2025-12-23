import greenfoot.*;
import java.util.List;

public class InimigoAtirador extends Actor
{
    // === IMAGENS (Agora temos pares para direita e esquerda) ===
    private GreenfootImage imagemParadoDireita;
    private GreenfootImage imagemParadoEsquerda;
    private GreenfootImage[] imagensTiroDireita; 
    private GreenfootImage[] imagensTiroEsquerda; 
    
    // === ESTADOS ===
    private boolean estahAtirando = false; 
    private boolean olhandoParaDireita = true; // Para saber qual imagem usar
    
    // === TIMERS ===
    private int timerRecarga = 0;
    private int tempoEntreTiros = 200; 
    
    // === ANIMAÇÃO ===
    private int frameAtualTiro = 0;
    private int timerAnimTiro = 0;
    private int velocidadeAnimTiro = 15; 

    public InimigoAtirador()
    {
        carregarImagens();
        setImage(imagemParadoDireita);
    }
    
    private void carregarImagens()
    {
        // 1. Carregar Imagem Parada (Direita e Esquerda)
        imagemParadoDireita = new GreenfootImage("inimigo2/ini_1.png");
        imagemParadoDireita.scale(70, 70);
        
        imagemParadoEsquerda = new GreenfootImage(imagemParadoDireita);
        imagemParadoEsquerda.mirrorHorizontally(); // Cria a versão esquerda
        
        // 2. Carregar Animação de Tiro
        imagensTiroDireita = new GreenfootImage[3];
        imagensTiroEsquerda = new GreenfootImage[3];
        
        for(int i = 0; i < 3; i++) {
            int numeroArquivo = i + 1; 
            String nomeArquivo = "inimigo2/ini_" + numeroArquivo + ".png";
            
            // Direita
            imagensTiroDireita[i] = new GreenfootImage(nomeArquivo);
            imagensTiroDireita[i].scale(70, 70);
            
            // Esquerda
            imagensTiroEsquerda[i] = new GreenfootImage(imagensTiroDireita[i]);
            imagensTiroEsquerda[i].mirrorHorizontally();
        }
    }

    public void act()
    {
        // Agora checamos o lado sem girar o boneco de cabeça pra baixo
        verificarLadoDoPlayer(); 

        if (estahAtirando) {
            rodarAnimacaoTiro(); 
        } else {
            esperarParaAtirar(); 
        }
    }
    
    // === NOVA LÓGICA: Apenas olha para o lado certo ===
    private void verificarLadoDoPlayer()
    {
        List<player> jogadores = getWorld().getObjects(player.class);
        if (!jogadores.isEmpty()) {
            player alvo = jogadores.get(0);
            
            // Se o player está à direita (X maior)
            if (alvo.getX() > getX()) {
                olhandoParaDireita = true;
            } 
            // Se o player está à esquerda (X menor)
            else {
                olhandoParaDireita = false;
            }
            
            // Atualiza a imagem se não estiver atirando (pra não travar animação)
            if (!estahAtirando) {
                if (olhandoParaDireita) setImage(imagemParadoDireita);
                else setImage(imagemParadoEsquerda);
            }
        }
    }
    
    private void esperarParaAtirar()
    {
        timerRecarga++;
        if (timerRecarga >= tempoEntreTiros) {
            timerRecarga = 0;
            iniciarDisparo();
        }
    }
    
    private void iniciarDisparo()
    {
        List<player> jogadores = getWorld().getObjects(player.class);
        if (!jogadores.isEmpty()) {
            estahAtirando = true;
            frameAtualTiro = 0;
            timerAnimTiro = 0;
            
            // Define a primeira imagem da animação baseada no lado
            if (olhandoParaDireita) setImage(imagensTiroDireita[0]);
            else setImage(imagensTiroEsquerda[0]);
        }
    }
    
    private void rodarAnimacaoTiro()
    {
        timerAnimTiro++;
        
        if (timerAnimTiro >= velocidadeAnimTiro) {
            timerAnimTiro = 0;
            frameAtualTiro++;
            
            if (frameAtualTiro == 1) {
                criarBala();
            }

            if (frameAtualTiro >= 3) {
                estahAtirando = false;
                // Volta para a imagem parada correta
                if (olhandoParaDireita) setImage(imagemParadoDireita);
                else setImage(imagemParadoEsquerda);
            } else {
                // Define a imagem da animação correta
                if (olhandoParaDireita) setImage(imagensTiroDireita[frameAtualTiro]);
                else setImage(imagensTiroEsquerda[frameAtualTiro]);
            }
        }
    }
    
    // No final da classe InimigoAtirador...

    private void criarBala()
    {
        // Verifica se o player existe só para não dar erro de criar bala sem alvo
        if (!getWorld().getObjects(player.class).isEmpty()) {
            
            // Cria a bala na posição do inimigo
            // A própria bala vai se virar para o player sozinha agora.
            BalaInimigo bala = new BalaInimigo();
            getWorld().addObject(bala, getX(), getY());
            
            Greenfoot.playSound("atirar.wav");
        }
    }
}