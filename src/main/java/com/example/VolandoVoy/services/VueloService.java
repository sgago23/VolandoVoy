package com.example.VolandoVoy.services;
import com.example.VolandoVoy.datos.VueloRequest;
import com.example.VolandoVoy.models.Vuelo;
import com.example.VolandoVoy.repositorios.VueloRepo;
import com.example.VolandoVoy.utils.FechaUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class VueloService {
    private final VueloRepo repo;

    public VueloService(VueloRepo repo){ this.repo = repo; }

    public List<Vuelo> list(String empresa, String destino, String fechaSalida, String ordenarPor, String orden) {

        return repo.findAll().stream()
                .filter(v -> empresa == null || v.getEmpresa().equalsIgnoreCase(empresa))
                .filter(v -> destino == null || v.getLugarLlegada().equalsIgnoreCase(destino))
                .filter(v -> fechaSalida == null || v.getFechaSalida().toString().equals(fechaSalida))
                .sorted(getComparator(ordenarPor, orden))
                .collect(Collectors.toList());
    }

    private Comparator<Vuelo> getComparator(String ordenarPor, String orden){
        Comparator<Vuelo> comparator;
        if (ordenarPor == null) {
            comparator = Comparator.comparing(Vuelo::getFechaSalida);
        } else {
            comparator = switch (ordenarPor){
                case "empresa" -> Comparator.comparing(Vuelo::getEmpresa, String.CASE_INSENSITIVE_ORDER);
                case "lugarLlegada" -> Comparator.comparing(Vuelo::getLugarLlegada, String.CASE_INSENSITIVE_ORDER);
                default -> Comparator.comparing(Vuelo::getFechaSalida);
            };
        }
        return "DESC".equalsIgnoreCase(orden) ? comparator.reversed() : comparator;
    }

    public Vuelo get(int id){
        return repo.findById(id).orElseThrow(() -> new VueloNotFoundException("Vuelo no encontrado"));
    }

    public Vuelo create(VueloRequest request){
        if(!FechaUtils.fechaValida(request.fechaSalida(), request.fechaLlegada()))
            throw new RuntimeException("Fecha inicio no puede ser mayor a fecha final");

        int nextId = repo.findAll().stream()
                .mapToInt(Vuelo::getId)
                .max()
                .orElse(0) + 1;
        Vuelo vuelo = new Vuelo(nextId,
                request.nombreVuelo(), request.empresa(),
                request.lugarSalida(), request.lugarLlegada(),
                request.fechaSalida(), request.fechaLlegada()
        );
        return repo.save(vuelo);
    }

    public Vuelo update(int id, VueloRequest req){
        Vuelo v = get(id);
        if(!FechaUtils.fechaValida(req.fechaSalida(), req.fechaLlegada()))
            throw new RuntimeException("Fecha inicio inválida");

        v.setNombreVuelo(req.nombreVuelo());
        v.setEmpresa(req.empresa());
        v.setLugarSalida(req.lugarSalida());
        v.setLugarLlegada(req.lugarLlegada());
        v.setFechaSalida(req.fechaSalida());
        v.setFechaLlegada(req.fechaLlegada());
        return v;
    }

    public void delete(int id){
        repo.delete(get(id));
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class VueloNotFoundException extends RuntimeException {
        public VueloNotFoundException(String msg) { super(msg); }
    }
}
