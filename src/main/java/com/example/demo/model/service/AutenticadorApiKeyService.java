package com.example.demo.model.service;

import com.example.demo.model.dto.RequisicaoDTO;

import java.io.IOException;
import java.net.HttpURLConnection;


public interface AutenticadorApiKeyService {

    HttpURLConnection montarURLAutenticada(String url, String tipoRequisicao) throws IOException;

    RequisicaoDTO enviarRequest(HttpURLConnection con) throws IOException;

    Boolean validarTokens(String APIKey, String APIToken) throws IOException;

}
