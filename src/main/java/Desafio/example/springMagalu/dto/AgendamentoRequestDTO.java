package Desafio.example.springMagalu.dto;

import Desafio.example.springMagalu.enums.TipoComunicacao;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(
        @NotBlank(message = "O destinatario é obrigatorio.")
        String destinatario,

        @NotBlank(message = "A mensagem é obrigatoria.")
        String mensagem,

        @NotBlank(message = "O tipo de comunicação é obrigatorio.")
        TipoComunicacao tipoComunicacao,

        @NotNull(message = "A data e hora de envio são obrigatorios.")
        @Future(message = "A data de envio deve ser futura!")
        LocalDateTime dataHoraEnvio

) {

}
