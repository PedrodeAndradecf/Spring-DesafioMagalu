package Desafio.example.springMagalu;

import Desafio.example.springMagalu.controller.AgendamentoController;
import Desafio.example.springMagalu.dto.AgendamentoRequestDTO;
import Desafio.example.springMagalu.enums.TipoComunicacao;
import Desafio.example.springMagalu.service.AgendamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

@WebMvcTest(AgendamentoController.class)
public class AgendamentoCOntrollerTest {

    //PERMITE SIMULAR REQUISOÇÕES HTTP
    @AutoClose
    private MockMvc mockMvc;


    // Converte objetos em JSON e vice-versa
    @Autowired
    private ObjectMapper objectMapper;

    // cria um mock do service dentro do controller
    // evita chamadas reais ao banco de dados ou regra de negocios
    // Esse mock vai ser injetado no controller pelo SPring simulando  o comportamento esperado para testes
    @MockitoBean
    private AgendamentoService service;

    @Test
    void deveAgendarComunicacao () throws Exception {
        AgendamentoRequestDTO requestDTO = new AgendamentoRequestDTO(
                "cliente@gmail.com",
                "mensagem de teste",
                TipoComunicacao.EMAIL,
                LocalDateTime.now().plusMinutes(1)
        );

    }


}
