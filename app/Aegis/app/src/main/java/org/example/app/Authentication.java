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

public class Authentication {

    private static final String CLIENT_ID = "yaneluq4z6l6jqaew0q4av423ch6t5";
    private static final String CLIENT_SECRET = client_secret.getCLIENT_SECRET();

    public static void GET_api_request() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.igdb.com/v4/games"))
                .header("Client-ID", CLIENT_ID)
                .header("Authorization", client_bearer_access_token.getClientBearerAccessToken())
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        // %s is a placeholder for the client_id and client_secret
        URI authUri = new URI(String.format(
                "https://id.twitch.tv/oauth2/token?client_id=%s&client_secret=%s&grant_type=client_credentials",
                CLIENT_ID, CLIENT_SECRET));
        URL authPath = authUri.toURL();
        URLConnection con = authPath.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        // 200 GOOD
        System.out.println(http.getResponseCode());
        GET_api_request();

        // print response Body
        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        System.out.println(content);

        // print type of content
        System.out.println(http.getContentType());

    }

}
