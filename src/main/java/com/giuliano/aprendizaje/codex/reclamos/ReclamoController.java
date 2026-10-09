package com.giuliano.aprendizaje.codex.reclamos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamoController {

    @GetMapping
    public List<Reclamo> listarReclamos() {
        return List.of(
                new Reclamo(1L, "Demora en la entrega del producto", "PENDIENTE"),
                new Reclamo(2L, "Producto recibido con daños", "EN_REVISION")
        );
    }
}
