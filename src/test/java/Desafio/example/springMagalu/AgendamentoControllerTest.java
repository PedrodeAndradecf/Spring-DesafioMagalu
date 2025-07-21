package Desafio.example.springMagalu;

import Desafio.example.springMagalu.controller.AgendamentoController;
import Desafio.example.springMagalu.dto.AgendamentoRequestDTO;
import Desafio.example.springMagalu.dto.AgendamentoResponseDTO;
import Desafio.example.springMagalu.enums.StatusAgendamento;
import Desafio.example.springMagalu.enums.TipoComunicacao;
import Desafio.example.springMagalu.service.AgendamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AgendamentoController.class)
public class AgendamentoControllerTest {

    //PERMITE SIMULAR REQUISOÇÕES HTTP
    @Autowired
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

        AgendamentoResponseDTO response = new AgendamentoResponseDTO(
                1L,
                requestDTO.destinatario(),
                requestDTO.mensagem(),
                requestDTO.tipoComunicacao(),
                requestDTO.dataHoraEnvio(),
                StatusAgendamento.AGENDADO,
                LocalDateTime.now()
        );

        when(service.agendar(any())).thenReturn(response);

        mockMvc.perform(post("/api/agendamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.destinatario").value("cliente@gmail.com"));



    }

    @Test
    void deveBuscarPorID () throws Exception {
        Long id = 1L;

        AgendamentoResponseDTO response = new AgendamentoResponseDTO(
                1L,
                "cliente@gmail.com",
                "mensagem de teste",
                TipoComunicacao.SMS,
                LocalDateTime.now().plusMinutes(2),
                StatusAgendamento.AGENDADO,
                LocalDateTime.now()
        );

        when(service.buscarPorId(id)).thenReturn(response);

        mockMvc.perform(get("/api/agendamentos/" + id));
    }

}