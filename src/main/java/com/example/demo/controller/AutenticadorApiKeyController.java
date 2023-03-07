package com.example.demo.controller;


import com.example.demo.model.service.AutenticadorApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/Autenticador")
public class AutenticadorApiKeyController {

    @Autowired
    private AutenticadorApiKeyService autenticadorApiKeyService;

    @RequestMapping(value = "validarToken.do")
    @ResponseBody
    public Boolean validarToken(@RequestParam String APIKey, @RequestParam String APIToken) {
        try {
            return autenticadorApiKeyService.validarTokens(APIKey, APIToken);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel validar o token:" + e.getMessage());
        }
    }
}
