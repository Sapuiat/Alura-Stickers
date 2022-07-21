import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream,  String nomeArquivo) throws IOException {

        // leitura da imagem

        //BufferedImage imagemOriginal = ImageIO.read(new File("src/filme.jpg"));
        //InputStream inputStream =   new URL("https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@.jpg%22).openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura , novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memória)

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal,0,0,null);

        //fonte
        var fonte = new Font(Font.SANS_SERIF , Font.BOLD, 16);
        graphics.setFont(fonte);
        // escrever uma frase na nova imagem
        graphics.drawString("FILMÃO" ,35, novaAltura-100);
        graphics.setColor(Color.ORANGE);
        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

}