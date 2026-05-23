package com.practica.crud_tareas.controller;

import com.practica.crud_tareas.model.Tarea;
import com.practica.crud_tareas.Service.TareaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return service.guardarTarea(tarea);
    }

    @GetMapping
    public List<Tarea> listarTodas() {
        return service.obtenerTodas();
    }

    // Al quitar ResponseEntity, devolvemos la Tarea directa.
    // Si no existe, usamos .orElse(null) para que regrese un JSON vacío.
    @GetMapping("/{id}")
    public Tarea obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id).orElse(null);
    }

    // Actualiza y devuelve la tarea actualizada directa, o null si no existía.
    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Long id, @RequestBody Tarea detalles) {
        return service.obtenerPorId(id).map(existente -> {
            existente.setTitulo(detalles.getTitulo());
            existente.setMateria(detalles.getMateria());
            existente.setEstatus(detalles.getEstatus());
            return service.guardarTarea(existente);
        }).orElse(null);
    }

    // El método se vuelve 'void' (no regresa nada, solo borra).
    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id) {
        if (service.obtenerPorId(id).isPresent()) {
            service.eliminarTarea(id);
        }
    }
}