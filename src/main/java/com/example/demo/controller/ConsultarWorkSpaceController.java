package com.example.demo.controller;


import com.example.demo.model.dto.RequisicaoDTO;
import com.example.demo.model.service.DadosWorkSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ConsultarWorkSpaceController {

    @Autowired
    DadosWorkSpaceService dadosWorkSpaceService;

    @RequestMapping(value = "/buscar.do", method = RequestMethod.GET)
    @ResponseBody
    public String buscarWorkSpacePorUsuario(@RequestParam String codigoWorkSpace) throws Exception {
        try {
            if (codigoWorkSpace != null) {
                RequisicaoDTO resposta = dadosWorkSpaceService.buscarWorkSpacePorUsuario(codigoWorkSpace);
                return resposta.getResponse();
            } else {
                throw new Exception("Não foi possivel localizar o codigo do quadro.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}
