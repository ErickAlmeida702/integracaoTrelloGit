package com.example.demo.model.service.Impl;

import com.example.demo.model.dto.RequisicaoDTO;
import com.example.demo.model.service.AutenticadorApiKeyService;
import com.example.demo.model.service.DadosWorkSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;

@Service
public class DadoWorkSpaceServiceImpl implements DadosWorkSpaceService {

    @Autowired
    AutenticadorApiKeyService autenticadorApiKeyService;

    private static final String URL_QUADROS_USUARIO = "1/boards/%s/?cards=all";

    public RequisicaoDTO buscarWorkSpacePorUsuario(String codigoWorkSpace) {
        String url = String.format(URL_QUADROS_USUARIO, codigoWorkSpace);
        try {
            HttpURLConnection urlAutenticadaMontada = autenticadorApiKeyService.montarURLAutenticada(url, "GET");
            return autenticadorApiKeyService.enviarRequest(urlAutenticadaMontada);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
