package Desafio.example.springMagalu.controller;


import Desafio.example.springMagalu.dto.AgendamentoRequestDTO;
import Desafio.example.springMagalu.dto.AgendamentoResponseDTO;
import Desafio.example.springMagalu.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService service;


    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> agendar(AgendamentoRequestDTO requestDTO) {
        AgendamentoResponseDTO responseDTO = service.agendar(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/id")
    public ResponseEntity<AgendamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        AgendamentoResponseDTO responseDTO = service.buscarPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/id")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

}
