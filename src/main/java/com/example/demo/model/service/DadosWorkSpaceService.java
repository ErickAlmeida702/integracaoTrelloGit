package com.example.demo.model.service;

import com.example.demo.model.dto.RequisicaoDTO;

public interface DadosWorkSpaceService {

    RequisicaoDTO buscarWorkSpacePorUsuario(String codigoWorkSpace);
}
