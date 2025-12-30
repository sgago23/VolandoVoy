package com.example.VolandoVoy.repositorios;
import com.example.VolandoVoy.models.Vuelo;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class VueloRepo {
    private final List<Vuelo> vuelos = new ArrayList<>(List.of(
            new Vuelo(1, "IB1010","Iberia","Madrid","Tokyo", LocalDate.of(2025,1,1), LocalDate.of(2025,1,16)),
            new Vuelo(2, "EK202", "Emirates", "Dubai", "New York", LocalDate.of(2025,2,2), LocalDate.of(2025,2,3)),
            new Vuelo(3, "DL909", "Delta", "Atlanta", "Paris", LocalDate.of(2025,3,3), LocalDate.of(2025,3,4)),
            new Vuelo(4, "LH404", "Lufthansa", "Frankfurt", "Los Angeles", LocalDate.of(2025,4,1), LocalDate.of(2025,4,2)),
            new Vuelo(5, "BA505", "British Airways", "London", "Chicago", LocalDate.of(2025,5,2), LocalDate.of(2025,5,21)),
            new Vuelo(6, "TK606", "Turkish Airlines", "Istanbul", "New York", LocalDate.of(2025,6,10), LocalDate.of(2025,6,11)),
            new Vuelo(7, "AF303", "Air France", "Paris", "Tokyo", LocalDate.of(2025,7,9), LocalDate.of(2025,7,16)),
            new Vuelo(8, "QF707", "Qantas", "Sydney", "Los Angeles", LocalDate.of(2025,8,5), LocalDate.of(2025,8,6)),
            new Vuelo(9, "H001-V", "Iberia", "Madrid", "Buenos Aires", LocalDate.of(2025,9,10), LocalDate.of(2025,9,11)),
            new Vuelo(10,"AA808","American Airlines","Miami","London", LocalDate.of(2025,10,12), LocalDate.of(2025,10,13))
    ));

    public List<Vuelo> findAll(){ return vuelos; }
    public Optional<Vuelo> findById(int id){ return vuelos.stream().filter(v -> v.getId()==id).findFirst(); }
    public Vuelo save(Vuelo vuelo){ vuelos.add(vuelo); return vuelo; }
    public void delete(Vuelo vuelo){ vuelos.remove(vuelo); }
}
