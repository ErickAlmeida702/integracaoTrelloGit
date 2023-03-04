package com.example.demo.model.service.Impl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class QuadroQualidadeServiceImpl {

    private static final String CRIAR_QUADRO_QUALIDADE = "https://api.trello.com/1/boards/";

    public void criarQuadroDeQualidade() {
        try {
            String nomeQuadro = "Reports de qualidade";
            JSONObject path = new JSONObject();

            path.put("name", nomeQuadro);
            URL obj = new URL(CRIAR_QUADRO_QUALIDADE);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(path.toString().getBytes());
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject json = new JSONObject(response.toString());
                String boardId = json.getString("id");
                // faz algo com o ID do board retornado
            }
        } catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
