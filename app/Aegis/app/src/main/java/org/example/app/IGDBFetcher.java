package org.example.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.example.secrets.client_secret;
import org.example.secrets.client_bearer_access_token;

public class IGDBFetcher {

    private static final String CLIENT_ID = "yaneluq4z6l6jqaew0q4av423ch6t5";
    private static final String CLIENT_SECRET = client_secret.getCLIENT_SECRET();
    private static client_bearer_access_token token = new client_bearer_access_token();

    public static void GET_api_request() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.igdb.com/v4/games/"))
                .header("Client-ID", CLIENT_ID)
                .header("Authorization", token.getClientBearerAccessToken())
                .header("Accept", "application/json")
                // POST
                .POST(HttpRequest.BodyPublishers.ofString(
                        "fields name; limit 500;"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        URI authUri = new URI(String.format(
                "https://id.twitch.tv/oauth2/token?client_id=%s&client_secret=%s&grant_type=client_credentials",
                CLIENT_ID, CLIENT_SECRET));
        URL authPath = authUri.toURL();
        URLConnection con = authPath.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);

        System.out.println(http.getResponseCode());

        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        String access_token = content.substring(content.indexOf("access_token") + 15,
                content.indexOf("expires_in") - 3);
        System.out.println(access_token);

        token.setclientBearerAccessToken(access_token);

        GET_api_request();

    }

}
