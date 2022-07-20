import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
//import java.io.FileInputStream;
//import java.net.URL;

import javax.imageio.ImageIO;

public class StickerFactory {

    void create(InputStream input, String phrase, int textPositionX, String output) throws IOException {
        // leitura da imagem
        // InputStream inputStream = new FileInputStream(file);
        // InputStream inputStream = new URL(url);
        BufferedImage originalImage = ImageIO.read(input);

        // cria nova imagem em memória com transparência e com tamanho novo
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int addHeight = height/6;
        int fontSize = (int)(addHeight*0.95);
        int newHeight = height + addHeight;
        int textPositionY = newHeight - addHeight/10;
        BufferedImage newImagem = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para a nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) newImagem.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // configurar a fonte e escrever texto na imagem
        var font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        graphics.drawString(phrase, textPositionX, textPositionY);

        // escrever a nova imagem em arquivo
        ImageIO.write(newImagem, "png", new File(output));
    }
}
