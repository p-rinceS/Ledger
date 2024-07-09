package app.src.main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class Authentication {

    private static final String CLIENT_ID = "yaneluq4z6l6jqaew0q4av423ch6t5";
    private static final String CLIENT_SECRET = Client_Secret.CLIENT_SECRET();
    
    public static void GET_api_request() throws IOException {
    
        URL url = new URL("https://api.igdb.com/v4/games");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Client-ID", "yaneluq4z6l6jqaew0q4av423ch6t5");
        con.setRequestProperty("Authorization", "Bearer pyljpb8f5wys0ezxe3s20dygh6wyf4");
        con.setRequestProperty("Accept", "application/json");
    
        int status = con.getResponseCode();
        System.out.println(status);
    }
    

    public static void main(String[] args) throws IOException {

        // %s is a placeholder for the client_id and client_secret
        URL auth_path = new URL(String.format("https://id.twitch.tv/oauth2/token?client_id=%s&client_secret=%s&grant_type=client_credentials", CLIENT_ID, CLIENT_SECRET));
        URLConnection con = auth_path.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        // 200 GOOD
        System.out.println(http.getResponseCode());
        // GET_api_request();
        
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






