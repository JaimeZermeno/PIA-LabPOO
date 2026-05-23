package com.practica.crud_tareas.controller;

import com.practica.crud_tareas.model.Tarea;
import com.practica.crud_tareas.Service.TareaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private TareaService service;

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

    @GetMapping("/{id}")
    public Tarea obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Long id, @RequestBody Tarea detalles) {
        return service.obtenerPorId(id).map(existente -> {
            existente.setTitulo(detalles.getTitulo());
            existente.setMateria(detalles.getMateria());
            existente.setEstatus(detalles.getEstatus());
            return service.guardarTarea(existente);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id) {
        if (service.obtenerPorId(id).isPresent()) {
            service.eliminarTarea(id);
        }
    }
}