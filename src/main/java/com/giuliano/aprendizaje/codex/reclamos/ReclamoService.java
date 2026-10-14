package com.giuliano.aprendizaje.codex.reclamos;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReclamoService {

    private final List<Reclamo> reclamos = new ArrayList<>(List.of(
            new Reclamo(1L, "Demora en la entrega del producto", "PENDIENTE"),
            new Reclamo(2L, "Producto recibido con daños", "EN_REVISION")
    ));

    private final AtomicLong proximoId = new AtomicLong(3L);

    public synchronized List<Reclamo> listarReclamos() {
        return List.copyOf(reclamos);
    }

    public synchronized Optional<Reclamo> buscarPorId(Long id) {
        return reclamos.stream()
                .filter(reclamo -> reclamo.id().equals(id))
                .findFirst();
    }

    public synchronized Reclamo crearReclamo(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }

        Reclamo reclamo = new Reclamo(proximoId.getAndIncrement(), descripcion, "PENDIENTE");
        reclamos.add(reclamo);
        return reclamo;
    }
}
