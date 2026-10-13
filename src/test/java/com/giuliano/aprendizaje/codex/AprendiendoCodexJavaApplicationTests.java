package com.giuliano.aprendizaje.codex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AprendiendoCodexJavaApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void listaReclamosDevuelveDosReclamos() throws Exception {
        mockMvc.perform(get("/api/reclamos").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].descripcion").value("Demora en la entrega del producto"))
                .andExpect(jsonPath("$[0].estado").value("PENDIENTE"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].descripcion").value("Producto recibido con daños"))
                .andExpect(jsonPath("$[1].estado").value("EN_REVISION"));
    }

    @Test
    void crearReclamoDevuelveCreatedYLoIncluyeEnLaLista() throws Exception {
        String nuevoReclamo = "{\"descripcion\":\"Entrega incompleta\"}";

        mockMvc.perform(post("/api/reclamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevoReclamo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.descripcion").value("Entrega incompleta"))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));

        mockMvc.perform(get("/api/reclamos").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].descripcion").value("Entrega incompleta"));
    }

    @Test
    void crearReclamoRechazaDescripcionVacia() throws Exception {
        mockMvc.perform(post("/api/reclamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descripcion\":\"   \"}"))
                .andExpect(status().isBadRequest());
    }

}
