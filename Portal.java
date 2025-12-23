import greenfoot.*;

public class Portal extends Actor
{
    public Portal()
    {
        // Se você tiver uma imagem de porta, ele ajusta o tamanho aqui.
        // Ajuste 50, 80 para a largura e altura que você quiser.
        if (getImage() != null) {
            getImage().scale(50, 80); 
            GreenfootImage imagem = getImage(); 
            imagem.setTransparency(0);

        }
    }

    public void act()
    {
        // O portal não precisa fazer nada, ele só fica parado lá.
        // Se quiser que ele gire, você pode colocar turn(5); aqui.
    }
}