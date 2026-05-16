package com.practica.crud_tareas.Service;

import com.practica.crud_tareas.model.Tarea;
import com.practica.crud_tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    @Autowired
    private TareaRepository repository;

    public List<Tarea> obtenerTodas() {
        return repository.findAll();
    }

    public Optional<Tarea> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Tarea guardarTarea(Tarea tarea) {
        return repository.save(tarea);
    }

    public void eliminarTarea(Long id) {
        repository.deleteById(id);
    }
}