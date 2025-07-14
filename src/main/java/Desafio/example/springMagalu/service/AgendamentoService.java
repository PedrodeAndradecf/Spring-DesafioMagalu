package Desafio.example.springMagalu.service;
import Desafio.example.springMagalu.enums.StatusAgendamento;
import Desafio.example.springMagalu.model.Agendamento;
import Desafio.example.springMagalu.dto.AgendamentoRequestDTO;
import Desafio.example.springMagalu.dto.AgendamentoResponseDTO;
import Desafio.example.springMagalu.repository.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor //
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoResponseDTO agendar(AgendamentoRequestDTO agendamentoRequestDTO) {
        Agendamento agendamento = Agendamento.builder()
                .destinatario(agendamentoRequestDTO.destinatario())
                .mensagem(agendamentoRequestDTO.mensagem())
                .tipoComunicacao(agendamentoRequestDTO.tipoComunicacao())
                .dataHoraEnvio(agendamentoRequestDTO.dataHoraEnvio())
                .status(StatusAgendamento.AGENDADO)
                .criadoEm(LocalDateTime.now())
                .build();
        return toResponse(agendamentoRepository.save(agendamento));
    }

    public AgendamentoResponseDTO buscarPorId(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));
        return toResponse(agendamento);
    }

    public void remover(Long id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Agendamento não encontrado!");
        }
        agendamentoRepository.deleteById(id);
    }

    public AgendamentoResponseDTO toResponse(Agendamento agendamento) {
        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getDestinatario(),
                agendamento.getMensagem(),
                agendamento.getTipoComunicacao(),
                agendamento.getDataHoraEnvio(),
                agendamento.getStatus(),
                agendamento.getCriadoEm()
        );
    }
}
