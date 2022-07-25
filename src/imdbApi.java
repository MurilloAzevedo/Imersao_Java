import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

//Iniciar comendo os passos do programa

public class imdbApi {

    public static void main(String[] args) throws Exception {
        
        //fazer a conexão HTTP e fazer a busca dos 250 filmes

        //String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD-JamesWebbSpaceTelescope";
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2021-12-03&end_date=2021-12-12";
        //String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_q6h6s0ro";

        var http = new clienteHttp();
        String json = http.buscaDados(url);

        //System.out.println(body);
        
        //filtrar os dados que interessam (titulo, poster, classificação)
        jsonParser parser = new jsonParser();
        List<Map<String, String>> listaDeConteudo = parser.parse(json);
        /* System.out.println(listaDeFilmes.size());
        System.out.println(listaDeFilmes.get(0)); */

        //exibir e manipular os dados

        var geradora = new geradoraDeFigurinhas();
        for(int i = 0; i<10; i++){ // for para os 10 primeiros filmes
            Map<String,String> conteudo = listaDeConteudo.get(i);

            String titulo = conteudo.get("title");

            String urlImagem = conteudo.get("url")
                //conteudo.get("image")
                .replaceAll("(@+)(.*).jpg$", "$1.jpg"); // regex para deixar a imagem melhor

            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = "saida/" + titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
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
