import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Calculadora {
    Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

    public double retornarValor (String endereco, String moeda, double valor) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build();
        HttpResponse<String> response = client
            .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        Map<String, Object> dados = gson.fromJson(json, Map.class);
        Map<String, Double> taxasConversao = (Map<String, Double>) dados.get("conversion_rates");


        return taxasConversao.get(moeda) * valor;
    }

    public String retornarSigla (String nomeMoeda) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://v6.exchangerate-api.com/v6/c79957ae827de732a205498c/codes"))
            .build();
        HttpResponse<String> response = client
            .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray supportedCodes = jsonObject.getAsJsonArray("supported_codes");

        for (int i = 0; i < supportedCodes.size(); i++) {
            JsonArray currencyPair = supportedCodes.get(i).getAsJsonArray();
            String sigla = currencyPair.get(0).getAsString();
            String currency = currencyPair.get(1).getAsString();
            
            if (currency.equalsIgnoreCase(nomeMoeda)) return sigla;
        }

        throw new NullPointerException("Currency '"+ nomeMoeda + "' not found."); 
    }
}
