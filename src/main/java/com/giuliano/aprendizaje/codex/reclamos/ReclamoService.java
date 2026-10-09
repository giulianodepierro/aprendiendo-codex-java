package com.giuliano.aprendizaje.codex.reclamos;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReclamoService {

    public List<Reclamo> listarReclamos() {
        return List.of(
                new Reclamo(1L, "Demora en la entrega del producto", "PENDIENTE"),
                new Reclamo(2L, "Producto recibido con daños", "EN_REVISION")
        );
    }
}
