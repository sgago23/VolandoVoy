package com.example.VolandoVoy.controllers;
import com.example.VolandoVoy.datos.VueloRequest;
import com.example.VolandoVoy.models.Vuelo;
import com.example.VolandoVoy.services.VueloService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/vuelos")
public class VueloController {
    private final VueloService service;

    public VueloController(VueloService service){ this.service = service; }

    @GetMapping
    public List<Vuelo> list(
            @RequestParam(required = false) String empresa,
            @RequestParam(required = false) String lugarLlegada,
            @RequestParam(required = false) String fechaSalida,
            @RequestParam(required = false) String ordenarPor,
            @RequestParam(required = false) String ordenar
    ){
        return service.list(empresa, lugarLlegada, fechaSalida, ordenarPor, ordenar);
    }

    @GetMapping("/{id}")
    public Vuelo get(@PathVariable int id){
        return service.get(id);
    }

    @PostMapping
    public Vuelo create(@Valid @RequestBody VueloRequest req){
        return service.create(req);
    }

    @PutMapping("/{id}")
    public Vuelo update(@PathVariable int id, @Valid @RequestBody VueloRequest req){
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
