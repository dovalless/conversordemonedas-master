import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorMoneda {

    public MonedasApi buscaMoneda(String moneda1, String moneda2, double valor){

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/c104e6ab710cda0d9951d392/pair/"+moneda1+"/"+moneda2+"/"+valor);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> response;

        {
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return new Gson().fromJson(response.body(), MonedasApi.class);

            } catch (IOException  | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }


}
