package com.giuliano.aprendizaje.codex.reclamos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamoController {

    private final ReclamoService reclamoService;

    public ReclamoController(ReclamoService reclamoService) {
        this.reclamoService = reclamoService;
    }

    @GetMapping
    public List<Reclamo> listarReclamos() {
        return reclamoService.listarReclamos();
    }

    @PostMapping
    public ResponseEntity<Reclamo> crearReclamo(@RequestBody CrearReclamoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(reclamoService.crearReclamo(request.descripcion()));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
