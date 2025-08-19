package com.aluracursos.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

public class CurrencyExchange {
    public Currency exchange(String baseCurrency, String targetCurrency, double amount) {
        String apiKey = ConfigLoader.getApiKey();

        try {
            String uri = String.format(Locale.US,"https://v6.exchangerate-api.com/v6/%s/pair/%S/%S/%.2f/", apiKey, baseCurrency, targetCurrency, amount);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.fromJson(response.body(), Currency.class);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al conectar con el servicio de cambio de monedas.");
            throw new RuntimeException(e);
        }
    }
}
