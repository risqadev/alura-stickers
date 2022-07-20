import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // obter a lista dos Top 250 Filmes do IMDB através da API

        // original: "https://imdb-api.com/en/API/Top250Movies/{myAPIKey}"
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        URI uri = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String,String>> moviesList = parser.parse(body);

        var generator = new StickerFactory();

        for (Map<String,String> movie : moviesList) {
            String imageUrl = movie.get("image");
            String title = movie.get("title").replace(" ", "_");
            String imDbRating = movie.get("imDbRating");
            String outputFolder = "output/";
            String outputPath = outputFolder + title + ".png";
            
            InputStream imageStream = new URL(imageUrl).openStream();
            
            generator.create(imageStream, imDbRating, 50, outputPath);

            System.out.println(movie.get("fullTitle"));
        }
    }
}