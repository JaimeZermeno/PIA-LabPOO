package com.practica.crud_tareas.controller;

import com.practica.crud_tareas.model.Tarea;
import com.practica.crud_tareas.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService service;

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return service.guardarTarea(tarea);
    }

    @GetMapping
    public List<Tarea> listarTodas() {
        return service.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @RequestBody Tarea detalles) {
        return service.obtenerPorId(id).map(existente -> {
            existente.setTitulo(detalles.getTitulo());
            existente.setMateria(detalles.getMateria());
            existente.setEstatus(detalles.getEstatus());
            Tarea actualizada = service.guardarTarea(existente);
            return ResponseEntity.ok(actualizada);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        if (service.obtenerPorId(id).isPresent()) {
            service.eliminarTarea(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}