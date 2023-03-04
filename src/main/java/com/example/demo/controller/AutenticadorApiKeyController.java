package com.example.demo.controller;


import com.example.demo.model.service.AutenticadorApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/Autenticador")
public class AutenticadorApiKeyController {

    @Autowired
    AutenticadorApiKeyService autenticadorApiKeyService;

    @RequestMapping(value = "validarToken.do")
    public Boolean validarToken(@RequestParam String APIKey, @RequestParam String APIToken) {
        try {
            return autenticadorApiKeyService.validarTokens(APIKey, APIToken);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel validar o token com o trello");
        }
    }
}
