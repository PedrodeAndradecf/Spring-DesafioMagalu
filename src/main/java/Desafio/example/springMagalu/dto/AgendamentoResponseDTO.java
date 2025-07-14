package Desafio.example.springMagalu.dto;

import Desafio.example.springMagalu.enums.StatusAgendamento;
import Desafio.example.springMagalu.enums.TipoComunicacao;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        Long id, // No response tem id para caso queira fazr uma outra requisição para fazer, por exemplo, um update, então na resposta precisamos do ID (objeto que manipulamos)
        String destinatario,
        String mensagem,
        TipoComunicacao tipoComunicacao,
        LocalDateTime dataHora,
        StatusAgendamento status,
        LocalDateTime criadoEm

) {
}
