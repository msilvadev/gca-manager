package br.com.gcamanager.controllers;

import br.com.gcamanager.domains.assistance.AssistanceDto;
import br.com.gcamanager.services.process.AssistanceManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("industrial-process")
public class IndustrialProcessManagerController {

    private final AssistanceManagerService service;

    public IndustrialProcessManagerController(AssistanceManagerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AssistanceDto>> listAllAssistance() {
        return ResponseEntity.ok(service.listAllAssistance());
    }

    @PostMapping
    public ResponseEntity<AssistanceDto> saveAssistance(@RequestBody AssistanceDto input) {
        return ResponseEntity.ok(service.saveAssistance(input));
    }
}
