import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class extratorConteudoIMDB implements extratorConteudo {

    public List<Conteudo> extraiConteudos(String json) {
        
        //filtrar os dados que interessam (titulo, poster, classificação)
        jsonParser parser = new jsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        //popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("image")
                        .replaceAll("(@+)(.*).jpg$", "$1.jpg"); // regex para deixar a imagem melhor;

            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
