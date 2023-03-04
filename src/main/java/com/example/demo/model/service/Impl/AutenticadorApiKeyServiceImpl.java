package com.example.demo.model.service.Impl;

import com.example.demo.model.dto.RequisicaoDTO;
import com.example.demo.model.service.AutenticadorApiKeyService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class AutenticadorApiKeyServiceImpl implements AutenticadorApiKeyService {

    private static final String URL_BASE = "https://api.trello.com/";

    private static final String URL_VALIDACAO_TOKEN = "https://api.trello.com/1/tokens/%s?key=%s&token=%s";

    public HttpURLConnection montarURLAutenticada(String url, String tipoRequisicao) throws IOException {

        String APIKey = "0076141fb92e1b1976ef4088d9d93d7a";
        String APIToken = "ATTA02b98f9078c96757972b1530dac3f6e12471e1a676b7a2d7a7b890489cfbd9aaAC3D4284";

        String urlAutenticada = URL_BASE + url + "&key=" + APIKey + "&" + "token=" + APIToken;
        URL obj = new URL(urlAutenticada);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(tipoRequisicao);
        con.setRequestProperty("Accept", "application/json");

        return con;
    }

    public RequisicaoDTO enviarRequest(HttpURLConnection con) throws IOException {
        RequisicaoDTO requisicaoDTO = new RequisicaoDTO();
        int responseCode = con.getResponseCode();
        StringBuilder response = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
//            JSONObject json = new JSONObject(response.toString());
        }
        requisicaoDTO.setResponseCode(responseCode);
        requisicaoDTO.setResponse(response.toString());
        return null;
    }

    public Boolean validarTokens(String APIKey, String APIToken) {
        String url = String.format(URL_VALIDACAO_TOKEN, APIToken, APIKey, APIToken);
        try {
            HttpURLConnection urlAutenticadaMontada = montarURLAutenticada(url, "GET");
            RequisicaoDTO requisicaoDTO = enviarRequest(urlAutenticadaMontada);
            return requisicaoDTO.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
