import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

//Iniciar comendo os passos do programa

public class imdbApi {

    public static void main(String[] args) throws Exception {
        
        //fazer a conexão HTTP e fazer a busca dos 250 filmes

        //String url = "https://api.mocki.io/v2/549a5d8b"
        String url = "https://imdb-api.com/en/API/Top250Movies/k_q6h6s0ro";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient(); //criado usando o atalho "Ctrl + ."
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build(); //criação de um request da API - visitar javadoc para saber mais de http request
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //System.out.println(body);
        
        //filtrar os dados que interessam (titulo, poster, classificação)
        jsonParser parser = new jsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        /* System.out.println(listaDeFilmes.size());
        System.out.println(listaDeFilmes.get(0)); */

        //exibir e manipular os dados

        var geradora = new geradoraDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = titulo+".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }
    }
}
