import java.io.InputStream;
import java.net.URL;
import java.util.List;

//Iniciar comendo os passos do programa

public class imdbApi {

    public static void main(String[] args) throws Exception {
        
        //fazer a conexão HTTP e fazer a busca dos 250 filmes

        //String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD-JamesWebbSpaceTelescope";
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2021-12-03&end_date=2021-12-12";
        extratorConteudo extrator = new extrratorConteudoNasa();
        
        //String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        //extratorConteudo extrator = new extratorConteudoIMDB();
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_q6h6s0ro";

        var http = new clienteHttp();
        String json = http.buscaDados(url);

        //exibir e manipular os dados
        
        var geradora = new geradoraDeFigurinhas();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        for(int i = 0; i<10; i++){ // for para os 10 primeiros filmes
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem())
                                                    .openStream();

            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }

        /*for (Map<String,String> filme : listaDeFilmes) {

            //String urlImagem = filme.get("image");
            String titulo = conteudo.get("title");

            String urlImagem = conteudo.get("url")
                //conteudo.get("image")
                .replaceAll("(@+)(.*).jpg$", "$1.jpg"); // regex para deixar a imagem melhor

            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = "saida/" + titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }*/
    }
}
