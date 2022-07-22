import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class geradoraDeFigurinhas {
    
    void cria(InputStream inputStream, String nomeArquivo) throws Exception{

        //ler imagem

        //InputStream inputStream = new FileInputStream("Imagem/TopMovies_1.jpg");
        //BufferedImage imagemOrigianal = ImageIO.read(new File("Imagens/TopMovies_1.jpg"));

        //InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        BufferedImage imagemOrigianal = ImageIO.read(inputStream);

        //cria nova imagem em memómria com transparencia e com tamanha novo
        int largura = imagemOrigianal.getWidth();
        int altura = imagemOrigianal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original para novo imagem (em memoria)
        Graphics2D grafics = (Graphics2D) novaImagem.getGraphics();
        grafics.drawImage(imagemOrigianal, 0, 0, null);

        //configurar fonte
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 65);
        grafics.setColor(Color.RED);
        grafics.setFont(font);
        
        //escrever uma frase na nova imagem
        grafics.drawString("Isso é um filme", 100, novaAltura - 120);

        //escrever a imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }
}
