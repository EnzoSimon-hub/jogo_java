import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot)

public class Moeda extends Actor {
    
    // 1. Array para guardar todas as imagens (frames) da animação
    private GreenfootImage[] frames;
    
    // 2. Controladores da animação
    private int frameAtual = 0;
    private int contadorTimer = 0;
    
    // 3. Velocidade da animação 
    private int velocidadeAnimacao = 5; 

    
    public Moeda() {
        // 1. Defina o número de frames que você tem
        int totalFrames = 10; 
        frames = new GreenfootImage[totalFrames];
        
        // 2. Defina o nome da sua pasta
        String nomeDaPasta = "moedas"; 
        
        // 3. Defina o prefixo do nome dos seus arquivos
        String prefixoArquivo = "download_";
        
        // 4. Carrega todas as imagens
        for (int i = 0; i < frames.length; i++) {
            // Isso vai montar o nome do arquivo, ex: "moedas/download_1.png"
            frames[i] = new GreenfootImage(nomeDaPasta + "/" + prefixoArquivo + (i + 1) + ".png");
            
            frames[i].scale(30, 30); 
    
        }
        
        setImage(frames[0]);
    }

    public void act() {
        contadorTimer++;
        
        if (contadorTimer >= velocidadeAnimacao) {
            contadorTimer = 0;
            animar();
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