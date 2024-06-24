package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Docente;
import com.example.demo.models.Dto.Response;
import com.example.demo.services.DocenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/docente")
@Tag(name = "Docentes", description = "Operaciones relacionadas del swagger con docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping
    @Operation(summary = "Obtener todos los docentes")
    public ResponseEntity<List<Docente>> getAll() {    
        return ResponseEntity.ok(docenteService.getAll());
    }

    @PostMapping
    @Operation(summary = "Agregar un nuevo docente")
    public ResponseEntity<?> save(@RequestBody Docente docente) {
        try {
            return ResponseEntity.ok(docenteService.save(docente));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Response<>(e.getMessage()));
        }
    }

    @PutMapping
    @Operation(summary = "Actualizar un docente existente")
    public ResponseEntity<Response> update(@RequestBody Docente docente) {
        var actualizarDocente = docenteService.update(docente);

        if (actualizarDocente == null) {
            return ResponseEntity.ok(new Response<>("Docente no existe"));
        } else {
            return ResponseEntity.ok(new Response<>("Docente Actualizado", actualizarDocente.getId()));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un docente por ID")
    public ResponseEntity<Response> delete(@PathVariable("id") int id) {
        if (docenteService.delete(id)) {
            return ResponseEntity.ok(new Response<>("Docente ha sido eliminado"));
        } else {
            return ResponseEntity.ok(new Response<>("El docente no pudo ser eliminado porque no existe en el sistema"));
        }
    }
}
