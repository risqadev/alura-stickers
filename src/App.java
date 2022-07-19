import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class App {
    public static void main(String[] args) throws Exception {

        // obter a lista dos Top 250 Filmes do IMDB através da API

        String url = "https://alura-filmes.herokuapp.com/conteudos"; // original: "https://imdb-api.com/en/API/Top250Movies/{myAPIKey}"
        URI uri = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        System.out.println(body);
    }
}
