import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Filmes {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão  HTTP e buscar os top 250 filmes
        String url ="https://alura-filmes.herokuapp.com/conteudos";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        // pegar só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String , String>> listaDeFilmes = parser.parse(body);
        System.out.println(listaDeFilmes.size());
        System.out.println(listaDeFilmes.get(0));

        // exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String , String> filme : listaDeFilmes){
            String urlImagem = filme.get("image") ;
            String titulo = filme.get("title");
            InputStream inputStream =   new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";
            geradora.cria(inputStream ,nomeArquivo);

            System.out.println(filme.get("title"));
            System.out.println();
            System.out.println(filme.get("imDbRating"));

        }
    }
}